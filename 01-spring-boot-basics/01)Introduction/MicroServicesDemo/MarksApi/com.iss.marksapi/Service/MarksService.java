package com.example.marksapi.Services;

import com.example.marksapi.Controllers.MarksController;
import com.example.marksapi.Models.Marks;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarksService
{
    private List<Marks> marks;
    public MarksService()
    {
        marks = new ArrayList<Marks>();
        marks.add(Marks.builder().roll_no(1).physics(100).chemistry(90).math(90).build());
        marks.add(Marks.builder().roll_no(1).physics(190).chemistry(190).math(190).build());
        marks.add(Marks.builder().roll_no(1).physics(10).chemistry(20).math(30).build());
    }
    public  List<Marks> getMarks()
    {
        return marks;
    }
}
