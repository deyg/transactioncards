package com.solucao.transacoes.infraestrutura.adaptador.controladorrest;

import com.solucao.transacoes.dominio.entidade.Account;
import com.solucao.transacoes.dominio.porta.AccountUseCasePort;
import com.solucao.transacoes.infraestrutura.exceptionhandler.ResourceNotFoundException;
import com.solucao.transacoes.infraestrutura.mapeador.AccountMapper;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jpaentity.AccountJpaEntity;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jparepository.AccountRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class AccountController {

    private final AccountUseCasePort accountUseCasePort;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    private MessageSource message;

    @Autowired
    public AccountController(AccountUseCasePort accountUseCasePort,
                             AccountRepository accountRepository,
                             AccountMapper accountMapper){
        this.accountUseCasePort = accountUseCasePort;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }


    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Account> getAccontById(@PathVariable(value = "accountId")  Long accountId){

        AccountJpaEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Account with id = "
                        + accountId));

        //TODO: nao deveria usar o dominio. Usar response para mapear body
        return ResponseEntity.ok(accountMapper.toDomain(account));
    }

    //TODO: nao deveria usar o dominio. Usar request para mapear body
    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) throws Exception {

        var newAccount = accountUseCasePort.createAccount(account);

        //TODO: nao deveria usar o dominio. Usar reponse para mapear body
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

}
