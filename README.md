# Nexus Backend

Spring Boot backend for employee and shift management.

It includes JWT login, role-based access, PostgreSQL persistence, validation, Swagger docs, RabbitMQ messaging, email support, Docker, and automated tests.

## Demo

Swagger UI:

https://gastric-valencia-klerk-4f003058.koyeb.app/swagger-ui/index.html

Demo login:

```text
Username: admin
Password: admin1234
```

Log in with `POST /auth/login`, then use the returned JWT in Swagger's Authorize dialog.

## Stack

- Java 17
- Spring Boot
- Spring Web MVC
- Spring Security
- Spring Data JPA
- PostgreSQL / H2
- RabbitMQ
- MapStruct
- Maven
- Docker
- Swagger / OpenAPI

## Main Endpoints

- `POST /auth/login`
- `GET /employees`
- `POST /employees`
- `DELETE /employees/{id}`
- `GET /shifts`
- `POST /shifts`
- `POST /shifts/{shiftId}/employees/{employeeId}`
- `DELETE /shifts/{shiftId}/employees/{employeeId}`

## Run Locally

Set the required environment variables:

```bash
export DB_URL=<database-url>
export DB_USERNAME=<database-username>
export DB_PASSWORD=<database-password>
export JWT_SECRET=<jwt-secret>
export ADMIN_PASSWORD=<admin-password>
export MAIL_USERNAME=<mail-username>
export MAIL_PASSWORD=<mail-password>
export RABBITMQ_USERNAME=<rabbitmq-username>
export RABBITMQ_PASSWORD=<rabbitmq-password>
export RABBITMQ_VHOST=<rabbitmq-vhost>
```

Start the app:

```bash
./mvnw spring-boot:run
```

The API runs on port `8000`.

## Tests

```bash
./mvnw test
```
