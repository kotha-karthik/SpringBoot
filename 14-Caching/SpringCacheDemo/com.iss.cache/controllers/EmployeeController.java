package com.iss.springcachedemo.controllers;

import com.iss.springcachedemo.models.Employee;
import com.iss.springcachedemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("id/{id}")
    public Employee getEmployee(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/update")
    public Employee updateEmployee(@RequestBody Employee employee) {

            return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/del/{id}")
    public String deleteEmployee(@PathVariable int id)
    {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully";
    }
}
