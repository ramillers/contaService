package com.example.contaService.exception;

public class ContaNaoExistenteException extends RuntimeException {
    public ContaNaoExistenteException(String message) {
        super(message);
    }
}