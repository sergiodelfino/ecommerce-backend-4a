# Documentação da API

Base URL local: `http://localhost:8080`. As rotas usam JSON e HTTP Basic.

## Acesso e erros

- `/usuarios/**`: ADMIN.
- GET em veículos, rotas, viagens, paradas, reservas, presenças e avisos: ADMIN ou ALUNO.
- POST, PUT e DELETE nesses recursos: ADMIN.
- Sem autenticação: 401. Usuário autenticado sem permissão: 403.
- POST: 201; GET/PUT: 200; DELETE: 204.
- Validação/argumento inválido: 400; inexistente: 404; conflito de regra: 409.

Erros tratados têm o formato `{"erro":"mensagem"}`. A autenticação HTTP Basic atualmente usa contas em memória definidas em `SegurancaConfiguracao.java`. Os registros de usuário no banco ainda não são consultados pelo mecanismo de autenticação.

## Usuários — `/usuarios`

GET lista, GET `/{id}` consulta, POST cria, PUT `/{id}` atualiza e DELETE `/{id}` remove.

~~~json
{"nome":"Aluno Exemplo","email":"aluno@example.com","senha":"<SENHA_LOCAL>","perfil":"ALUNO"}
~~~

Perfis aceitos: `ADMIN` e `ALUNO`. A senha é guardada com BCrypt e omitida nas respostas.

## Veículos — `/veiculos`

GET lista, GET `/{id}` consulta, POST cria, PUT `/{id}` atualiza e DELETE `/{id}` remove.

~~~json
{"placa":"ABC1D23","modelo":"Micro-ônibus","tipo":"Universitário","capacidade":20}
~~~

A placa tem sete caracteres e é única; capacidade deve ser maior que zero.

## Rotas — `/rotas`

GET lista, GET `/{id}` consulta, POST cria, PUT `/{id}` atualiza e DELETE `/{id}` remove.

~~~json
{"nome":"Campus - Centro","origem":"Campus","destino":"Centro","duracaoMinutos":35}
~~~

## Viagens — `/viagens`

GET lista, GET `/{id}` consulta, POST cria, PUT `/{id}` atualiza e DELETE `/{id}` remove.

~~~json
{"partida":"2030-05-10T07:00:00","rota":{"id":1},"veiculo":{"id":1},"status":"AGENDADA"}
~~~

A partida deve estar no futuro e referenciar uma rota e um veículo existentes.

## Paradas — `/paradas`

GET lista, GET `/{id}` consulta, POST cria, PUT `/{id}` atualiza e DELETE `/{id}` remove.

~~~json
{"nome":"Terminal Central","ordem":1,"rota":{"id":1}}
~~~

## Reservas — `/reservas`

GET lista, GET `/{id}` consulta, POST cria/reativa, PUT `/{id}` cancela e DELETE `/{id}` remove.

~~~json
{"usuario":{"id":1},"viagem":{"id":1},"status":"CONFIRMADA"}
~~~

O serviço define o status inicial como CONFIRMADA. Para cancelar, envie `{"status":"CANCELADA"}` no PUT. Uma reserva cancelada pode ser reativada no mesmo registro. A verificação de vagas é serializada por viagem.

## Presenças — `/presencas`

GET lista, GET `/{id}` consulta, POST cria, PUT `/{id}` atualiza e DELETE `/{id}` remove.

~~~json
{"reserva":{"id":1},"presente":true}
~~~

Cada reserva pode ter no máximo um registro de presença.

## Avisos — `/avisos`

GET lista, GET `/{id}` consulta, POST cria, PUT `/{id}` atualiza e DELETE `/{id}` remove.

~~~json
{"titulo":"Alteração de horário","mensagem":"A saída será às 7h15.","autor":{"id":1}}
~~~

A data de criação é preenchida automaticamente.
