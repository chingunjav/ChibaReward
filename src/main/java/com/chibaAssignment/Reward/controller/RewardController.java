package com.chibaAssignment.Reward.controller;

import com.chibaAssignment.Reward.entity.Transaction;
import com.chibaAssignment.Reward.service.RewardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reward")
public class RewardController {

    @Autowired
    RewardServiceImpl service;

    @GetMapping("/")
    public String hello(){
        service.fakeData();
        return "Initial fake data added successfully";
    }
    @GetMapping("/list")
    public ResponseEntity<List<Transaction>> getTransactions(){
        List<Transaction> list = service.listTransactions();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/limit/{amount}")
    public ResponseEntity<List<Transaction>> limitedTransaction(@PathVariable String amount){
        List<Transaction> list = new ArrayList<>();
        try {
            int checked = Integer.parseInt(amount);
            list = service.listLimitedTransactions(checked);
        }catch (Exception e ){
            throw  new InvalidAmountException("Invalid value!! must be integer");
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction){
        if(transaction.getPurchase().signum() < 0)
            throw new InvalidAmountException("transaction amount must be greater than 0.0");
        try{
            service.addTransaction(transaction);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

}
