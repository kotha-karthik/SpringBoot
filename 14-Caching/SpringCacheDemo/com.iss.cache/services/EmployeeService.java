package com.iss.springcachedemo.services;

import com.iss.springcachedemo.models.Employee;
import com.iss.springcachedemo.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
A

    @Cacheable(value="Employee",key = "#id")
    public Employee getEmployeeById(Integer id) {
        return employeeRepo.findById(id).orElse(null);
    }

    @CachePut(value="Employee",key="#employee.emp_id")
    public Employee updateEmployee(Employee employee) {
        System.out.println("Saving employee details");
        return employeeRepo.save(employee);
    }

    @CacheEvict(value="Employee",key = "#id")
    public void deleteEmployee(Integer id) {
        System.out.println("Deleting employee details");
        employeeRepo.deleteById(id);
    }
}
