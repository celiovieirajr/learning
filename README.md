# API de Cadastro de Produtos

Este projeto é uma API RESTful desenvolvida com Spring Boot para gerenciamento de produtos. Ele permite operações de CRUD (criar, ler, atualizar e deletar) com validações, persistência em banco de dados e documentação interativa via Swagger.

---

## 📌 Funcionalidades

- ✅ Cadastrar novos produtos
- 🔍 Listar todos os produtos
- 🔎 Buscar produto por ID
- ✏️ Atualizar informações de um produto
- 🗑️ Remover produto do sistema
- 📄 Documentação interativa com Swagger
- 🧪 Testes unitários e de integração

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
  - Spring Web
  - Spring Data JPA
  - Bean Validation
- Banco de dados H2 (em memória)
- Swagger (Springdoc OpenAPI)
- Maven
- Docker & Docker Compose
- JUnit & Mockito

---


---

## 📋 Requisitos da API

### Entidade Produto

```json
{
  "id": 1,
  "nome": "Teclado Mecânico",
  "preco": 299.90,
  "quantidade": 10
}
````

Validações
nome: obrigatório, mínimo 3 caracteres

preco: obrigatório, maior que zero

quantidade: obrigatório, não negativo

🧪 Testes
Testes unitários com JUnit e Mockito

Testes de integração com MockMvc

Cobertura de testes para Service e Controller


