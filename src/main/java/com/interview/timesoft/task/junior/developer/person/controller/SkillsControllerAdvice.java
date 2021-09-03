package com.interview.timesoft.task.junior.developer.person.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(basePackageClasses = SkillsController.class)
public class SkillsControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public TezBazarResponse handle(RuntimeException runtimeException){
        runtimeException.printStackTrace();
        return new TezBazarResponse(runtimeException.getMessage());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TezBazarResponse{
        @JsonProperty("message")
        private String message;
    }
}