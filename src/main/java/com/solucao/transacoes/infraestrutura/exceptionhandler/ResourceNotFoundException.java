package com.solucao.transacoes.infraestrutura.exceptionhandler;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}

