package com.solucao.transacoes.integration;

import com.solucao.transacoes.dominio.dto.TransactionDto;
import com.solucao.transacoes.dominio.entidade.Account;
import com.solucao.transacoes.dominio.porta.AccountUseCasePort;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jparepository.AccountRepository;
import com.solucao.transacoes.dominio.aplicacao.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class TransactionTest {


    private TransactionService transactionService;
    private AccountRepository accountRepository;
    private final AccountUseCasePort accountUseCasePort;

    @Autowired
    public TransactionTest(TransactionService transactionService, AccountRepository accountRepository,
                           AccountUseCasePort accountUseCasePort) {
        this.transactionService = transactionService;
        this.accountRepository = accountRepository;
        this.accountUseCasePort = accountUseCasePort;
    }

    @Test
    void deveriaPersisitirUmaTransacaoQuantiaNegativa(){

        var accountDto = new Account("99000000099", new BigDecimal("5000.00"));
        var account = accountRepository.findByDocumentNumber(accountDto.getDocumentNumber());
        if (!account.isPresent()) accountUseCasePort.createAccount(accountDto);
        var returnedAccount = accountRepository.findByDocumentNumber(accountDto.getDocumentNumber());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(new BigDecimal("1.12"));
        transactionDto.setAccountId(returnedAccount.get().getId());
        transactionDto.setOperationTypeId(1L);

        var transaction = transactionService.createTransaction(transactionDto);

        Assertions.assertTrue(transaction.getAmount().signum() == -1);
    }

    @Test
    void deveriaPersisitirUmaTransacaoQuantiaPositiva(){

        var accountDto = new Account("99000000099", new BigDecimal("5000.00"));
        var account = accountRepository.findByDocumentNumber(accountDto.getDocumentNumber())
                .orElse(null);
        if (account==null) accountUseCasePort.createAccount(accountDto);
        var returnedAccount = accountRepository.findByDocumentNumber(accountDto.getDocumentNumber());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(new BigDecimal("-700"));
        transactionDto.setAccountId(returnedAccount.get().getId());
        transactionDto.setOperationTypeId(4L);

        var transaction = transactionService.createTransaction(transactionDto);

        Assertions.assertTrue(transaction.getAmount().signum() == +1);
    }
}
