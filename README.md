# ecommerce-backend-4a
Backend REST para usuários, veículos, rotas, viagens, reservas, paradas, presenças e avisos.

## Tecnologias

- Java 21, Spring Boot, Spring Data JPA e MySQL
- Spring Security com HTTP Basic e perfis ADMIN e ALUNO
- BCrypt para senhas cadastradas pela API
- Maven

## Executar no Codespace

~~~bash
cd ecommerce
./mvnw spring-boot:run
~~~

No Windows, use `mvnw.cmd spring-boot:run`. A API fica em `localhost:8080`. O Dev Container inicializa o MySQL e cria o banco local.

Para os testes automatizados:

~~~bash
cd ecommerce
./mvnw test
~~~

A autenticação HTTP Basic usa contas em memória definidas em `SegurancaConfiguracao.java`. Ligar o login aos usuários persistidos no banco continua pendente.

## API

| Recurso | Caminho | Acesso |
|---|---|---|
| Usuários | `/usuarios` | ADMIN |
| Veículos, rotas, viagens, paradas, reservas, presenças e avisos | `/{recurso}` | GET: ADMIN/ALUNO; alterações: ADMIN |

Veja a [documentação da API](docs/API.md), o [diagrama do banco](docs/diagrama-banco.md) e a [coleção Postman](postman/ToAqui.postman_collection.json).

A coleção cria dados de teste, percorre CRUD, valida regras de reserva e remove os registros ao final. Configure as variáveis `usuarioAuth` e `senhaAuth` e execute-a somente em ambiente de desenvolvimento ou teste. O workflow Maven salva relatórios JUnit como artefato.

## Integrantes

| Integrantes  
|---|---|
| João Vitor Lemos de Campos | Pendente |
| Paulo Roberto Azevedo Neto | Pendente |
| Rogério Cater jr | Pendente |
| Enzo Luiz Keller Granemann | Pendente |
| Joaquim Matheus da Silva Costa | Pendente |
| Leandro Gabriel Tostes | Pendente |

## Arquivos herdados

`Categoria`, `Produto` e `/produtos` pertencem ao esqueleto antigo de e-commerce e não fazem parte dos oito recursos do Tô Aqui!.
