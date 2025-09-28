# Projeto de CRUD - Disciplina de Sistemas Distribuídos

Este projeto consiste em uma aplicação CRUD (Create, Read, Update, Delete) simples para gerenciamento de usuários. Foi desenvolvido como requisito para a disciplina de Sistemas Distribuídos, com o objetivo de explorar na prática conceitos de APIs, bancos de dados NoSQL e ambientes de desenvolvimento containerizados.

Este trabalho representa minha primeira experiência com as tecnologias centrais utilizadas: o banco de dados **MongoDB** e o microframework web **Javalin**.

## Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes ferramentas:

* **Java 21:** Linguagem de programação principal.
* **Javalin:** Um microframework web leve para Kotlin e Java, utilizado para a criação da API REST.
* **MongoDB:** Banco de dados NoSQL orientado a documentos, escolhido para explorar a flexibilidade de um schema não-relacional.
* **Docker & Docker Compose:** Ferramentas de containerização utilizadas para criar um ambiente de desenvolvimento padronizado, portátil e de fácil configuração, garantindo que a aplicação e o banco de dados rodem da mesma forma em qualquer máquina.
* **Maven:** Ferramenta de automação de build e gerenciamento de dependências do projeto Java.

## Como Executar o Projeto

Para executar a aplicação, é necessário ter o **Docker** e o **Docker Compose** instalados em sua máquina.

### Passos para a Configuração

1.  **Clone o Repositório**
    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    cd <NOME_DA_PASTA_DO_PROJETO>
    ```

2.  **Crie o Arquivo de Variáveis de Ambiente**
    Crie um arquivo chamado `.env` na raiz do projeto. Ele guardará as credenciais do banco de dados. Copie o conteúdo abaixo para dentro dele:

    ```ini
    # Credenciais do MongoDB
    MONGO_INITDB_ROOT_USERNAME=admin
    MONGO_INITDB_ROOT_PASSWORD=password
    MONGO_DB=minhafaculdade

    # Porta da aplicação Java
    APP_PORT=8080
    ```

3.  **Suba os Contêineres**
    Com o Docker em execução, execute o seguinte comando no terminal, na raiz do projeto:

    ```bash
    docker-compose up --build
    ```
    * O comando `docker-compose up` irá ler o arquivo `docker-compose.yml`, baixar as imagens necessárias e iniciar os contêineres.
    * A flag `--build` garante que a imagem da sua aplicação Java seja construída a partir do código-fonte mais recente.

### Acessando os Serviços

Após a execução, os seguintes serviços estarão disponíveis:

* **API Java:** `http://localhost:8080`
* **Mongo Express (Interface Gráfica para o DB):** `http://localhost:8081`
    * **Login:** `admin`
    * **Senha:** `password`

## Endpoints da API

A API possui os seguintes endpoints para o gerenciamento de usuários:

| Método | Rota               | Descrição                              | Corpo da Requisição (Exemplo)                                    |
| :----- | :----------------- | :------------------------------------- | :--------------------------------------------------------------- |
| `POST` | `/users`           | Cria um novo usuário.                  | `{ "name": "Nome Sobrenome", "email": "email@exemplo.com" }`      |
| `GET`  | `/users`           | Lista todos os usuários cadastrados.   | (Vazio)                                                          |
| `GET`  | `/users/{id}`      | Busca um usuário específico pelo seu `_id`. | (Vazio)                                                          |
| `PUT`  | `/users/{id}`      | Atualiza os dados de um usuário.       | `{ "name": "Nome Sobrenome", "email": "email@exemplo.com" }`          |
| `DELETE` | `/users/{id}`      | Deleta um usuário específico.          | (Vazio)                                                          |

---

### Autor

* **Guilherme V. R. Figueiredo**