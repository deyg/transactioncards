package com.solucao.transacoes.dominio.businessexception;

public class AccountDuplicatedBusinessException extends BusinessException {
    public AccountDuplicatedBusinessException(String errorMessage){
        super(errorMessage);
    }
}
