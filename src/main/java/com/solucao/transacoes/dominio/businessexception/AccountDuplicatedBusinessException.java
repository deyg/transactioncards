package com.solucao.transacoes.dominio.businessexception;

public class AccountDuplicatedBusinessException extends RuntimeException {
    public AccountDuplicatedBusinessException(String errorMessage){
        super(errorMessage);
    }
}
