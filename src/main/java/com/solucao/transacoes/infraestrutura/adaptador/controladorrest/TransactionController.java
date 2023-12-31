package com.solucao.transacoes.infraestrutura.adaptador.controladorrest;

import com.solucao.transacoes.dominio.dto.TransactionDto;
import com.solucao.transacoes.dominio.aplicacao.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<TransactionDto> createTransaction(@Valid @RequestBody TransactionDto transactionDto)
            throws Exception {

        var transaction = transactionService.createTransaction(transactionDto);
        transactionDto.setAmount(transaction.getAmount());
        return new ResponseEntity<>(transactionDto, HttpStatus.CREATED);

    }

}
