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
      this.loadAllMembers();
      
    });
  
    // جلب جميع الأعضاء
  }
  

// Variables
 projectId!:number;
 projectInformation:any={
  "project_id": 1,
  "project_name": "project 1",
  "description": "description for project one ",
  "start_date": null,
  "end_date": null,
  "assignedUsers": [],

  "lists": [
        {
            "id": 8,
            "name": "To Do",
            "cardList": [
                {
                    "cardId": 1,
                    "title": "Complete the project documentation",
                    "dueDate": "2024-12-31T23:59:59.000+00:00",
                    "description": "Ensure all project-related documents are finalized and reviewed.",
                    "priority": "High",
                    "createAt": null,
                    "createBy": null,
                    "assignedTo": null
                }
            ]
        },
        {
            "id": 9,
            "name": "In Progress",
            "cardList": []
        },
        {
            "id": 10,
            "name": "Done",
            "cardList": []
}
]

    }
    roleId?:number ;
    todo: Task[] = this.projectInformation.lists.find((list: { id: number; name:string }) => list.name == "To Do")?.cardList;
    inProgress: Task[] = this.projectInformation.lists.find((list: { id: number; name:string }) => list.name == "In Progress")?.cardList;
    done: Task[] =this.projectInformation.lists.find((list: { id: number; name:string }) => list.name == "Done")?.cardList;
    
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
    startDate: null,
    endDate: null,
    taskMember:null,
  };

// open task form dialog
openDialog(listName: Task[]): void {
  this.activeList = listName;
  this.newTask = {
    title: '',
    description: '',
    startDate: null,
    endDate: null,
    taskMember:null,
  };
  this.visible = true;
}
// close task form dialog
closeDialog(): void {
  this.visible = false;
}

// Create a new Task
createTask(): void {
  // const index = this.activeList.findIndex((t) => t.id === this.newTask.id);

  // if (index !== -1) {
  //   // تحديث المهمة الحالية
  //   this.activeList[index] = { ...this.newTask };
  // } else {
  //   // إضافة مهمة جديدة
  //   this.newTask.id = Date.now(); // توليد ID جديد
  //   this.activeList.push({ ...this.newTask });
  // }

  this.visible = false; // غلق الفورم
  console.log('Updated/New Task:', this.newTask);
}

// Edit on Task
editTask(task: Task, listName: Task[]): void {
  // this.newTask = { ...task }; // نسخ بيانات المهمة الحالية إلى النموذج
  // this.activeList = listName; // تحديد القائمة المرتبطة بالمهمة
  this.visible = true; // عرض الفورم
}
// Delete the task
deleteTask(index: number, listName: Task[], event: Event): void {
  event.stopPropagation(); // إيقاف انتشار الحدث
  listName.splice(index, 1); // حذف المهمة بناءً على الفهرس
  console.log('Task deleted:', index);
}

// Drag drop function
drop(event: CdkDragDrop<Task[]>): void {
  if (event.previousContainer === event.container) {
    moveItemInArray(
      event.container.data,
      event.previousIndex,
      event.currentIndex
    );
  } else {
    transferArrayItem(
      event.previousContainer.data,
      event.container.data,
      event.previousIndex,
      event.currentIndex
    );
  }
}

addMembersToProject(): void {
  this.BoardService.postSignedMembers({projectId:this.projectId,userIds:this.projectMembers}).subscribe({
    next:(response)=>{
      console.log(response),
      this.success = true;
      setTimeout(() => {
        this.success = false;
      }, 2000)
      this.BoardService.getSignedMembers(this.projectId).subscribe(
        {
        next: (members) => this.showProjectMembers = members,
        error: (err) => console.error(err),
        }
    )
    },
    error:(err)=>console.error(err)
  })
  // this.selectedMembers = [];
  console.log('Updated Project Members:',{projectId:this.projectId,userIds:this.projectMembers});
}
loadAllMembers(){
  this.BoardService.getAllMembers().subscribe({
    next: (response) => {
      this.Allmembers = response;

      // بعد جلب كل الأعضاء، اربط الأعضاء الحاليين بالمشروع كـ selected
      this.BoardService.getSignedMembers(this.projectId).subscribe({
        next: (signedMembers) => {
          this.showProjectMembers = signedMembers;
          // جعل الأعضاء المختارين في حالة selected
          this.projectMembers = signedMembers.map((member: { id: any; }) => member.id);
        },
        error: (err) => console.error(err),
      });
    },
    error: (err) => console.error(err),
  });
}
}

