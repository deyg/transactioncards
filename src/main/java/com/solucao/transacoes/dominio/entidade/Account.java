package com.solucao.transacoes.dominio.entidade;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    /*
     @JsonProperty pode ser colocado em um Request
     ver o impacto nas validacoes.
     */

    @NotEmpty(message = "The document number should not be empty")
    @NotBlank(message = "The document number should not be a blank space")
    @Size(max = 11, min = 11, message = "Invalid Document Number, Size should be 11")
    @Pattern(regexp = "^[0-9]+$", message = "The document number should only contain numbers")
    @JsonProperty("document_number")
    private String documentNumber;

    @JsonProperty("available_creditLimit")
    private BigDecimal availableCreditLimit;


}
