package com.chibaAssignment.Reward.service;

import com.chibaAssignment.Reward.component.ConfigProperties;
import com.chibaAssignment.Reward.entity.Transaction;
import com.chibaAssignment.Reward.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class RewardServiceImpl implements RewardService{
    @Autowired
    ConfigProperties config;
    @Autowired
    RewardRepository repo;
    @Override
    public int calculateRewardPoints(BigDecimal amount) {

        int rvPoint = 0;
        int limit1 = Integer.parseInt(config.getConfigValue("limit1"));
        int limit2 = Integer.parseInt(config.getConfigValue("limit2"));
        int multi1 = Integer.parseInt(config.getConfigValue("multi1"));
        int multi2 = Integer.parseInt(config.getConfigValue("multi2"));

        amount = amount.setScale(0, RoundingMode.HALF_UP);
        if(amount.intValue() <= limit1 )
            return rvPoint;
        if(amount.intValue() > limit1 && amount.intValue() <=limit2)
            rvPoint = amount.intValue() % limit1 * multi1 ;
        if(amount.intValue() >limit2)
            rvPoint = (amount.intValue() - limit2 ) * multi2 + limit1;

        return rvPoint;
    }

    @Override
    public void addTransaction(Transaction transaction) {

        transaction.setReward(calculateRewardPoints(transaction.getPurchase()));
        repo.addTransaction(transaction);
    }

    @Override
    public List<Transaction> listTransactions() {
        return repo.listTransactions();
    }

    @Override
    public List<Transaction> listLimitedTransactions(int limit) {
        return repo.getLimitedTransaction(limit);
    }

    public void fakeData(){
        List<Transaction> list = new ArrayList<>();
        Transaction t1 = new Transaction(new BigDecimal(50));
        t1.setReward(calculateRewardPoints(t1.getPurchase()));
        Transaction t2 = new Transaction(new BigDecimal(120));
        t2.setReward(calculateRewardPoints(t2.getPurchase()));
        Transaction t3 = new Transaction(new BigDecimal(75));
        t3.setReward(calculateRewardPoints(t3.getPurchase()));

        list.add(t1);
        list.add(t2);
        list.add(t3);
        repo.setTransactions(list);

    }

}
