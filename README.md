# Nexus New Demo

Spring Boot-projekt för att hantera anställda och arbetspass.

Projektet använder bland annat:

- Spring Web MVC
- Spring Data JPA
- Spring Security
- JWT
- PostgreSQL i applikationen
- H2 i tester
- MapStruct
- Maven

## Tester

Tester körs med:

```bash
./mvnw test
```

Just nu finns tester för service-, repository- och controller-lagret.

### Service

`EmployeeServiceTest` testar att det går att:

- skapa en anställd
- hämta en anställd med id
- hantera att en anställd saknas
- hämta alla anställda

`ShiftServiceTest` testar att det går att:

- skapa ett arbetspass
- hämta alla arbetspass
- lägga till en anställd på ett arbetspass
- ta bort en anställd från ett arbetspass

Service-testerna använder Mockito, så repository och mapper är mockade.

### Repository

`EmployeeRepositoryTest` testar `findByUsername` mot H2.

Det testet kör alltså mot en riktig testdatabas, inte mot mockar.

### Controller

`EmployeeControllerTest` testar två endpoints med `MockMvc`:

- `GET /employees`
- `GET /employees/{id}`

Eftersom endpoints är skyddade används `@WithMockUser(roles = "ADMIN")` i testerna.

## Nästa saker att göra

Om projektet byggs vidare är nästa rimliga steg:

- lägga till `ShiftControllerTest`
- testa login-flödet i `AuthController`
- testa relationen mellan `Employee` och `Shift` i repository-lagret
- se över felhantering, till exempel vad API:t ska returnera när id saknas

## Kort om testtyperna

- Mockito-test: testar en klass isolerat
- `@DataJpaTest`: testar repository mot H2
- `@WebMvcTest`: testar controller med `MockMvc`
- `@SpringBootTest`: startar en större del av applikationen
