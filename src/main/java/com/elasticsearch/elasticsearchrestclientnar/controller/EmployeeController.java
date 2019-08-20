package com.elasticsearch.elasticsearchrestclientnar.controller;

import com.elasticsearch.elasticsearchrestclientnar.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/lowlevel/getAllEmployees")
    public String getAllEmployees(){
        return employeeService.getAllEmployees();
    }
}
