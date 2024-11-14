export interface Project {
  project_id?: number;
  projectName: string;
  projectManagerId: number|null;
  start_date:Date|null;
  end_date:Date|null;
  description:string;
  members?: string[];
}
