import { Component, Inject } from '@angular/core';
import { Todo } from '../../interfaces/Todo';
import { CommonModule } from '@angular/common';
import { DIALOG_DATA, DialogRef } from '@angular/cdk/dialog';


  interface InputData{
    todo: Todo;
  }
  
  interface outputData{
    rta:boolean;
  }
  
  @Component({
    selector: 'app-todo-dialog',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './todo-dialog.component.html'
  })
  export class TodoDialogComponent {
  
    todo: Todo;
  
    constructor(
      private dialogRef: DialogRef<outputData>,
      @Inject(DIALOG_DATA) data: InputData
    ){
      this.todo = data.todo;
    }
  
  
    close(){
      this.dialogRef.close()
    }
  
    closeWithRta(rta: boolean){
      this.dialogRef.close({rta});
    }
  }
  
