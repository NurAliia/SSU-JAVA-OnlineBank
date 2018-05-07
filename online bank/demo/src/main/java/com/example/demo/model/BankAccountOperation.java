package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "OPERATION")
@Data
public class BankAccountOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SOURCE_BANK_ACCOUNT_ID")
    private BankAccount source;

    @Column(name = "TARGET_BANK_ACCOUNT_ID")
    private Long target;

    @Column(name = "SUM", nullable = false)
    private Long sum;

    @Column(name = "OPERATION_DATE", nullable = false)
    private Date date;

    @Column(name = "MARKER")
    private String marker;

    @Transient
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy kk:mm:ss");


}
