# Integração Assíncrona com RabbitMQ
Este repositório contém um projeto usado em uma live sobre integração assíncrona com mensageria (RabbitMQ).
São três microserviços independentes que ilustram comunicação síncrona (REST) e assíncrona (mensageria): pedidos, pagamentos e notas-fiscais.

## Visão geral
O objetivo desta base é demonstrar um cenário realista de integração entre microserviços usando padrões síncronos (chamadas REST) e assíncronos (RabbitMQ). Cada serviço tem seu próprio banco de dados MySQL e responsabilidades de domínio:

✔️ pedidos: gestão de pedidos (criação, consulta). Porta: 8080.
✔️ pagamentos: processamento de pagamento associado a pedidos. Porta: 8081.
✔️ notas-fiscais: emissão/registro de notas fiscais com integração ao serviço de pedidos. Porta: 8082.

## Pré-requisitos

JDK 17
Maven
IntelliJ IDEA (ou outra IDE Java com suporte a Spring Boot)
Postman (para importar e executar as collections fornecidas)

## Tecnologias
Spring Boot
Spring Data JPA + Hibernate
Flyway (migrações de banco)
MySQL
RabbitMQ
Maven (build)
## Descrição dos módulos (domínio e tech)
🔹pedidos

✔️ Domínio: criação e consulta de pedidos, expõe API REST.
✔️ Tech: Spring Boot, JPA, Flyway, MySQL.
✔️ Main: com.florinda.pedidos.PedidosApplication.
✔️ DB: florinda_pedidos

📟 pagamentos

✔️ Domínio: criação/registro de pagamentos
✔️ Tech: Spring Boot, JPA, Flyway, MySQL.
✔️ Main: com.florinda.pagamentos.PagamentosApplication.
✔️ DB: florinda_pagamentos.

📝 notas-fiscais

✔️ Domínio: emissão e armazenamento de notas fiscais a partir de integrações com pedidos.
✔️ Tech: Spring Boot, consumo de APIs REST de pedidos para alguns fluxos e possivelmente eventos.
✔️ Main: com.florinda.notasfiscais.NotasFiscaisApplication.

Como executar

Iniciar o banco MySQL
Iniciar as aplicações na IDE (IntelliJ):
Abra a pasta do repositório como um projeto Maven.
Aguarde o download das dependências.
Para cada módulo (pedidos, pagamentos, notas-fiscais) localize a classe *Application (ex.: PedidosApplication) e execute como aplicação Spring Boot.
Exemplo de execução a partir do terminal (opcional):

# Na raiz de cada módulo
mvn clean spring-boot:run -DskipTests
Verifique as portas e endpoints:
pedidos → http://localhost:8080
pagamentos → http://localhost:8081
notas-fiscais → http://localhost:8082
Postman — collections
Existem collections Postman fornecidas para facilitar os testes e demonstrar os fluxos. Arquivos (na raiz de cada módulo):

notas-fiscais/florinda-eats-notas-fiscais.postman_collection.json
pagamentos/florinda-eats-notas-fiscais.postman_collection.json
pedidos/florinda-eats-pedidos.postman_collection.json
O que as collections fazem (resumo):

pedidos collection:

Endpoints para criar pedidos, consultar pedidos, listar itens.
Fluxo típico: criar pedido → obter id → consultar status.
pagamentos collection:

Endpoints para simular criação de pagamento e consulta de status do pagamento.
Usar para testar integrações entre pedido → processamento de pagamento.
notas-fiscais collection:

