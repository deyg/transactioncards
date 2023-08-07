package com.solucao.transacoes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    @JsonProperty("account_id")
    @NotNull(message = "The account_id should not be null")
    @Positive(message = "The account_id should be positive")
    private Long accountId;

    @JsonProperty("operation_type_id")
    @NotNull(message = "The operation_type_id should not be null")
    @Positive(message = "The operation_type_id should be positive")
    private Long operationTypeId;

    @JsonProperty("amount")
    @NotNull(message = "The amount should not be null")
    @Positive(message = "The amount should be positive")
    private BigDecimal amount;

}
