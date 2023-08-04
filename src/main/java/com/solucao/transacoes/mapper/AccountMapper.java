package com.solucao.transacoes.mapper;

import com.solucao.transacoes.dto.AccountDto;
import com.solucao.transacoes.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    Account toEntity(final AccountDto accountDto);
    AccountDto toDto(final Account account);
}
