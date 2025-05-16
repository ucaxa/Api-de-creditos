# Sistema de Gerenciamento de Créditos

## 📋 Visão Geral
Solução completa para gestão de créditos com:
- **Backend**: API REST em Spring Boot
- **Frontend**: Aplicação Angular com SSR
- **Infraestrutura**: Docker (PostgreSQL + Kafka)

## 🛠️ Tecnologias

### Backend (`creditos-api`)
| Tecnologia           | Versão   | Finalidade               |
|----------------------|----------|--------------------------|
| Spring Boot          | 3.2.5    | Framework principal      |
| Java                 | 21       | Linguagem base          |
| PostgreSQL           | 15       | Banco de dados          |
| Flyway               | -        | Migrações de BD         |
| Spring Kafka         | 3.2.5    | Mensageria              |
| Lombok               | 1.18.30  | Redução de código       |

### Frontend (`front-font`)
| Tecnologia           | Versão   | Finalidade               |
|----------------------|----------|--------------------------|
| Angular              | 19       | Framework SPA            |
| Bootstrap            | 5.3      | UI Components            |
| RxJS                 | 7.8      | Programação reativa      |

## 🚀 Pré-requisitos
- Docker 23+
- Docker Compose 2.20+
- 4GB RAM disponível

## 🛠️ Configuração

1. **Clone o projeto**:
   ```bash
   https://github.com/ucaxa/Api-de-creditos.git

   
1. **Inicialize o docker e execute o comando abaixo dentro de diretório que foi clonado**:
   ```bash
   bash docker-compose up --build

 1. **Inicialize o docker e execute o comando abaixo dentro de diretório que foi clonado**:
    ```bash docker-compose up --build
  

## 🚀 Acesso aos serviços

| Serviço              | URL                               | 
|----------------------|-----------------------------------|
| Aplicação Frontend   | http://localhost:4000             | 
| API Backend          | http://localhost:8080             |
| Swagger UI           | http://localhost:8080/swagger-ui  |
| Kafka UI             | http://localhost:8081             |   






