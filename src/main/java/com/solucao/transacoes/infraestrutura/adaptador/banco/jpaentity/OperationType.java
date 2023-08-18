package com.solucao.transacoes.infraestrutura.adaptador.banco.jpaentity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operation_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationType {

    @Id
    @Column(name = "operation_type_id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "operation_classifier")
    private String operationClassifier;
}
