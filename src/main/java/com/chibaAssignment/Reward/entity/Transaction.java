package com.chibaAssignment.Reward.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Data
public class Transaction {
    private String id ;
    @NonNull
    private BigDecimal purchase;
    private int reward;

    public Transaction(BigDecimal transactionAmount){
        this.id = UUID.randomUUID().toString();
        this.purchase = transactionAmount;
        this.reward = 0;
    }

    public Transaction() {
        this.id = UUID.randomUUID().toString();
    }
}
