package com.example.studentapi.Services;

import com.example.studentapi.Models.Marks;
import com.example.studentapi.Models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService
{
    private List<Student> students;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClient;
    public StudentService()
    {
        students = new ArrayList<>();
        students.add(Student.builder().roll_no(1).name("John").fees(15.00).build());
        students.add(Student.builder().roll_no(2).name("Jane").fees(10.00).build());
        students.add(Student.builder().roll_no(3).name("Jack").fees(50.00).build());
        students.add(Student.builder().roll_no(4).name("Sam").fees(75.00).build());
    }
    public List<Student> getStudents()
    {
        return students;
    }
    public void getMarksFromMarksAPI()
    {
        //by using the resttemplate
        ResponseEntity<List<Marks>> entity = restTemplate.exchange("http://localhost:8080/Marks/list", HttpMethod.GET,null,new ParameterizedTypeReference<List<Marks>>() {});
        entity.getBody().stream().forEach(System.out::println);
    }

    public void getMarksAPI()
    {
        // using the webclient
        Mono<ResponseEntity<List<Marks>>> marks=webClient.build().get().uri("/Marks/list")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Marks>>() {});

        marks.subscribe(p->p.getBody().stream().forEach(System.out::println));

    }


}
