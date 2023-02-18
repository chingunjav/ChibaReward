package com.chibaAssignment.Reward.repository;

import com.chibaAssignment.Reward.entity.Transaction;
import com.chibaAssignment.Reward.service.RewardServiceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RewardRepository {

    private List<Transaction> transactions = new ArrayList<>();


    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }

    public List<Transaction> listTransactions(){
       return transactions;
    }
    public List<Transaction> getLimitedTransaction(int limit){
        return transactions.stream().filter(x->x.getPurchase().intValue()<=limit)
                .collect(Collectors.toList());
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
