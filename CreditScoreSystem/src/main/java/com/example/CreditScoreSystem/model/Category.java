package com.example.CreditScoreSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String creditType;

    @OneToMany(mappedBy = "category")
    private List<CreditInquiries> creditInquiries;
}
