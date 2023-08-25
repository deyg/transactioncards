package com.solucao.transacoes.infraestrutura.adaptador.controladorrest.requisicao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccountRequest {

    @JsonProperty("document_number")
    private String documentNumber;

    @JsonProperty("available_creditLimit")
    private BigDecimal availableCreditLimit;

    private AccountRequest(){}

    public AccountRequest(String documentNumber, BigDecimal availableCreditLimit) {

        this.documentNumber = documentNumber;
        this.availableCreditLimit = availableCreditLimit;
    }
}
