package com.solucao.transacoes.integration;

import com.solucao.transacoes.TransacoesApplication;
import com.solucao.transacoes.dominio.aplicacao.AccountUseCase;
import com.solucao.transacoes.dominio.businessexception.AccountDuplicatedBusinessException;
import com.solucao.transacoes.dominio.entidade.Account;
import com.solucao.transacoes.dominio.porta.AccountRepositoryPort;
import com.solucao.transacoes.dominio.porta.AccountUseCasePort;
import com.solucao.transacoes.infraestrutura.adaptador.banco.AccountRepositoryAdapter;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jparepository.AccountRepository;
import com.solucao.transacoes.infraestrutura.mapeador.AccountMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.math.BigDecimal;

@SpringBootTest
public class AccountIntegrationTest {

    private static AccountUseCasePort accountUseCasePort;

    @Autowired
    private static AccountRepository accountRepository;

    @Autowired
    private static AccountMapper accountMapper;

    private static Account zeroAccountDto;
    private static Logger LOG = LoggerFactory
            .getLogger(TransacoesApplication.class);

    @Autowired
    private static MessageSource message;


    @BeforeAll
    public static void init(){

        AccountRepositoryPort accountRepositoryPort = new AccountRepositoryAdapter(accountRepository, accountMapper);
        accountUseCasePort = new AccountUseCase(accountRepositoryPort, message);
        zeroAccountDto = new Account("00000000000", new BigDecimal("5000"));
    }

//    @Test
//    void deveria_excluir_conta_zero_se_existir_e_criar_conta_zero(){
//
//        var account = accountRepository.findByDocumentNumber(zeroAccountDto.getDocumentNumber())
//                .orElse(null);
//        if (account !=null) accountRepository.delete(account);
//        var zeroAccount = accountUseCasePort.createAccount(zeroAccountDto);
//
//        Assertions.assertEquals(zeroAccountDto.getDocumentNumber(), zeroAccount.getDocumentNumber());
//    }

//    @Test
//    void deveria_lancar_exception_ao_tentar_persistir_conta_mais_de_uma_vez(){
//
//        AccountDuplicatedBusinessException exception = Assertions.assertThrows(AccountDuplicatedBusinessException.class, () -> {
//            accountUseCasePort.createAccount(zeroAccountDto);
//            accountUseCasePort.createAccount(zeroAccountDto);
//        });
//
//        String expectedMessage = message.getMessage("duplicate.account",null,null);
//        String actualMessage = exception.getMessage();
//
//        Assertions.assertTrue(actualMessage.contains(expectedMessage));
//
//    }
}



