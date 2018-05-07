package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BANK_ACCOUNT")
@Data
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ACCOUNT_VALUE")
    private Long value;

    @OneToMany(mappedBy = "source", fetch = FetchType.EAGER)
    private Set<BankAccountOperation> operations;

}
