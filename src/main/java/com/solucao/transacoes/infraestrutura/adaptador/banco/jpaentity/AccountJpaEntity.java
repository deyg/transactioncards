package com.solucao.transacoes.infraestrutura.adaptador.banco.jpaentity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "document_number", unique = true, nullable = false, length = 11)
    private String documentNumber;

    @Column(name = "available_creditLimit")
    private BigDecimal availableCreditLimit;

}
