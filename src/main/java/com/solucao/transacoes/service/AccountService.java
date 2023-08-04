package com.solucao.transacoes.service;

import com.solucao.transacoes.exception.businessexception.AccountDuplicatedBusinessException;
import com.solucao.transacoes.dto.AccountDto;
import com.solucao.transacoes.mapper.AccountMapper;
import com.solucao.transacoes.model.Account;
import com.solucao.transacoes.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    private MessageSource message;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper){

        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public Account createAccount(AccountDto accountDto){

        if(!accountRepository.findByDocumentNumber(accountDto.getDocumentNumber()).isPresent()){
            return accountRepository.save(accountMapper.toEntity(accountDto));
        }else{
            throw new AccountDuplicatedBusinessException(message.getMessage("duplicate.account",null,null));
        }

    }

}
