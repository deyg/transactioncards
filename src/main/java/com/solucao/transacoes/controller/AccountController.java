package com.solucao.transacoes.controller;

import com.solucao.transacoes.dto.AccountDto;
import com.solucao.transacoes.exception.exceptionhandler.ResourceNotFoundException;
import com.solucao.transacoes.mapper.AccountMapper;
import com.solucao.transacoes.model.Account;
import com.solucao.transacoes.repository.AccountRepository;
import com.solucao.transacoes.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    private MessageSource message;

    public AccountController(AccountService accountService, AccountRepository accountRepository, AccountMapper accountMapper ){
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }


    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountDto> getAccontById(@PathVariable(value = "accountId")  Long accountId){

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Account with id = " + accountId));

        return ResponseEntity.ok(accountMapper.toDto(account));
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto accountDto) throws Exception {

        var newAccountDto = accountMapper.toDto(accountService.createAccount(accountDto));
        return new ResponseEntity<>(newAccountDto, HttpStatus.CREATED);

    }

}
