package com.solucao.transacoes.service;

import com.solucao.transacoes.dto.TransactionDto;
import com.solucao.transacoes.model.Transaction;
import com.solucao.transacoes.repository.AccountRepository;
import com.solucao.transacoes.repository.OperationTypeRepository;
import com.solucao.transacoes.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final OperationTypeRepository operationTypeRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, OperationTypeRepository operationTypeRepository,
                              AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.operationTypeRepository = operationTypeRepository;
        this.accountRepository = accountRepository;
    }

    public Transaction createTransaction(TransactionDto transactionDto){

        var account = accountRepository.findById(transactionDto.getAccountId()).get();
        var operationType = operationTypeRepository.findById(transactionDto.getOperationTypeId()).get();
        var normalizedAmount = normalizeAmountByOperationType(transactionDto.getAmount(),
                operationType.getOperationClassifier());

        Transaction transaction = Transaction.builder()
                .account(account)
                .operationType(operationType)
                .amount(normalizedAmount)
                .eventDate(new Date())
                .build();

        return transactionRepository.save(transaction);
    }

    private BigDecimal normalizeAmountByOperationType(BigDecimal amount, String operationClassifier){

        if(operationClassifier.equals("-")) return amount.abs().negate();
        else return amount.abs();
    }

}
