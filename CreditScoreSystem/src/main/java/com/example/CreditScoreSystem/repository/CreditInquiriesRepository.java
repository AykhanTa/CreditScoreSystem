package com.example.CreditScoreSystem.repository;

import com.example.CreditScoreSystem.model.CreditInquiries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditInquiriesRepository extends JpaRepository<CreditInquiries, Integer> {
}
