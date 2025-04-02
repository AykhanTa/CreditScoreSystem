package com.example.CreditScoreSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
public class CustomerPayments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String creditType;
    private double creditAmount;
    private int latePaymentDay;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

}
