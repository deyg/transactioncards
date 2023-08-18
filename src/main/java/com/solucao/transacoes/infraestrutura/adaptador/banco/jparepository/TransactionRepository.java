package com.solucao.transacoes.infraestrutura.adaptador.banco.jparepository;

import com.solucao.transacoes.infraestrutura.adaptador.banco.jpaentity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}



