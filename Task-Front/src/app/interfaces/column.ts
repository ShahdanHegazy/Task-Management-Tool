import { Todo } from "./Todo";
export interface Column{
    id?: number;
    title: string;
    todos: Todo[];
}