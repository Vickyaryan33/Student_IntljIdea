package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "StudentIntljIdea5")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "name" , nullable = false)
    private String name;
    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;
    @Column(name = "mobile", nullable = false, unique = true)
    private String mobile;
}
