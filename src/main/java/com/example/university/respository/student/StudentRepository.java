package com.example.university.respository.student;

import com.example.university.domain.entity.Student;
import com.example.university.respository.student.StudentCustomRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, String>, StudentCustomRepo { }
