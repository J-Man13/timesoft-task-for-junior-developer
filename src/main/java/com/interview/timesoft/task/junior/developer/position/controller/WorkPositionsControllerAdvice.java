package com.interview.timesoft.task.junior.developer.position.controller;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(basePackageClasses = WorkPositionsController.class)
public class WorkPositionsControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public TezBazarHandling handle(RuntimeException runtimeException){
        runtimeException.printStackTrace();
        return new TezBazarHandling(runtimeException.getMessage());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TezBazarHandling{
        @JsonProperty("message")
        private String message;
    }
}
