package com.chibaAssignment.Reward.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RewardAmountException {
    @ExceptionHandler(value = InvalidAmountException.class)
    public ResponseEntity<Object> exception(InvalidAmountException exception) {
        return new ResponseEntity<>("Invalid amount", HttpStatus.BAD_REQUEST);
    }
}
