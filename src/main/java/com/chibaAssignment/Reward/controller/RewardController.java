package com.chibaAssignment.Reward.controller;

import com.chibaAssignment.Reward.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    @Autowired
    RewardService service;
    @GetMapping("/")
    public String  getHello(){
        return "hi Chiba";
    }

    @GetMapping("/{amount}")
    public int rewardAmount(@PathVariable String amount){
        float givenAmount =0;
        int checkedValue =0;
        int totalPoint =0;
        try{
            amount = amount.trim();
            givenAmount = Float.parseFloat(amount);
            checkedValue = Math.round(givenAmount);
            totalPoint = service.totalRewards(checkedValue);
        }catch (Exception e){
            throw new InvalidAmountException();
        }
        return totalPoint;
    }
}
