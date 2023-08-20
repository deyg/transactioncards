package com.solucao.transacoes.infraestrutura.adaptador.controladorrest.resposta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
public class AccountResponse {

    @JsonProperty("account_id")
    private Long id;

    @JsonProperty("document_number")
    private String documentNumber;

    @JsonProperty("available_creditLimit")
    private BigDecimal availableCreditLimit;
}
