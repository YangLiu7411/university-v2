package com.example.university.service;

import com.example.university.domain.CommonResponse;
import com.example.university.domain.entity.Student;
import com.example.university.domain.entity.StudentAndTeacher;
import com.example.university.domain.entity.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface StudentTeacherService {
    CommonResponse findById(String id);
    CommonResponse findAll();
    CommonResponse insert(StudentAndTeacher stuTea);
    CommonResponse save(StudentAndTeacher stuTea);
    CommonResponse delete(StudentAndTeacher stuTea);
}
