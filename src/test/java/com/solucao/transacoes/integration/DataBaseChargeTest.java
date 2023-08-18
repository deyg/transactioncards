package com.solucao.transacoes.integration;

import com.solucao.transacoes.infraestrutura.adaptador.banco.jpaentity.AccountJpaEntity;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jpaentity.OperationType;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jpaentity.Transaction;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jparepository.AccountRepository;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jparepository.OperationTypeRepository;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jparepository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
public class DataBaseChargeTest {

    @Autowired
    private  AccountRepository accountRepository;

    @Autowired
    private  OperationTypeRepository operationTypeRepository;

    @Autowired
    private  TransactionRepository transactionRepository;

    String documentNumber = "00000000999";

    @BeforeAll
    public static void init(){
    }

    @EventListener(ContextRefreshedEvent.class)
     void populaTabelaOperationType(){

        if(operationTypeRepository.findAll().isEmpty()){

            operationTypeRepository.save(new OperationType(1L,"Compra a Vista","-"));
            operationTypeRepository.save(new OperationType(2L,"Compra Parcelada","-"));
            operationTypeRepository.save(new OperationType(3L,"Saque","-"));
            operationTypeRepository.save(new OperationType(4L,"Pagamento","+"));
        }
    }

    void criarConta(){

        if(!accountRepository.findByDocumentNumber(documentNumber).isPresent())
            accountRepository.save(new AccountJpaEntity(null,documentNumber, new BigDecimal("5000")));

        var account = accountRepository.findByDocumentNumber(documentNumber).get();
    }

    @Test
    void deveria_ExistirQuatroOperationType(){

        populaTabelaOperationType();
        Assertions.assertTrue(operationTypeRepository.findAll().size() == 4);
    }

    @Test
    void deveria_CriarAConta_00000000999(){

        criarConta();
        var account = accountRepository.findByDocumentNumber(documentNumber).get();
        Assertions.assertTrue(documentNumber.equals(account.getDocumentNumber()));
    }

    @Test
    void deveria_CriarTransacoesParaAConta00000000999(){

        populaTabelaOperationType();
        criarConta();

        var operationType = operationTypeRepository.findById(4L).get();
        var account = accountRepository.findByDocumentNumber(documentNumber).get();

        var transaction = transactionRepository.save(
                new Transaction(null, account, operationType, new BigDecimal("-100.97"), new Date()));

        Assertions.assertTrue(transactionRepository.findById(transaction.getId()).isPresent());

    }

}


