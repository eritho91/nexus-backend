Nexus Backend

Nexus Backend är ett Spring Boot-projekt för att hantera anställda och arbetspass.

Projektet visar backendutveckling med säkerhet, databaser, validering, felhantering och automatiska tester.

Demo

Swagger UI:

https://gastric-valencia-klerk-4f003058.koyeb.app/swagger-ui/index.html

Admin-inloggning
Användarnamn: admin
Lösenord: admin1234

Logga in genom POST /auth/login.

Kopiera sedan JWT-token från svaret och ange den genom knappen Authorize i Swagger.

Teknik
Java 17
Spring Boot
Spring Web MVC
Spring Data JPA
Spring Security
JWT
PostgreSQL
H2
MapStruct
Maven
Docker
GitHub Actions
Swagger/OpenAPI
Funktioner
Skapa och hämta anställda
Skapa och hämta arbetspass
Lägga till anställda på arbetspass
Ta bort anställda från arbetspass
Ta bort anställda
JWT-baserad inloggning
Rollbaserad åtkomstkontroll
Validering av inkommande data
Global felhantering
Tester

Projektet innehåller tester för:

Service-lagret med JUnit och Mockito
Repository-lagret mot H2
Controller-lagret med MockMvc

Tester körs automatiskt genom GitHub Actions.

Kör tester lokalt med:

./mvnw test
Lokal start

Projektet kräver PostgreSQL och följande miljövariabler:

export DB_URL=<database-url>
export DB_USERNAME=<database-username>
export DB_PASSWORD=<database-password>
export JWT_SECRET=<jwt-secret>
export ADMIN_PASSWORD=<admin-password>

Starta applikationen med:

./mvnw spring-boot:run

Demo-inloggningen och databasen är endast avsedda för testning.
