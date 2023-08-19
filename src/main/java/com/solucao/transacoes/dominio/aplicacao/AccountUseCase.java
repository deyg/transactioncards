package com.solucao.transacoes.dominio.aplicacao;

import com.solucao.transacoes.dominio.businessexception.AccountDuplicatedBusinessException;
import com.solucao.transacoes.dominio.entidade.Account;
import com.solucao.transacoes.dominio.porta.AccountRepositoryPort;
import com.solucao.transacoes.dominio.porta.AccountUseCasePort;

/*
TODO: Criar Portas e Adptadores para message
 retirar dependencia daqui:  org.springframework.context.MessageSource;
 Caso de Uso nao devem depender de Framworks
 */
import org.springframework.context.MessageSource;

public class AccountUseCase implements AccountUseCasePort {

    private final MessageSource message;
    private final AccountRepositoryPort accountRepositoryPort;

    public AccountUseCase(AccountRepositoryPort accountRepositoryPort,
                          MessageSource message) {
        this.accountRepositoryPort = accountRepositoryPort;
        this.message = message;
    }

    @Override
    public Account createAccount(Account account) {

        if(accountRepositoryPort.exists(account.getDocumentNumber()))
            throw new AccountDuplicatedBusinessException(
                    message.getMessage("duplicate.account",null,null));

        return accountRepositoryPort.saveAccount(account);
    }
}

