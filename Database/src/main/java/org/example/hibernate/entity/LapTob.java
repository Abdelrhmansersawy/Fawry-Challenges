package org.example.hibernate.entity;

import jakarta.persistence.*;

@Entity
public class LapTob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    @ManyToOne
    private Student ownStudent;

    public LapTob() {}
    public LapTob(Student ownStudent, String model) {
        this.ownStudent = ownStudent;
        this.model = model;
    }


}
