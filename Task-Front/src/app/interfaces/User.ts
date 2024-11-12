import { Role } from "../enums/Role";

export
interface User {
    id :number;
    name: string;
    email: string;
    password: string;
    roleId: Role;
  }
