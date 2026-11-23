# Diagrama do banco de dados

~~~mermaid
erDiagram
    USUARIO ||--o{ AVISO : publica
    USUARIO ||--o{ RESERVA : solicita
    ROTA ||--o{ PARADA : possui
    ROTA ||--o{ VIAGEM : atende
    VEICULO ||--o{ VIAGEM : realiza
    VIAGEM ||--o{ RESERVA : recebe
    RESERVA ||--o| PRESENCA : registra
    USUARIO {
        bigint id PK
        string nome
        string email UK
        string senha
        string perfil
    }
    VEICULO {
        bigint id PK
        string placa UK
        string modelo
        string tipo
        int capacidade
    }
    ROTA {
        bigint id PK
        string nome
        string origem
        string destino
        int duracaoMinutos
    }
    VIAGEM {
        bigint id PK
        datetime partida
        bigint rota_id FK
        bigint veiculo_id FK
        string status
    }
    PARADA {
        bigint id PK
        string nome
        int ordem
        bigint rota_id FK
    }
    RESERVA {
        bigint id PK
        bigint usuario_id FK
        bigint viagem_id FK
        string status
    }
    PRESENCA {
        bigint id PK
        bigint reserva_id FK_UK
        boolean presente
    }
    AVISO {
        bigint id PK
        string titulo
        string mensagem
        bigint autor_id FK
        datetime criadoEm
    }
~~~

## Regras

- Usuário publica avisos e solicita reservas.
- Rota possui paradas e atende viagens; veículo realiza viagens.
- Viagem recebe reservas, limitadas pela capacidade do veículo.
- Reserva tem zero ou um registro de presença; `reserva_id` é único em PRESENCA.
- Usuário e viagem formam uma chave única em RESERVA; uma reserva cancelada pode ser reativada.
- Relações Java são unidirecionais; o diagrama representa as cardinalidades do banco.
