# Nexus New Demo

Spring Boot/Maven-projekt för anställda, arbetspass och JWT-baserad inloggning.

Nuvarande fokus är testklasser. Projektet har tester på tre nivåer: service, repository och controller.

## Kör tester

```bash
./mvnw test
```

Senaste status: `BUILD SUCCESS`.

## Teststatus

### Service-tester

`EmployeeServiceTest` testar:

- skapa employee
- hämta employee när id finns
- kasta exception när id saknas
- hämta alla employees

`ShiftServiceTest` testar:

- skapa shift
- hämta alla shifts
- lägga till employee på shift
- ta bort employee från shift

### Repository-test

`EmployeeRepositoryTest` testar:

- `findByUsername` mot riktig H2-testdatabas

Detta visar att repository/JPA-lagret fungerar utan att starta PostgreSQL.

### Controller-test

`EmployeeControllerTest` testar med `MockMvc`:

- `GET /employees`
- `GET /employees/{id}`
- admin-behörighet med `@WithMockUser`
- HTTP-status och JSON-response

## Var vi är nu

Projektet har en bra grund för LIA-bedömning:

- Mockito används i service-tester
- H2 används i repository-test
- MockMvc används i controller-test
- Spring Security hanteras i controller-test
- hela testsviten går igenom lokalt

## Nästa rimliga steg

Om mer tid finns, prioritera detta:

1. Byt namn på `createEmployeeShouldReturn201`, eftersom `201` är controller-/HTTP-språk och testet ligger i service-lagret.
2. Lägg till `ShiftControllerTest` för `GET /shifts`.
3. Lägg till ett repository-test för relationen mellan `Employee` och `Shift`.
4. Lägg till ett auth-test för `POST /auth/login`.
5. Snygga upp upprepade testdata med små helper-metoder om testerna börjar bli svåra att läsa.

## Viktig skillnad mellan testtyper

- `MockitoExtension`: testar en klass isolerat, utan Spring och utan databas.
- `@DataJpaTest`: testar riktig repository/JPA mot H2.
- `@WebMvcTest`: testar controller-lagret med Spring MVC och `MockMvc`.
- `@SpringBootTest`: startar större del av appen och passar bättre för integrationstester.

## Definition of done för nuvarande testfas

- `./mvnw test` passerar.
- Service-lagret har både happy path och felväg.
- Repository-lagret är testat mot H2.
- Controller-lagret har minst ett skyddat endpoint-test med JSON-kontroll.
