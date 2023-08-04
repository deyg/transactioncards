package com.solucao.transacoes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private Long id;

    @ManyToOne
    private Account account;

    @ManyToOne
    private OperationType operationType;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "event_date")
    private Date eventDate;

}
