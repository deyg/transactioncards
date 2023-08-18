package com.solucao.transacoes.infraestrutura.adaptador.banco.jparepository;

import com.solucao.transacoes.infraestrutura.adaptador.banco.jpaentity.AccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountJpaEntity, Long> {
    Optional<AccountJpaEntity> findByDocumentNumber(String documentNumber);
}



