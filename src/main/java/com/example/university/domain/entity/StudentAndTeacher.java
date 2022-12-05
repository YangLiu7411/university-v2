package com.example.university.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "stu_tea")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAndTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_id")
    private Student stu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_id")
    private Teacher tea;


    public StudentAndTeacher(Student stuId, Teacher teaId) {
        this.stu = stuId;
        this.tea = teaId;
    }
}
