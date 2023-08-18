package com.solucao.transacoes.dominio.entidade;

/*
TODO: Objetos response e request devem ser criado na
 infra para mapear o Body.
 A dependencia JsonProperty deve ser movida para infra
 */
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Account {


    /*
    TODO: Dominio nao deve possuir id
     */
    @JsonProperty("account_id")
    private Long id;

    @JsonProperty("document_number")
    @NotEmpty(message = "The document number should not be empty")
    @NotBlank(message = "The document number should not be a blank space")
    @Size(max = 11, min = 11, message = "Invalid Document Number, Size should be 11")
    @Pattern(regexp = "^[0-9]+$", message = "The document number should only contain numbers")
    private String documentNumber;

    @JsonProperty("available_creditLimit")
    private BigDecimal availableCreditLimit;


    public Account(String documentNumber, BigDecimal availableCreditLimit) {
        this.documentNumber = documentNumber;
        this.availableCreditLimit = availableCreditLimit;
    }
}
