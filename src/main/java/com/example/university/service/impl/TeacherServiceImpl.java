package com.example.university.service.impl;


import com.example.university.domain.CommonResponse;
import com.example.university.domain.entity.Student;
import com.example.university.domain.entity.Teacher;
import com.example.university.exception.ResourceNotFoundException;
import com.example.university.respository.student.StudentRepository;
import com.example.university.respository.teacher.TeacherRepository;
import com.example.university.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public CommonResponse findById(String id) {
        Optional<Teacher> tea =  teacherRepository.findById(id);
        if(tea.isEmpty()) {
            //log
            throw new ResourceNotFoundException("...");
        }
        return new CommonResponse(0, new Date(), tea.get());
    }

    @Override
    public CommonResponse findAll() {
        List<Teacher> teaList =  teacherRepository.findAll();
        return new CommonResponse(0, new Date(), teaList);
    }

    @Override
    @Transactional
    public CommonResponse insert(Teacher tea) {
        Teacher teacher = teacherRepository.insert(tea);
        return new CommonResponse(0, new Date(), teacher.getId());
    }

    @Override
    public CommonResponse save(Teacher tea) {
        Teacher teacher = teacherRepository.save(tea);
        return new CommonResponse(0, new Date(), teacher.getId());
    }

    @Override
    public CommonResponse delete(Teacher teacher) {
        teacherRepository.delete(teacher);
        return new CommonResponse(0, new Date(), true);
    }
}
