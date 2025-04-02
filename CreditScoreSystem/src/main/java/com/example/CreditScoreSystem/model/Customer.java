package com.example.CreditScoreSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private int age;
    private String phoneNumber;
    private double creditLimit;
    private double usedLimit;
    private double income;
    @Column(nullable = true)
    private int creditScore;

    @OneToMany(mappedBy = "customer")
    private List<CreditInquiries> creditInquiries;

    @OneToMany(mappedBy = "customer")
    private List<CustomerPayments> customerPayments;
}
