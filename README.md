# OdonTecWeb Spring MVC

Projeto Java Web criado com Spring Boot MVC, Thymeleaf, JDBC e MySQL. O projeto pode ser aberto diretamente pelo NetBeans como projeto Maven.

## Requisitos

- Java 17 ou superior
- MySQL 8
- Maven 3.9 ou Maven integrado ao NetBeans

## Banco de dados

Execute `database/schema.sql` no MySQL. A configuração padrão usa:

- banco: `odontecdb`
- usuário: `root`
- senha: `root`

As credenciais podem ser alteradas pelas variáveis `DB_URL`, `DB_USER` e `DB_PASSWORD`.

Usuário inicial: `Daniel`  
Senha inicial: `123`

## Executar

```powershell
mvn spring-boot:run
```

Acesse `http://localhost:8080`.

## Testes

```powershell
mvn test
```

O registro de bugs está em `docs/BUGTRACKING.md` e o plano em `docs/PLANO_TESTES.md`.
