# LibraFlow

LibraFlow is a Spring Boot backend project for managing libraries, books, and users.

>  **Project status: Work in Progress**  
> LibraFlow is still under active development. Some modules are incomplete, and APIs may change as the project evolves.

## Overview

The goal of LibraFlow is to provide a clean and extensible library-management backend with:

- Library catalog management
- Book inventory operations
- User onboarding and account lifecycle
- API-first development with OpenAPI documentation
- Relational data persistence with JPA/Hibernate

## Current Implementation Status

### Implemented

- Spring Boot application bootstrap
- Core domain models:
  - `Library`
  - `Book`
  - `User`
- CRUD/search flows for books (including soft-delete behavior)
- Basic library create/delete/search flows
- User creation flow
- Centralized exception handling for common domain errors

### In Progress / Not Final Yet

- Authentication and authorization (auth module scaffolded)
- Complete request validation consistency
- Endpoint contract refinement and standardization
- Expanded automated testing
- Production-ready deployment profile and documentation

## Tech Stack

- **Language:** Java 21
- **Framework:** Spring Boot 4
- **Data Access:** Spring Data JPA (Hibernate)
- **Databases:** PostgreSQL (default), H2 dependency included
- **API Docs:** Springdoc OpenAPI + Swagger UI
- **Build Tool:** Maven Wrapper (`./mvnw`)
- **Utilities:** Lombok

## Project Structure

```text
src/main/java/com/sikavo/libraflow
├── auth
├── book
│   ├── controller
│   ├── dto
│   ├── mapper
│   ├── repository
│   └── service
├── common
│   ├── dto
│   └── exception
├── library
│   ├── controller
│   ├── dto
│   ├── mapper
│   ├── model
│   ├── repository
│   └── service
├── user
│   ├── controller
│   ├── dto
│   ├── mapper
│   ├── model
│   ├── repository
│   └── service
└── utils
```

## Getting Started

### Prerequisites

- Java 21
- PostgreSQL (local or remote instance)

### Configuration

The app reads datasource settings from environment variables with defaults:

- `SPRING_DATASOURCE_URL` (default: `jdbc:postgresql://localhost:5432/libraflow`)
- `SPRING_DATASOURCE_USERNAME` (default: `postgres`)
- `SPRING_DATASOURCE_PASSWORD` (default value exists in config; override in your environment)
- Optional Hikari tuning variables are also supported.

### Run the Application

```bash
./mvnw spring-boot:run
```

### Run Tests

```bash
./mvnw test
```

## API Notes

Current API groups include:

- `/api/v1/books` – create, search, update, and soft-delete book operations
- `/api/v1/library` – library create/delete/search operations
- `/api/v1/user` – user creation

Swagger/OpenAPI UI is available when running the app (default Springdoc path):

- `http://localhost:8080/swagger-ui/index.html`

## Known Gaps

- Auth flow is not yet implemented end-to-end.
- Some endpoint signatures and annotations are still being standardized.
- Test coverage is minimal and will expand as features stabilize.

## Contributing (Early Stage)

If you want to contribute while the project is still evolving:

1. Keep changes small and focused.
2. Follow existing package boundaries (`controller`, `service`, `repository`, `dto`, `mapper`).
3. Add/adjust tests when introducing functional changes.
4. Open an issue for major architectural changes before implementation.

## Vision

LibraFlow is being built toward a maintainable and production-ready library platform.  
As the project matures, this document will be updated with finalized architecture, security model, and deployment guidance.
