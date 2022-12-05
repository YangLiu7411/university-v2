package com.example.university.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "stu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<StudentAndTeacher> teachers = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public boolean addRelations(StudentAndTeacher rel) {
        teachers.add(rel);
        return true;
    }
}
