package com.example.CreditScoreSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
public class CreditInquiries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isApproved;

    @ManyToOne
    @JoinColumn(name="category_id",nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name="customer_id",nullable = false)
    private Customer customer;
}