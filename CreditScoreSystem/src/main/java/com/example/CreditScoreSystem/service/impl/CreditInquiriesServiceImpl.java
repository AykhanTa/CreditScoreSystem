package com.example.CreditScoreSystem.service.impl;

import com.example.CreditScoreSystem.model.CreditInquiries;
import com.example.CreditScoreSystem.model.Customer;
import com.example.CreditScoreSystem.model.CustomerPayments;
import com.example.CreditScoreSystem.repository.CategoryRepository;
import com.example.CreditScoreSystem.repository.CreditInquiriesRepository;
import com.example.CreditScoreSystem.repository.CustomerRepository;
import com.example.CreditScoreSystem.service.CreditInquiriesService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreditInquiriesServiceImpl implements CreditInquiriesService {
    private static final Logger logger = LoggerFactory.getLogger(CreditInquiriesServiceImpl.class);
    private final CreditInquiriesRepository creditInquiries;
    private final CustomerRepository customerRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void applyCredit(int customerId, int categoryId) {
        logger.info("Applying new credit query: {}", customerId);
        var creditScore = calculateCreditScore(customerId);
        CreditInquiries creditInquiry = new CreditInquiries();
        creditInquiry.setCategory(categoryRepository.findById(categoryId).get());
        creditInquiry.setCustomer(customerRepository.findById(customerId).get());
        if(creditScore >= 600 && creditScore <= 850) {
            creditInquiry.setApproved(true);
        } else {
            creditInquiry.setApproved(false);
        }
        creditInquiries.save(creditInquiry);
        logger.info("Credit query added with ID: {}", creditInquiry.getId());

    }

    @Override
    public Integer getCreditScore(int customerId) {
        logger.info("Fetching credit score for customer ID: {}", customerId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        logger.info("Found {} credit score", customer.getCreditScore());
        return customer.getCreditScore();
    }

    @Override
    public void updateCreditScore(int customerId, int score) {
        logger.info("Updating customer's credit score with ID: {}", customerId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    logger.error("Customer with ID {} not found", customerId);
                    return new RuntimeException("Customer not found");
                });
        customer.setCreditScore(score);
        customerRepository.save(customer);
        logger.info("Customer's credit score with ID {} updated successfully", customerId);
    }

    @Async
    public Integer calculateCreditScore(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        var incomeFactor = (customer.getIncome() / 2000) * 50;
        var limitFactor = (1 - customer.getUsedLimit() / customer.getCreditLimit()) * 100;
        var latePaymentFactor = -(customer.getCustomerPayments()
                .stream()
                .mapToInt(CustomerPayments::getLatePaymentDay)
                .sum() * 2);
        var inquiryFactor = -(customer.getCustomerPayments()
                .stream()
                .mapToInt(CustomerPayments::getLatePaymentDay)
                .sum() * 10);
        var ageFactor = (customer.getAge() / 100) * 50;
        var creditScore = 850 + incomeFactor + limitFactor + inquiryFactor + latePaymentFactor + ageFactor;
        customer.setCreditScore((int) creditScore);
        customerRepository.save(customer);
        return (int) creditScore;
    }


}
