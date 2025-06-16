# Sistema de Reserva de Salas (Microservices Booking System)

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

Este projeto é uma implementação de um sistema de reserva de salas utilizando uma arquitetura de microserviços, com o objetivo de demonstrar os princípios do Domain-Driven Design (DDD) em um ambiente moderno, containerizado e orientado a eventos.

## ✨ Conceitos Principais

A arquitetura foi guiada por dois conceitos fundamentais:

* **Arquitetura de Microserviços:** O sistema é dividido em serviços independentes e autônomos, cada um com sua própria responsabilidade e banco de dados. Isso promove escalabilidade, resiliência e manutenibilidade. A comunicação entre os serviços é feita de forma assíncrona através de um message broker.

* **Domain-Driven Design (DDD):** Cada microserviço é construído em torno de um "Contexto Delimitado" (Bounded Context), com um modelo de domínio rico que encapsula as regras de negócio. O código é organizado em camadas (Domain, Application, Infrastructure, Presentation) para garantir um baixo acoplamento e alta coesão.

## 🚀 Microserviços

O sistema é composto pelos seguintes microserviços:

* **👤 `user-service`**: Serviço central para gerenciamento de identidade, autenticação (geração de JWT) e perfis de usuário.
* **🚪 `room-service`**: Gerencia o inventário de salas, suas características e estados. Publica eventos quando salas são criadas ou alteradas.
* **📅 `reservation-service`**: Orquestra o ciclo de vida das reservas, conectando usuários a salas em períodos específicos. Publica eventos sobre o status das reservas.

## 🛠️ Stack de Tecnologias

| Categoria                | Tecnologia                                                              |
| ------------------------ | ----------------------------------------------------------------------- |
| **Backend** | Java 17, Spring Boot, Spring Web, Spring Data JPA                       |
| **Segurança** | Spring Security, JSON Web Token (JWT)                                   |
| **Banco de Dados** | PostgreSQL, Flyway (para migrações de schema)                           |
| **Mensageria** | RabbitMQ (com Spring AMQP)                                              |
| **Containerização** | Docker, Docker Compose                                                  |
| **API Gateway** | Traefik                                                                 |
| **Ferramentas de Dev** | Lombok, Maven, Adminer (para gerenciamento de DB)                       |

## ⚙️ Pré-requisitos

Para executar este projeto localmente, você precisará ter instalado:

* [Git](https://git-scm.com/)
* [Docker](https://www.docker.com/products/docker-desktop/)
* [Docker Compose](https://docs.docker.com/compose/)

> **Nota:** Você **não** precisa ter Java ou Maven instalados na sua máquina local, pois todo o processo de build e execução acontece dentro de contêineres Docker.

## ▶️ Como Executar o Projeto

Siga estes passos para colocar todo o sistema no ar:

1.  **Clone o repositório:**
    ```bash
    git clone <url-do-seu-repositorio>
    cd <nome-da-pasta-do-projeto>
    ```

2.  **Crie o arquivo de variáveis de ambiente:**
    Crie um arquivo chamado `.env` na raiz do projeto. Este arquivo guardará a chave secreta do JWT e **não deve** ser enviado para o Git.
    ```
    # .env
    JWT_SECRET=sua-chave-super-secreta-e-longa-que-todos-os-servicos-usam-aqui
    ```

3.  **Suba os contêineres com Docker Compose:**
    Este comando irá construir as imagens de cada microserviço e iniciar todos os contêineres definidos no `docker-compose.yml`.
    ```bash
    docker-compose up --build -d
    ```
    * `--build`: Força a reconstrução das imagens a partir dos Dockerfiles.
    * `-d`: Executa os contêineres em modo "detached" (em segundo plano).

4.  **Verifique se tudo está rodando:**
    ```bash
    docker-compose ps
    ```
    Você deverá ver todos os serviços (`user-service`, `room-service`, `reservation-service`, `postgres_db`, `rabbitmq`, `traefik`, `adminer`) com o status `Up` ou `running`.

## 🔩 Testando a API (Fluxo End-to-End)

Todas as requisições devem ser feitas para o API Gateway (Traefik) na porta `80`.

1.  **Registrar um novo usuário:**
    ```bash
    curl -X POST http://localhost/api/users \
    -H "Content-Type: application/json" \
    -d '{"name": "Cesar Pisa", "email": "cesar.pisa@example.com", "password": "password123", "phone": "11999999999", "street": "Rua Exemplo", "city": "Toledo", "state": "PR", "zipCode": "85900-000"}'
    ```

2.  **Fazer login para obter um token JWT:**
    ```bash
    # Execute este comando e copie o valor do campo "token" da resposta
    curl -X POST http://localhost/api/users/login \
    -H "Content-Type: application/json" \
    -d '{"email": "cesar.pisa@example.com", "password": "password123"}'
    ```

3.  **Criar uma nova sala (usando o token):**
    Substitua `<SEU_TOKEN_AQUI>` pelo token obtido no passo anterior.
    ```bash
    curl -X POST http://localhost/api/rooms \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer <SEU_TOKEN_AQUI>" \
    -d '{"name": "Sala de Reunião 1", "location": "Primeiro Andar", "capacity": 10}'
    ```

4.  **Criar uma reserva (usando o token e os IDs):**
    Substitua os IDs pelos valores retornados nos passos 1 e 3.
    ```bash
    curl -X POST http://localhost/api/reservations \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer <SEU_TOKEN_AQUI>" \
    -d '{"userId": "ID_DO_USUARIO_AQUI", "roomId": "ID_DA_SALA_AQUI", "startTime": "2025-10-20T14:00:00", "endTime": "2025-10-20T15:00:00"}'
    ```

## 🧰 Ferramentas de Gerenciamento

* **Traefik Dashboard (API Gateway):** [http://localhost:8080](http://localhost:8080)
* **RabbitMQ Management UI (Mensageria):** [http://localhost:15672](http://localhost:15672) (usuário: `guest`, senha: `guest`)
* **Adminer (Banco de Dados):** [http://localhost:8088](http://localhost:8088)
    * Sistema: `PostgreSQL`
    * Servidor: `postgres_db`
    * Usuário/Senha: `postgres` / `postgres`

## 📁 Estrutura do Projeto