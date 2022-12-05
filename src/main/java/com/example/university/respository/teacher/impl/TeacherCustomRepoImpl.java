package com.example.university.respository.teacher.impl;

import com.example.university.domain.entity.Teacher;
import com.example.university.respository.teacher.TeacherCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
public class TeacherCustomRepoImpl implements TeacherCustomRepo {

    private final EntityManager entityManager;

    @Autowired
    public TeacherCustomRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Teacher insert(Teacher teacher) {
        entityManager.persist(teacher);
        return teacher;
    }
}
