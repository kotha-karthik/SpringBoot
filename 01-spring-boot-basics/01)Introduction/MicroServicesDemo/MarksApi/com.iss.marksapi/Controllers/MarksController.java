package com.example.marksapi.Controllers;

import com.example.marksapi.Models.Marks;
import com.example.marksapi.Services.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.error.Mark;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Marks")
public class MarksController
{
    @Autowired
    private MarksService marksService;

    @GetMapping("/list")
    public   List<Marks> getMarks()
    {
        return marksService.getMarks();
    }

}
