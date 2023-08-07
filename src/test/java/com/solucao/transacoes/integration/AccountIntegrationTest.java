package com.solucao.transacoes.integration;

import com.solucao.transacoes.TransacoesApplication;
import com.solucao.transacoes.exception.businessexception.AccountDuplicatedBusinessException;
import com.solucao.transacoes.dto.AccountDto;
import com.solucao.transacoes.repository.AccountRepository;
import com.solucao.transacoes.service.AccountService;
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

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    private static AccountDto zeroAccountDto;

    private static Logger LOG = LoggerFactory
            .getLogger(TransacoesApplication.class);

    @Autowired
    private MessageSource message;


    @BeforeAll
    public static void init(){

         zeroAccountDto = new AccountDto(null,"00000000000", new BigDecimal("5000"));
    }

    @Test
    void deveria_excluir_conta_zero_se_existir_e_criar_conta_zero(){

        var account = accountRepository.findByDocumentNumber(zeroAccountDto.getDocumentNumber());
        if (account.isPresent()) accountRepository.delete(account.get());
        var zeroAccount = accountService.createAccount(zeroAccountDto);

        Assertions.assertEquals(zeroAccountDto.getDocumentNumber(), zeroAccount.getDocumentNumber());
    }

    @Test
    void deveria_lancar_exception_ao_tentar_persistir_conta_mais_de_uma_vez(){

        AccountDuplicatedBusinessException exception = Assertions.assertThrows(AccountDuplicatedBusinessException.class, () -> {
            accountService.createAccount(zeroAccountDto);
            accountService.createAccount(zeroAccountDto);
        });

        String expectedMessage = message.getMessage("duplicate.account",null,null);
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));

    }
}



