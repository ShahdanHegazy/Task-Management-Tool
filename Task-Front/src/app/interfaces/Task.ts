export interface Task{
    id?:number;
    title:string,
    description: string,
    startDate: Date|null,
    endDate: Date|null,
    taskMembers: number[],
}