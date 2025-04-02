package com.example.CreditScoreSystem.service;

public interface CreditInquiriesService {
    void applyCredit(int customerId,int categoryId);
    Integer getCreditScore(int customerId);
    void updateCreditScore(int customerId,int score);

}
