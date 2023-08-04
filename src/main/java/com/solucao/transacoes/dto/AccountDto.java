package com.solucao.transacoes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    @JsonProperty("account_id")
    private Long id;

    @JsonProperty("document_number")
    @NotEmpty(message = "The document number should not be empty")
    @NotBlank(message = "The document number should not be a blank space")
    @Size(max = 11, min = 11, message = "Invalid Document Number, Size should be 11")
    @Pattern(regexp = "^[0-9]+$", message = "The document number should only contain numbers")
    private String documentNumber;

}
