package com.solucao.transacoes.dominio.porta;

import com.solucao.transacoes.dominio.entidade.Account;

public interface AccountRepositoryPort {
    Account saveAccount(Account account);
    boolean exists(String documentNumber);
}
