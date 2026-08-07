# Nexus Backend

Nexus is a Spring Boot backend for managing employees and work shifts.

The project demonstrates backend development with REST APIs, authentication and authorization, relational databases, validation, error handling, automated testing, Docker, and continuous integration.

## Live Demo

Swagger UI:

https://gastric-valencia-klerk-4f003058.koyeb.app/swagger-ui/index.html

Demo administrator account:

```text
Username: admin
Password: admin1234
```

Log in using:

```text
POST /auth/login
```

Copy the returned JWT and use the **Authorize** button in Swagger to authenticate.

The demo account and database are intended for testing purposes only.

## Tech Stack

* Java 17
* Spring Boot
* Spring Web MVC
* Spring Data JPA
* Spring Security
* JWT
* PostgreSQL
* H2
* MapStruct
* Maven
* Docker
* GitHub Actions
* Swagger / OpenAPI

## Features

* Create and retrieve employees
* Create and retrieve work shifts
* Assign employees to shifts
* Remove employees from shifts
* Delete employees
* JWT-based authentication
* Role-based authorization
* Request validation
* Global exception handling

## Testing

The project includes tests for multiple application layers:

* Service-layer tests using JUnit and Mockito
* Repository tests using H2
* Controller tests using MockMvc

Tests are also executed automatically through GitHub Actions.

Run the tests locally with:

```bash
./mvnw test
```

## Running Locally

The application requires PostgreSQL and the following environment variables:

```bash
export DB_URL=<database-url>
export DB_USERNAME=<database-username>
export DB_PASSWORD=<database-password>
export JWT_SECRET=<jwt-secret>
export ADMIN_PASSWORD=<admin-password>
```

Start the application with:

```bash
./mvnw spring-boot:run
```

## Project Focus

Nexus is designed as a portfolio project demonstrating a structured Spring Boot backend with security, persistence, validation, testing, API documentation, containerization, and automated builds.
