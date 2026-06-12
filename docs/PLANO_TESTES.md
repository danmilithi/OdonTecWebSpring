# Plano de testes - OdonTecWeb

## Testes automatizados

| Caso | Objetivo | Resultado esperado |
|---|---|---|
| UT-01 | Validar texto obrigatório preenchido | Não lançar exceção |
| UT-02 | Validar texto obrigatório vazio | Lançar `IllegalArgumentException` |
| UT-03 | Calcular valor total | Retornar valor unitário multiplicado pela quantidade |
| UT-04 | Rejeitar quantidade igual a zero | Lançar `IllegalArgumentException` |
| IT-01 | Executar CRUD de paciente | Incluir, buscar, alterar e excluir no H2 |
| IT-02 | Autenticar usuário válido | Retornar usuário e perfil |
| IT-03 | Rejeitar senha inválida | Não retornar usuário |

## Testes manuais

| Caso | Procedimento | Resultado esperado |
|---|---|---|
| MT-01 | Abrir `/dashboard` sem login | Redirecionar para `/login` |
| MT-02 | Entrar com `Mariana` e `123` | Exibir dashboard e nome do usuário |
| MT-03 | Entrar com senha inválida | Exibir mensagem de credenciais inválidas |
| MT-04 | Cadastrar paciente com nome vazio | Exibir validação e não salvar |
| MT-05 | Cadastrar, editar e excluir paciente | Tabela e banco refletirem as três operações |
| MT-06 | Cadastrar dentista e tratamento | Registros aparecerem na listagem |
| MT-07 | Criar consulta | Combos exibirem registros cadastrados e consulta ser persistida |
| MT-08 | Buscar produto por nome | Tabela exibir apenas correspondências |
| MT-09 | Cadastrar produto com preço zero | Exibir validação e não salvar |
| MT-10 | Clicar em excluir e cancelar confirmação | Registro permanecer |
| MT-11 | Efetuar logout | Invalidar sessão e voltar ao login |
| MT-12 | Abrir em viewport móvel | Navegação e formulários permanecerem utilizáveis |

## Execução em 12/06/2026

| Casos executados | Resultado | Evidência |
|---|---|---|
| UT-01 a UT-04 | Aprovado | `ValidacaoServiceTest`, executado pelo Maven |
| IT-01 | Aprovado | CRUD completo no H2 em `PacienteRepositoryTest` |
| IT-02 e IT-03 | Aprovado | Autenticação válida e inválida em `UsuarioRepositoryTest` |
| MT-01 a MT-04 | Aprovado | Redirecionamento, login válido/inválido e mensagem de campo obrigatório conferidos no navegador |
| MT-05 a MT-07 | Aprovado | Paciente criado e editado, dentista e tratamento cadastrados e consulta persistida no MySQL |
| MT-08 e MT-09 | Aprovado | Busca por produto filtrou a tabela e preço zero foi bloqueado pelo limite mínimo do formulário |
| MT-10 | Aprovado | Formulários de exclusão possuem confirmação e cancelamento impede o envio |
| MT-11 | Aprovado | Logout invalidou a sessão e `/pacientes` voltou a redirecionar para `/login` |
| MT-12 | Aprovado | Layout verificado em viewport de 390 x 844 pixels |

Resultado automatizado: **7 testes executados, 0 falhas e 0 erros**.

Os registros criados exclusivamente para os testes manuais foram removidos do banco ao final da execução.
