package com.solucao.transacoes.util;

import com.solucao.transacoes.TransacoesApplication;
import com.solucao.transacoes.model.OperationType;
import com.solucao.transacoes.repository.OperationTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class Start implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private OperationTypeRepository operationTypeRepository;

    private static Logger LOG = LoggerFactory
            .getLogger(TransacoesApplication.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        LOG.info("--> started @EventListener(ContextRefreshedEvent.class)");

        if(operationTypeRepository.findAll().isEmpty()){

            operationTypeRepository.save(new OperationType(1L,"Compra a Vista","-"));
            operationTypeRepository.save(new OperationType(2L,"Compra Parcelada","-"));
            operationTypeRepository.save(new OperationType(3L,"Saque","-"));
            operationTypeRepository.save(new OperationType(4L,"Pagamento","+"));
        }

    }
}


