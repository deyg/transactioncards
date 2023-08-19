package com.solucao.transacoes.config;

import com.solucao.transacoes.dominio.aplicacao.AccountUseCase;
import com.solucao.transacoes.dominio.porta.AccountRepositoryPort;
import com.solucao.transacoes.dominio.porta.AccountUseCasePort;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public AccountUseCasePort accountUseCasePort(
            AccountRepositoryPort accountRepositoryPort,
            MessageSource message){
        return new AccountUseCase(accountRepositoryPort, message);
    }
}
