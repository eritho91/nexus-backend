# Nexus New Demo

Spring Boot-projekt för att hantera anställda och arbetspass. Projektet innehåller REST-endpoints, JWT-baserad säkerhet, PostgreSQL i applikationen och H2 i tester.

## Teknik

- Java 17
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Spring Security och JWT
- PostgreSQL
- H2 för tester
- MapStruct
- Maven

## Kom igång lokalt

Projektet kräver en PostgreSQL-databas och följande miljövariabler:

```bash
export DB_URL=jdbc:postgresql://localhost:5432/nexus
export DB_USERNAME=postgres
export DB_PASSWORD=postgres
export JWT_SECRET=01234567890123456789012345678901
export ADMIN_PASSWORD=admin1234
```

Starta applikationen:

```bash
./mvnw spring-boot:run
```

Kör tester:

```bash
./mvnw test
```

## Demo-admin

Vid uppstart skapas en admin-användare om den inte redan finns.

```text
Användarnamn: admin
Lösenord: admin1234
```

De här uppgifterna är endast för lokal demo. I en riktig miljö ska `ADMIN_PASSWORD` sättas till ett starkt lösenord utanför koden.

## Funktioner

- Skapa och hämta anställda
- Skapa och hämta arbetspass
- Lägga till och ta bort anställda från arbetspass
- Ta bort anställda med `DELETE /employees/{id}`
- Skyddade endpoints för admin
- Swagger/OpenAPI

## Felhantering och validering

Projektet har en egen `ResourceNotFoundException` och en global exception handler som returnerar 404 när en resurs saknas.

DTO:er för att skapa anställda och arbetspass har validation-regler. Controllers använder `@Valid`, så ogiltig input stoppas innan service-lagret körs.

## Tester

Testerna täcker service-, repository- och controller-lagret. Controller-testerna använder `MockMvc` och mockad admin-användare.
