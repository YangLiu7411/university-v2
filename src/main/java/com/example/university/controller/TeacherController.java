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
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final StudentService studentService;

    @Autowired
    public TeacherController(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> findStuById(@PathVariable String id) {
        return new ResponseEntity<>(teacherService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<CommonResponse> findAll() {
        return new ResponseEntity<>(teacherService.findAll(), HttpStatus.OK);
    }


    //path: /teacher, method: post ,  create teacher
    @PostMapping
    public ResponseEntity<CommonResponse> insert(@RequestBody Teacher teacher) {

        return new ResponseEntity<>(teacherService.insert(teacher), HttpStatus.CREATED);
    }


    //update
    @PutMapping(value = "/{id}")
    public ResponseEntity<CommonResponse> update(@PathVariable String id, @RequestBody Teacher teacher){
        CommonResponse response = teacherService.findById(id);
        Teacher t = (Teacher) response.getContent();
        t.setId(teacher.getId());
        t.setName(teacher.getName());
        t.setStudents(teacher.getStudents());

        CommonResponse updated = teacherService.save(t);
        return ResponseEntity.ok(updated);
    }

    //update relations
    @PutMapping(value = "/{teaId}/student/{stuId}") //, consumes = {"multipart/mixed"}
    public ResponseEntity<CommonResponse> update(@PathVariable String teaId, @PathVariable String stuId){
        CommonResponse response = teacherService.findById(teaId);
        CommonResponse response1 = studentService.findById(stuId);
        Student s = (Student) response1.getContent();
        Teacher t = (Teacher) response.getContent();
        t.addRelations(new StudentAndTeacher(s, t));

        CommonResponse updated = teacherService.save(t);
        return ResponseEntity.ok(updated);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        CommonResponse response = teacherService.findById(id);
        Teacher student = (Teacher) response.getContent();
        System.out.println(student.getId() + " " + student.getName());
        teacherService.delete(student);
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
