## README - Conta Service 🏦

Este serviço é responsável pelo gerenciamento de contas bancárias. Ele realiza operações de CRUD em contas e se comunica com o `bacenService` para registrar chaves Pix associadas às contas.

---

### 📌 Funcionalidades

* Criar conta bancária com chave Pix.
* Buscar todas as contas cadastradas.
* Buscar conta por ID.
* Atualizar dados da conta.
* Deletar conta.
* Impede duplicações utilizando o conceito de **idempotência**.
* Integração via Feign Client com o `bacenService` para registrar chaves Pix.

---

### 🚀 Tecnologias

* Java + Spring Boot
* JPA + H2
* Feign
* Lombok
* Insomnia
* WireMock

---

### ⚙️ Como executar

```bash
# Na raiz do projeto contaService
./mvnw spring-boot:run
```

* A aplicação estará disponível em: `http://localhost:9000`

---

### 🔗 Integração com o BacenService

O `contaService` utiliza um cliente Feign para se comunicar com o `bacenService`, consumindo os seguintes endpoints:

* `POST /api/bacen/chaves` para registrar uma chave Pix.

---

### 🔐 Endpoints principais

#### ✅ Criar conta

```http
POST /api/contas
```

**Exemplo de body:**

```json
{
  "nomeTitular": "Fulano",
  "numeroAgencia": "0001",
  "numeroConta": "12345-6",
  "chavePix": "fulano@pix.com"
}
```

---

#### 🔍 Buscar todas as contas

```http
GET /api/contas
```

---

#### 🔍 Buscar conta por ID

```http
GET /api/contas/{id}
```

---

#### ✏️ Atualizar conta

```http
PUT /api/contas/{id}
```

**Body:**

```json
{
  "nomeTitular": "Fulano de Tal",
  "numeroAgencia": "0001",
  "numeroConta": "12345-6",
  "chavePix": "fulano@pix.com"
}
```

---

#### ❌ Deletar conta

```http
DELETE /api/contas/{id}
```

---

### ✅ Testes

O projeto inclui:

* **Testes unitários** para os serviços e DTOs.
* **Testes de integração**, utilizando WireMock para simular o `bacenService`.

---

### 📀 Observações

* Utiliza `@Transactional` para garantir consistência nas operações.
* Implementa **idempotência** na criação de contas, evitando registros duplicados para a mesma chave Pix.
