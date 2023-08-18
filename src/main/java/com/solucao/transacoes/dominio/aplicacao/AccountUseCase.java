package com.solucao.transacoes.dominio.aplicacao;

import com.solucao.transacoes.dominio.businessexception.AccountDuplicatedBusinessException;
import com.solucao.transacoes.dominio.entidade.Account;
import com.solucao.transacoes.dominio.porta.AccountRepositoryPort;
import com.solucao.transacoes.dominio.porta.AccountUseCasePort;

//TODO: estas dependencias podem sair daqui. Criar um Configuration
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class AccountUseCase implements AccountUseCasePort {

    @Autowired
    private MessageSource message;
    private final AccountRepositoryPort accountRepositoryPort;

    @Autowired
    public AccountUseCase(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public Account createAccount(Account account) {
        if(accountRepositoryPort.exists(account.getDocumentNumber()))
            throw new AccountDuplicatedBusinessException(message.getMessage("duplicate.account",null,null));
        return accountRepositoryPort.saveAccount(account);
    }
}

