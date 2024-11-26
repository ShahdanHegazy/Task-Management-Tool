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


@Component({
  selector: 'app-board',
  standalone: true,
  imports: [CdkDropList, CdkDrag,DialogModule,ButtonModule,InputTextModule,FormsModule,MultiSelectModule,NgFor],
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent implements OnInit {
  constructor(private _AuthService: AuthService,private route:ActivatedRoute){}
  ngOnInit(): void {    
   console.log(this.projectInformation.lists.find((list: { id: number; }) => list.id == 1)?.cardList);
    this._AuthService.getuserInformation();
    this._AuthService.userData.subscribe((data) => {
      if (data) {
        this.roleId=data.roleId;
      }
    });
    this.route.params.subscribe(params => {
      this.projectId = params['id'];
      console.log(this.projectId); // هيطبع الـ ID الجديد كل مرة يتغير فيها
    });
  }
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
  Allmembers:any[]=[
    {id:1,name:'member1'},
    {id:2,name:'member2'},
    {id:3,name:'member3'},
    {id:4,name:'member4'},
    {id:5,name:'member5'},
    {id:6,name:'member6'},
  ]
  roleId?:number ;
  todo: Task[] = this.projectInformation.lists.find((list: { id: number; }) => list.id == 1)?.cardList;
  inProgress: Task[] = this.projectInformation.lists.find((list: { id: number; }) => list.id == 2)?.cardList;
  done: Task[] =this.projectInformation.lists.find((list: { id: number; }) => list.id == 3)?.cardList;
  
projectMembers:any[] = [];
taskMembers: number[] = [];
visible: boolean = false;
activeList: Task[] = [];


newTask: Task = {
  title: '',
  description: '',
  startDate: null,
  endDate: null,
  taskMembers: [],
};


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

closeDialog(): void {
  this.visible = false;
}


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


editTask(task: Task, listName: Task[]): void {
  this.newTask = { ...task }; // نسخ بيانات المهمة الحالية إلى النموذج
  this.activeList = listName; // تحديد القائمة المرتبطة بالمهمة
  this.visible = true; // عرض الفورم
}


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


trackById(index: number, item: any): number {
  return item.id;
}
deleteTask(index: number, listName: Task[], event: Event): void {
  event.stopPropagation(); // إيقاف انتشار الحدث
  listName.splice(index, 1); // حذف المهمة بناءً على الفهرس
  console.log('Task deleted:', index);
}

selectedMembers: number[] = []; // قائمة الأعضاء الذين تم اختيارهم مؤقتاً
updateSelectedMembers(): void {
  // وضع الأعضاء الحاليين في projectMembers داخل selectedMembers
  this.selectedMembers = this.projectMembers.map((member) => member.id);
}
addMembersToProject(): void {
  // إضافة الأعضاء من selectedMembers إلى projectMembers فقط إذا لم يكونوا موجودين بالفعل
  this.selectedMembers.forEach((id) => {
    const member = this.Allmembers.find((m) => m.id === id);
    if (member && !this.projectMembers.some((m) => m.id === member.id)) {
      this.projectMembers.push(member);
    }
  });

  // إفراغ الـ selectedMembers بعد الإضافة
  this.selectedMembers = [];

  console.log('Updated Project Members:', this.projectMembers);
}

}

