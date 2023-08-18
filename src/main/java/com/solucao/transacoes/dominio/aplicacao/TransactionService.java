package com.solucao.transacoes.dominio.aplicacao;

import com.solucao.transacoes.dominio.dto.TransactionDto;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jpaentity.AccountJpaEntity;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jpaentity.Transaction;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jparepository.AccountRepository;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jparepository.OperationTypeRepository;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jparepository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final OperationTypeRepository operationTypeRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              OperationTypeRepository operationTypeRepository,
                              AccountRepository accountRepository) {

        this.transactionRepository = transactionRepository;
        this.operationTypeRepository = operationTypeRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Transaction createTransaction(TransactionDto transactionDto) throws RuntimeException {

        var account = accountRepository.findById(transactionDto.getAccountId()).get();
        var operationType = operationTypeRepository.findById(transactionDto.getOperationTypeId()).get();
        var normalizedAmount = operationType.getOperationClassifier().equals("-")?
                transactionDto.getAmount().abs().negate() : transactionDto.getAmount().abs();

        Transaction transaction = Transaction.builder()
                .account(account)
                .operationType(operationType)
                .amount(normalizedAmount)
                .eventDate(new Date())
                .build();

        accountRepository.save(updateAvailableCreditLimit(transaction, account));
        //if (true) throw new RuntimeException("Sera q deu roll back? Claro que sim. Fui eu que fiz.");
        return transactionRepository.save(transaction);
    }

    //TODO: deve ser um regra da service AccountService - metodo privado nao e bom para teste
    private AccountJpaEntity updateAvailableCreditLimit(Transaction transaction, AccountJpaEntity account) throws RuntimeException {

        if(transaction.getAmount().abs().compareTo(account.getAvailableCreditLimit()) == 1
                && transaction.getOperationType().getOperationClassifier().equals("-")){
                throw new RuntimeException("Limit exceed");
        }
        account.setAvailableCreditLimit(account.getAvailableCreditLimit().add(
                transaction.getAmount()));

        return  account;
    }

}
