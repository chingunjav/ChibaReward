package com.chibaAssignment.Reward.service;

import com.chibaAssignment.Reward.component.ConfigProperties;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardService {
    @Autowired
    ConfigProperties config;
    public int totalRewards(int amount){
        int rvPoint = 0;
        int limit1 = Integer.parseInt(config.getConfigValue("limit1"));
        int limit2 = Integer.parseInt(config.getConfigValue("limit2"));
        int multi1 = Integer.parseInt(config.getConfigValue("multi1"));
        int multi2 = Integer.parseInt(config.getConfigValue("multi2"));

        if(amount <= limit1 )
            return rvPoint;
        if(amount > limit1 && amount <=limit2)
            rvPoint = amount % limit1 * multi1 ;
        if(amount >limit2)
            rvPoint = (amount - limit2 ) * multi2 + limit1;

        return rvPoint;
    }
}
