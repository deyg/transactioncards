package com.solucao.transacoes.dominio.businessexception;

public class BusinessException extends RuntimeException {
    public BusinessException(String errorMessage){
        super(errorMessage);
    }
}
