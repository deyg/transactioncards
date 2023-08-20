package com.solucao.transacoes.infraestrutura.mapeador;

import com.solucao.transacoes.dominio.entidade.Account;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jpaentity.AccountJpaEntity;
import com.solucao.transacoes.infraestrutura.adaptador.controladorrest.resposta.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    AccountJpaEntity toEntity(final Account account);
    Account toDomain(final AccountJpaEntity accountJpaEntity);
    AccountResponse fromEntityToResponse (AccountJpaEntity entity);
}
