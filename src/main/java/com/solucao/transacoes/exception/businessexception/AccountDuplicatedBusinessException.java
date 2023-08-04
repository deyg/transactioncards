package com.solucao.transacoes.exception.businessexception;

public class AccountDuplicatedBusinessException extends RuntimeException {
    public AccountDuplicatedBusinessException(String errorMessage){
        super(errorMessage);
    }
}
