package com.solucao.transacoes.infraestrutura.adaptador.controladorrest;

import com.solucao.transacoes.dominio.entidade.Account;
import com.solucao.transacoes.dominio.porta.AccountUseCasePort;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jpaentity.AccountJpaEntity;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jparepository.AccountRepository;
import com.solucao.transacoes.infraestrutura.adaptador.controladorrest.resposta.AccountResponse;
import com.solucao.transacoes.infraestrutura.exceptionhandler.ResourceNotFoundException;
import com.solucao.transacoes.infraestrutura.mapeador.AccountMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class AccountController {

    private final AccountUseCasePort accountUseCasePort;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountController(AccountUseCasePort accountUseCasePort,
                             AccountRepository accountRepository,
                             AccountMapper accountMapper){
        this.accountUseCasePort = accountUseCasePort;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountResponse> getAccontById(@PathVariable(value = "accountId")  Long accountId){

        AccountJpaEntity accountJpaEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Account with id = "
                        + accountId));

        return ResponseEntity.ok(accountMapper.fromEntityToResponse(accountJpaEntity));
    }

    /*
    TODO: verificar boa pratica para RequestBody:
     deveria usar a entidade de dominio ou um Request?
     como fazer a validacao do dominio funcionar com
     jakarta.validation.Valid?
     como nao deixar validacao redundante?
     */
    @PostMapping("/accounts")
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody Account account)
            throws Exception {

        accountUseCasePort.createAccount(account);

        AccountJpaEntity accountJpaEntity =
                accountRepository.findByDocumentNumber(account.getDocumentNumber()).get();

        return new ResponseEntity<AccountResponse>
                (accountMapper.fromEntityToResponse(accountJpaEntity), HttpStatus.CREATED);
    }
}
