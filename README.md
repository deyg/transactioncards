# transactioncards
Solucao para transacoes financeiras

Esta solucao trata-se de uma Rest Api para realizacao de transacoes de cartao de credito no contexto do desafio de programacao. A estruturacao do projeto tem como principios fundamentais: Simplicidade, Manutenibilidade e Testabilidade. Utiliza o conceito de 3 camadas sendo Pesistencia, Regras de Negocio e Apresentacao. 

Uma atencao especial foi dada ao tratamento de exceptions e visa dois objetivos: 1) fornecer mensagens de exceptions padronizadas e 2) evitar a exposicao de mensagens sensiveis do servidor de aplicacao. Para atender este objetivo uma modelagem de exception foi implementada de forma a centralizar o lancamento de todas as exceptions da aplicacao. Um mecanismo muito eficiente para isto foi disponibilizado pelo framework spring.

Para executar a aplicao se faz necessario uma instancia do banco de dados Mysql. Sua iniciacao se da atraves do Gradle.

- **TECNOLOGIAS**
  
    Java 17
      
    Gradle
  
    Springboot
   
    RestTemplate
   
    Lombok
  
    DTO para expor dados para api
  
    Mysql
  
    Entity class
  
    StructMapper
  
    Junit
  
    @Advice Tratamento de Exceptions
 
