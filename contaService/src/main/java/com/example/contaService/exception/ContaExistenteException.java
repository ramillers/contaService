package com.example.contaService.exception;

public class ContaExistenteException extends RuntimeException{
    public ContaExistenteException(String message) {
        super(message);
    }

}