export interface Task{
 priority: string;
 id?:number;
 cardId?:number
 title:string,
 description: string,
 createAt: Date|null,
 dueDate: Date|null,
 assignedTo: number|null,
}
