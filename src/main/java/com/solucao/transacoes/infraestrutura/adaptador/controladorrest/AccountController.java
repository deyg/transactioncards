package com.solucao.transacoes.infraestrutura.adaptador.controladorrest;

import com.solucao.transacoes.dominio.porta.AccountUseCasePort;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jpaentity.AccountJpaEntity;
import com.solucao.transacoes.infraestrutura.adaptador.banco.jparepository.AccountRepository;
import com.solucao.transacoes.infraestrutura.adaptador.controladorrest.requisicao.AccountRequest;
import com.solucao.transacoes.infraestrutura.adaptador.controladorrest.resposta.AccountResponse;
import com.solucao.transacoes.infraestrutura.exceptionhandler.ResourceNotFoundException;
import com.solucao.transacoes.infraestrutura.mapeador.AccountMapper;
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

    @PostMapping("/accounts")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest)
            throws Exception {

        accountUseCasePort.createAccount(accountMapper.fromRequestToDomain(accountRequest));
        return new ResponseEntity<AccountResponse>(accountMapper.fromEntityToResponse(
                accountRepository.findByDocumentNumber(
                        accountRequest.getDocumentNumber()).get()), HttpStatus.CREATED);
    }
}
