import { Role } from "../enums/Role";

export
interface User {
    username: string;
    email: string;
    password: string;
    roleid: Role; // Numeric representation for efficiency and consistency
  }