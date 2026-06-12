# Registro de bugs - OdonTecWeb

Este arquivo funciona como registro de defeitos da etapa de testes. Os itens foram identificados durante a integração do desktop, banco e front-end.

| ID | Severidade | Falha encontrada | Correção aplicada | Status |
|---|---|---|---|---|
| BUG-001 | Alta | O front-end apenas exibia mensagens com JavaScript e não persistia dados. | Formulários migrados para controllers Spring MVC e repositórios `JdbcTemplate`. | Corrigido |
| BUG-002 | Alta | Login estava fixo no JavaScript e aceitava credenciais diferentes das existentes no banco. | Autenticação passou a consultar a tabela `usuario` e criar sessão HTTP. | Corrigido |
| BUG-003 | Alta | Rotas internas podiam ser acessadas sem autenticação. | Criado `AutenticacaoInterceptor` protegendo todas as rotas, exceto login e arquivos estáticos. | Corrigido |
| BUG-004 | Média | Campos CPF, CRO, CNPJ e descrição existiam nas páginas, mas não no banco, causando perda silenciosa de dados. | Formulários alinhados ao schema MySQL existente. | Corrigido |
| BUG-005 | Média | O DAO antigo utilizava `nome_tratamento`, enquanto a coluna real é `nome`. | SQL do novo repositório usa a coluna `tratamento.nome`. | Corrigido |
| BUG-006 | Média | Agenda aceitava textos livres que não correspondiam a registros cadastrados. | Paciente, dentista e tratamento passaram a ser selecionados de listas carregadas do banco. | Corrigido |
| BUG-007 | Baixa | Não havia confirmação antes de excluir registros. | Adicionada confirmação JavaScript aos formulários de exclusão. | Corrigido |
| BUG-008 | Baixa | Não existia feedback de validação no servidor. | Adicionadas Bean Validation e mensagens junto aos campos. | Corrigido |
| BUG-009 | Alta | Ao editar um paciente sem alterar a data, o navegador enviava a data vazia e o banco perdia o valor. | Adicionada formatação ISO explícita nos campos `LocalDate` e `LocalTime`. | Corrigido |

## Evidências de teste

- `ValidacaoServiceTest`: regras sem acesso ao banco.
- `PacienteRepositoryTest`: inclusão, consulta, alteração e exclusão usando H2.
- `UsuarioRepositoryTest`: autenticação válida e rejeição de senha inválida.
- Testes manuais executados em 12/06/2026 e registrados em `PLANO_TESTES.md`.
- Navegação conferida em desktop e em viewport móvel de 390 x 844 pixels.
- Integração real confirmada com o banco MySQL `odontecdb`.
