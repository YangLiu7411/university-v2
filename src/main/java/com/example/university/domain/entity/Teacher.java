package com.example.university.domain.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "tea", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StudentAndTeacher> students = new ArrayList<>();


    public boolean addRelations(StudentAndTeacher rel) {
        students.add(rel);
        return true;
    }
}
