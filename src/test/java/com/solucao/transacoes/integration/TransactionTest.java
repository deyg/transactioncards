package com.solucao.transacoes.integration;

import com.solucao.transacoes.dto.AccountDto;
import com.solucao.transacoes.dto.TransactionDto;
import com.solucao.transacoes.repository.AccountRepository;
import com.solucao.transacoes.service.AccountService;
import com.solucao.transacoes.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class TransactionTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    void deveriaPersisitirUmaTransacaoQuantiaNegativa(){

        var accountDto = new AccountDto(null,"99000000099");
        var account = accountRepository.findByDocumentNumber(accountDto.getDocumentNumber());
        if (!account.isPresent()) accountService.createAccount(accountDto);
        var returnedAccount = accountRepository.findByDocumentNumber(accountDto.getDocumentNumber());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(new BigDecimal("500000"));
        transactionDto.setAccountId(returnedAccount.get().getId());
        transactionDto.setOperationTypeId(1L);

        var transaction = transactionService.createTransaction(transactionDto);

        Assertions.assertTrue(transaction.getAmount().signum() == -1);
    }

    @Test
    void deveriaPersisitirUmaTransacaoQuantiaPositiva(){

        var accountDto = new AccountDto(null,"99000000099");
        var account = accountRepository.findByDocumentNumber(accountDto.getDocumentNumber());
        if (!account.isPresent()) accountService.createAccount(accountDto);
        var returnedAccount = accountRepository.findByDocumentNumber(accountDto.getDocumentNumber());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(new BigDecimal("-700"));
        transactionDto.setAccountId(returnedAccount.get().getId());
        transactionDto.setOperationTypeId(4L);

        var transaction = transactionService.createTransaction(transactionDto);

        Assertions.assertTrue(transaction.getAmount().signum() == +1);
    }
}
