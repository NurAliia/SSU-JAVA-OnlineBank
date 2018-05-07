package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ROLE_TABLE")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ROLE")
    private String role;

}
