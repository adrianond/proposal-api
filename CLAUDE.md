# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Build
./mvnw clean package

# Run
./mvnw spring-boot:run

# Test
./mvnw test
```

Java 17, Maven wrapper (`mvnw`/`mvnw.cmd`).

## Architecture

Spring Boot 3 REST API for automobile insurance proposal management. Layered architecture following a Use Case pattern:

```
Controller → UseCase → RepositoryFacade → JpaRepository → Oracle DB
```

- **Controllers** (`controller/`): REST endpoints under `/api`. Two controllers: `LoginController` and `PropostaController`.
- **Use Cases** (`usecase/`): One `@Component` per business operation (e.g., `CadastrarProposta`, `TramitarProposta`, `AnalisarProposta`). All business logic lives here.
- **Repository Facades** (`database/repository/`): Wrap Spring Data JPA repositories. Controllers/use cases depend only on these facades, not directly on JPA repos.
- **Builders** (`builder/`): Handle DTO ↔ Entity conversion (`PropostaAutoBuilder`).
- **Config** (`config/`): Security, CORS, WebSocket, Swagger. Authentication uses a custom `AutenticacaoInterceptorFilter` that reads a JSON `autenticacao` header.

## Key Domain Concepts

- **PropostaAuto**: Central entity. Links to `Cliente` (with `Endereco`, `Telefone`, `Profissao`), `Garantia` (vehicle info), `StatusProposta`, and three `Usuario` references (`usuarioCadastro`, `usuarioAlteracao`, `usuarioAnalise`).
- **Proposal Workflow**: Proposals are created → claimed for analysis (`/take`) → transitioned to a new status (`/aprove`).
- **Real-time**: WebSocket (STOMP) broadcasts changes to `/topic/propostas` and `/topic/propostas-aprovadas`. The `ExecutarCallback*` use cases handle these notifications.

## API

| Method | Path | Use Case |
|--------|------|----------|
| POST | `/api/login` | `EfetuarLogin` |
| POST | `/api/proposals` | `CadastrarProposta` |
| GET | `/api/proposals` | `ConsultarPropostas` |
| GET | `/api/proposals/{id}` | `ConsultarProposta` |
| GET | `/api/proposals/user/{codigo}` | `ConsultarPropostasPorUsuario` |
| PUT | `/api/proposals/{id}` | `AtualizarProposta` |
| POST | `/api/proposals/{id}/aprove` | `TramitarProposta` |
| POST | `/api/proposals/{id}/take` | `AnalisarProposta` |

Authentication: all endpoints (except `/api/login/**`, `/ws-propostas/**`, Swagger) require header `autenticacao: {"login":"user","senha":"pass"}`.

## Database

Oracle (XEPDB1 at `localhost:1521`). `ddl-auto=none` — schema is managed manually. CORS is configured for `http://localhost:4200` (Angular frontend).

Swagger UI available at `/swagger-ui.html`.
