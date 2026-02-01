package com.example.studentapi.Controllers;

import com.example.studentapi.Models.Student;
import com.example.studentapi.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Students")
public class StudentController
{
    @Autowired
    private StudentService studentService;


    @GetMapping("/list")
    public List<Student> getlist()
    {
        return  studentService.getStudents();
    }

    @GetMapping("marks")
    public void getMarks()
    {
        //studentService.getMarksFromMarksAPI();
        studentService.getMarksAPI();
    }
}
