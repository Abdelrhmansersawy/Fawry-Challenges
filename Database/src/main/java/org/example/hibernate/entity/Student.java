package org.example.hibernate.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<LapTob> ownLapTob;
    public Student() {}
    public Student(String name) {
        this.name = name;
    }


}
