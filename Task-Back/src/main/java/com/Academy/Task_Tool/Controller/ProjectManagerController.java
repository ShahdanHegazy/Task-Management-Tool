package com.Academy.Task_Tool.Controller;//package com.Academy.Task_Tool.Controller;
import com.Academy.Task_Tool.Entity.Comment;
import com.Academy.Task_Tool.Services.AdminService;
import com.Academy.Task_Tool.Services.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProjectManagerController {

    @Autowired
    private ProjectManagerService projectManagerService;


}

