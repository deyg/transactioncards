package com.solucao.transacoes.infraestrutura.adaptador.banco;

import com.solucao.transacoes.dominio.entidade.Account;
import com.solucao.transacoes.dominio.porta.AccountRepositoryPort;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jparepository.AccountRepository;
import com.solucao.transacoes.infraestrutura.mapeador.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRepositoryAdapter implements AccountRepositoryPort{

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountRepositoryAdapter(AccountRepository accountRepository, AccountMapper accountMapper){
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account saveAccount(Account account){
        return accountMapper.toDomain(accountRepository.save(accountMapper.toEntity(account)));
    }

    @Override
    public boolean exists(String documentNumber) {
        return accountRepository.findByDocumentNumber(documentNumber).isPresent();
    }
}
