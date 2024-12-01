import {Component, OnInit} from '@angular/core';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
  CdkDrag,
  CdkDropList,
} from '@angular/cdk/drag-drop';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { MultiSelectModule } from 'primeng/multiselect';
import { Task } from '../../interfaces/Task';
import { NgFor, NgIf } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { ActivatedRoute } from '@angular/router';
import { BoardService } from '../../services/board.service';
import { BoardMember } from '../../interfaces/BoardMember';
import { DropdownModule } from 'primeng/dropdown';

@Component({
  selector: 'app-board',
  standalone: true,
  imports: [DropdownModule,NgIf,CdkDropList, CdkDrag,DialogModule,ButtonModule,InputTextModule,FormsModule,MultiSelectModule,NgFor],
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent implements OnInit {
  constructor(private _AuthService: AuthService,private route:ActivatedRoute,private BoardService:BoardService){}
  
  ngOnInit(): void {
    this._AuthService.getuserInformation();
    this._AuthService.userData.subscribe((data) => {
      if (data) {
        this.roleId = data.roleId;
      }
    });
    this.route.params.subscribe(params => {
      this.projectId = params['id'];
      console.log(this.projectId);
      // this.loadAllMembers();
    });
    this.loadBoardData();
  }

  
// Variables
projectId!:number;
projectInformation!:any;
editMood:boolean=false;
roleId?:number ;
todo: Task[] = [];
priorityList: any[]=[
  {prioState:"high"},
  {prioState:"medium"},
  {prioState:"low"}
];
    inProgress: Task[] = [];
    done: Task[] = [];

  Allmembers:BoardMember[]=[]
  projectMembers:any[]=[];
  showProjectMembers:any[]=[]
  taskMember!: number;
  visible: boolean = false;
  activeList: Task[] = [];
  success:boolean = false;
  newTask: Task={
    title:'',
    description: '',
    createAt: null,
    dueDate: null,
    assignedTo:null,
    priority:''
  };

// open task form dialog
openDialog(listName: Task[]): void {
  this.activeList = listName;
  this.newTask = {
    title: '',
    description: '',
    createAt: null,
    dueDate: null,
    assignedTo:null,
    priority:''
  };
  this.visible = true;
}
// close task form dialog
closeDialog(): void {
  this.newTask = {
    title: '',
    description: '',
    createAt: null,
    dueDate: null,
    assignedTo: null,
    priority:''
  };
  this.activeList = [];
  this.visible = false;}

// Create a new Task
createTask(projectId:number,listName:string): void {
  this.BoardService.createTask(projectId,listName,this.newTask).subscribe({
    next: (response)=>{
      console.log("created Task with Info "+response)
      this.loadBoardData();
      // this.activeList.push({...this.newTask, id: response.id}); // افترضنا إن السيرفر بيرجع الـ ID
    },
    error:(err)=>console.error(err)
  })
  console.log(this.newTask);
  
  this.visible = false; // غلق الفورم
}

// Edit on Task
editTask(task: Task, listName: Task[]): void {
  this.newTask = { ...task }; // نسخ بيانات المهمة الحالية إلى النموذج
  this.activeList = listName; // تحديد القائمة المرتبطة بالمهمة
  this.visible = true; // عرض الفورم
  this.editMood = true; // تعطيل ال��ضافة ��لى ��فحة التحرير
}
updateTask(taskId:number,task:Task){
  this.BoardService.updateTask(taskId,task).subscribe({
    next: (response)=>{
      console.log("Task updated with Info "+response)
      this.loadBoardData();
    },
    error:(err)=>console.error(err)
  });
  this.editMood = false; // تعطيل التحرير
  this.visible = false; // ��لق الفورم
}

deleteTask(taskId:number,event:Event){
  event.stopPropagation(); 
  console.log("Deleting Task with ID:", taskId);
  this.BoardService.deleteTask(taskId).subscribe({
    next: (response)=>{
      console.log("Task deleted with Info "+response)
      this.loadBoardData();
    },
    error:(err)=>console.error(err)
  });
}

drop(event: CdkDragDrop<Task[]>): void {
  
  if (event.previousContainer === event.container) {
    // إذا تم السحب داخل نفس العمود
    moveItemInArray(
      event.container.data,
      event.previousIndex,
      event.currentIndex
    );
  } else {
    // إذا تم نقل المهمة إلى عمود مختلف
    const movedTask = event.previousContainer.data[event.previousIndex];
    const sourceListName = this.getListName(event.previousContainer.data);
    const targetListName = this.getListName(event.container.data);

    // تحديث البيانات في الواجهة مباشرة
    transferArrayItem(
      event.previousContainer.data,
      event.container.data,
      event.previousIndex,
      event.currentIndex
    );

    // استدعاء API لتحريك المهمة
    this.BoardService.moveTask(
      this.projectId,
      sourceListName,
      movedTask.cardId!,
      targetListName
    ).subscribe({
      next: (response) => {
        console.log('Task moved successfully:', response);
      },
      error: (err) => {
        console.error('Error moving task:', err);
        // في حال الخطأ، قم بإرجاع المهمة إلى العمود السابق
        transferArrayItem(
          event.container.data,
          event.previousContainer.data,
          event.currentIndex,
          event.previousIndex
        );
      },
    });
  }
}


// دالة مساعدة للحصول على اسم القائمة
getListName(list: Task[]): string {
  if (list === this.todo) return 'To-Do';
  if (list === this.inProgress) return 'In-Progress';
  if (list === this.done) return 'Done';
  return '';
}

// Helper function to map container IDs to list names



addMembersToProject(): void {
  this.BoardService.postSignedMembers({projectId:this.projectId,userIds:this.projectMembers}).subscribe({
    next:(response)=>{
      console.log(response),
      this.success = true;
      setTimeout(() => {
        this.success = false;
      }, 2000)

    },
    error:(err)=>console.error(err)
  })
  // this.selectedMembers = [];
  console.log('Updated Project Members:',{projectId:this.projectId,userIds:this.projectMembers});
}

loadAllMembers() {
  this.BoardService.getAllMembers().subscribe({
    next: (response) => {
      this.Allmembers = response;

      // تعيين خاصية selected بناءً على projectMembers
      this.Allmembers.forEach((member) => {
        member.selected = this.projectMembers.includes(member.id);
      });

      console.log("All Members:", this.Allmembers);
    },
    error: (err) => console.error(err),
  });
}


loadBoardData() {
  this.BoardService.getBoardData(this.projectId).subscribe({
    next: (response) => {
      this.projectInformation = response;
      this.showProjectMembers = response.assignedUsers;
      this.projectMembers = response.assignedUsers.map((member: { id: number }) => member.id);

      this.todo = response.lists.find((list: { name: string }) => list.name === "To-Do")?.cardList || [];
      this.inProgress = response.lists.find((list: { name: string }) => list.name === "In-Progress")?.cardList || [];
      this.done = response.lists.find((list: { name: string }) => list.name === "Done")?.cardList || [];
      console.log("Board Data:", response);

      // تحميل باقي الأعضاء
      this.loadAllMembers();
    },
    error: (err) => console.error(err),
  });
}



}

