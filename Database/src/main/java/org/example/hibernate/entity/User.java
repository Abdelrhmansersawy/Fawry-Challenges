package org.example.hibernate.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Transient // Won't save into database
    private String fullName;
    private String username;
    private String password;
    private LocalDateTime created;

    public User(){}
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        created = LocalDateTime.now();
    }
}
