package com.example.university.controller;

import com.example.university.domain.CommonResponse;
import com.example.university.domain.entity.Student;
import com.example.university.domain.entity.StudentAndTeacher;
import com.example.university.domain.entity.Teacher;
import com.example.university.exception.ResourceNotFoundException;
import com.example.university.service.StudentService;
import com.example.university.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public StudentController(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> findStuById(@PathVariable String id) {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<CommonResponse> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }


    //path: /student, method: post ,  create student
    @PostMapping
    public ResponseEntity<CommonResponse> insert(@RequestBody Student student) {

        return new ResponseEntity<>(studentService.insert(student), HttpStatus.CREATED);
    }


    //update
    @PutMapping(value = "/{id}") //, consumes = {"multipart/mixed"}
    public ResponseEntity<CommonResponse> update(@PathVariable String id, @RequestBody Student student){
        CommonResponse response = studentService.findById(id);
        Student s = (Student) response.getContent();
        s.setId(student.getId());
        s.setName(student.getName());
        s.setTeachers(student.getTeachers());

        CommonResponse updated = studentService.save(s);
        return ResponseEntity.ok(updated);
    }

    //update relations
    @PutMapping(value = "/{stuId}/teacher/{teaId}") //, consumes = {"multipart/mixed"}
    public ResponseEntity<CommonResponse> update(@PathVariable String stuId, @PathVariable String teaId){
        CommonResponse response = studentService.findById(stuId);
        CommonResponse response1 = teacherService.findById(teaId);
        Student s = (Student) response.getContent();
        Teacher t = (Teacher) response1.getContent();
        s.addRelations(new StudentAndTeacher(s, t));

        CommonResponse updated = studentService.save(s);
        return ResponseEntity.ok(updated);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        CommonResponse response = studentService.findById(id);
        Student student = (Student) response.getContent();
        System.out.println(student.getId() + " " + student.getName());
        studentService.delete(student);
        return ResponseEntity.ok(true);
    }


    //TODO: /{id} , method : put,  update student



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CommonResponse> handleNotFound() {
        return new ResponseEntity<>(
                    new CommonResponse(-1, new Date(), "resource not found"),
                    HttpStatus.NOT_FOUND
                );
    }


}
