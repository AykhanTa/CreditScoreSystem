package com.example.CreditScoreSystem.controller;

import com.example.CreditScoreSystem.service.CreditInquiriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit")
@RequiredArgsConstructor
public class CreditInquiriController {
    private final CreditInquiriesService creditInquiries;

    @PostMapping("/apply")
    public String applyCreditInquiries(@RequestParam int customerId, @RequestParam int categoryId) {
        creditInquiries.applyCredit(customerId, categoryId);
        return "Credit Score hesablanir.Biraz sonra yeniden yoxlayin.";
    }

    @GetMapping("{customerId}")
    public String  getCreditScore(@PathVariable int customerId) {
        return  "Credit score is:"+creditInquiries.getCreditScore(customerId) ;
    }

    @PostMapping("/update/{customerId}")
    public String updateCreditScore(@PathVariable int customerId, @RequestParam int score) {
        creditInquiries.updateCreditScore(customerId, score);
        return "Credit Score is successfully updated:"+creditInquiries.getCreditScore(customerId) ;
    }


}
