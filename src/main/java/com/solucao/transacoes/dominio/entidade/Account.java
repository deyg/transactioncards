package com.solucao.transacoes.dominio.entidade;

import com.solucao.transacoes.dominio.businessexception.BusinessException;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Account {

    private String documentNumber;
    private BigDecimal availableCreditLimit;

    private Account(){}

    public Account(String documentNumber, BigDecimal availableCreditLimit) {

        if(documentNumber == null)
            throw new BusinessException("O número do documento não deveria ser null.");

        if(documentNumber.isEmpty() || documentNumber.isBlank())
            throw new BusinessException("O número do documento não deveria ser vazio.");

        if(!documentNumber.matches("^[0-9]+$"))
            throw new BusinessException("O número do documento deveria conter apenas dígitos.");

        if(documentNumber.length() != 11)
            throw new BusinessException("O número do documento deveria conter 11 dígitos.");

        this.documentNumber = documentNumber;
        this.availableCreditLimit = availableCreditLimit;
    }
}
