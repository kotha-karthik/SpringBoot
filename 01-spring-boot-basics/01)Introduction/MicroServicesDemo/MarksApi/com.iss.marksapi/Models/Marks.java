package com.example.marksapi.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Marks
{
    private int roll_no;
    private int math;
    private int physics;
    private int chemistry;
}
