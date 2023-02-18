package com.chibaAssignment.Reward.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;


public class InvalidAmountException extends  RuntimeException{
    private static final long serialVersionUID = -9079454849611061074L;

    public  InvalidAmountException(String message){
        super(message);
    }
}
