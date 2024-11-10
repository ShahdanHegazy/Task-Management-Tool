import { Role } from "../enums/Role";

export
interface User {
    name: string;
    email: string;
    password: string;
    roleId: Role; // Numeric representation for efficiency and consistency
  }
