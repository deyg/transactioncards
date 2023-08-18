package com.solucao.transacoes.dominio.porta;

import com.solucao.transacoes.dominio.entidade.Account;

public interface AccountUseCasePort {
    Account createAccount(Account account);
}
