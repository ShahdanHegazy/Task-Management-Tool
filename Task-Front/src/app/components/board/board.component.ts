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
import { NgFor } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { ActivatedRoute } from '@angular/router';
import { BoardService } from '../../services/board.service';
import { BoardMember } from '../../interfaces/BoardMember';
import { SignedMembersPost } from '../../interfaces/SignedMembersPost';


@Component({
  selector: 'app-board',
  standalone: true,
  imports: [CdkDropList, CdkDrag,DialogModule,ButtonModule,InputTextModule,FormsModule,MultiSelectModule,NgFor],
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent implements OnInit {
  constructor(private _AuthService: AuthService,private route:ActivatedRoute,private BoardService:BoardService){}
// OnInit
  // ngOnInit(): void {    
  //  console.log(this.projectInformation.lists.find((list: { id: number; }) => list.id == 1)?.cardList);
  //   this._AuthService.getuserInformation();
  //   this._AuthService.userData.subscribe((data) => {
  //     if (data) {
  //       this.roleId=data.roleId;
  //     }
  //   });
  //   this.route.params.subscribe(params => {
  //     this.projectId = params['id'];
  //     console.log(this.projectId); // هيطبع الـ ID الجديد كل مرة يتغير فيها
  //   });
  //   this.BoardService.getAllMembers().subscribe( {
  //     next:(response)=>{
  //       console.log(response);
  //       this.Allmembers = response;
        
  //     },
  //     error:(err)=>console.error(err)
  //   });
  //   this.BoardService.getSignedMembers(this.projectId).subscribe({
  //     next:(response)=>this.showProjectMembers=response,
  //     error:(err)=>console.error(err)
      
  //   })
  // }
  ngOnInit(): void {    
    console.log(this.projectInformation.lists.find((list: { id: number; }) => list.id == 1)?.cardList);
    this._AuthService.getuserInformation();
    this._AuthService.userData.subscribe((data) => {
      if (data) {
        this.roleId = data.roleId;
      }
    });
    this.route.params.subscribe(params => {
      this.projectId = params['id'];
      console.log(this.projectId);
    });
  
    // جلب جميع الأعضاء
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
          "id": 1,
          "name": "the first list",
          "cardList": [{"id":1,"title":"first task","description":"first task description","startDate":null,"endDate":null,"taskMembers":[1,2]}]
      },
      {
          "id": 2,
          "name": "the second list",
          "cardList": [{"id":3,"title":"front task","description":"front task description","startDate":null,"endDate":null,"taskMembers":[3]}]
      },
      {
        "id": 3,
        "name": "third list",
        "cardList": [{"id":5,"title":"back task","description":"back task description","startDate":null,"endDate":null,"taskMembers":[4,5]}]
      }]
    }
    roleId?:number ;
    todo: Task[] = this.projectInformation.lists.find((list: { id: number; }) => list.id == 1)?.cardList;
    inProgress: Task[] = this.projectInformation.lists.find((list: { id: number; }) => list.id == 2)?.cardList;
    done: Task[] =this.projectInformation.lists.find((list: { id: number; }) => list.id == 3)?.cardList;
    
  Allmembers:BoardMember[]=[]
  projectMembers:any[]=[];
  showProjectMembers:any[]=[]
  taskMembers: number[] = [];
  visible: boolean = false;
  activeList: Task[] = [];
  newTask: Task={
    title:'',
    description: '',
    startDate: null,
    endDate: null,
    taskMembers:[],
  };

// open task form dialog
openDialog(listName: Task[]): void {
  this.activeList = listName;
  this.newTask = {
    title: '',
    description: '',
    startDate: null,
    endDate: null,
    taskMembers: [],
  };
  this.visible = true;
}
// close task form dialog
closeDialog(): void {
  this.visible = false;
}

// Create a new Task
createTask(): void {
  const index = this.activeList.findIndex((t) => t.id === this.newTask.id);

  if (index !== -1) {
    // تحديث المهمة الحالية
    this.activeList[index] = { ...this.newTask };
  } else {
    // إضافة مهمة جديدة
    this.newTask.id = Date.now(); // توليد ID جديد
    this.activeList.push({ ...this.newTask });
  }

  this.visible = false; // غلق الفورم
  console.log('Updated/New Task:', this.newTask);
}

// Edit on Task
editTask(task: Task, listName: Task[]): void {
  this.newTask = { ...task }; // نسخ بيانات المهمة الحالية إلى النموذج
  this.activeList = listName; // تحديد القائمة المرتبطة بالمهمة
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

}

