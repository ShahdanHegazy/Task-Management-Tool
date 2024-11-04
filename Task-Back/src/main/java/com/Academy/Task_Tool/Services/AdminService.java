package com.Academy.Task_Tool.Services;

import com.Academy.Task_Tool.Repository.UserRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private UserRepsitory userRepsitory;

}
