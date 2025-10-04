# Patient Management System

[![Java](https://img.shields.io/badge/Java-21-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.x-red)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-20.x-blue)](https://www.docker.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A lightweight Spring Boot microservice for managing patient records in a healthcare system. This API handles CRUD operations for patients, with validation, Swagger documentation, and Docker support for easy deployment. Built for scalability in a microservices architecture.

## Features

- **Full CRUD Operations**: Create, read, update, and delete patient records via RESTful endpoints.
- **Data Validation**: Email uniqueness, required fields (name, address, DOB), and JPA entity constraints.
- **API Documentation**: Auto-generated OpenAPI/Swagger docs at `/v3/api-docs` or `/swagger-ui.html`.
- **Database Integration**: JPA/Hibernate with H2 (in-memory for dev) or configurable for PostgreSQL/MySQL.
- **UUID Identifiers**: Secure, unique patient IDs using UUIDv4.
- **Containerized**: Dockerfile for building and running as a Docker image.
- **Server Port**: Defaults to 4000 for easy integration with gateways or load balancers.

## Architecture Overview

This is a single microservice focused on patient data:
- **Controller**: Handles HTTP requests with Swagger annotations.
- **Entity**: JPA-mapped `Patient` model with fields like name, email, address, DOB, and registration date.
- **Service Layer**: Business logic (injected via constructor).
- **Config**: application.properties for datasource, JPA, and Springdoc.

For a full patient management system, integrate with other services (e.g., appointments, auth).

## Prerequisites

- **Java JDK 21** or higher.
- **Maven 3.x** for building.
- **Docker** (optional, for containerization).
- **Database**: H2 (embedded, for quick starts) or PostgreSQL (uncomment and configure in properties).

## Installation & Running Locally

1. **Clone the Repository**:
   ```
   git clone https://github.com/yourusername/patient-management-system.git
   cd patient-management-system
   ```

2. **Build the Project**:
   ```
   mvn clean install
   ```

3. **Run the Application**:
   ```
   mvn spring-boot:run
   ```
   - The server starts on `http://localhost:4000`.
   - Access Swagger UI: `http://localhost:4000/swagger-ui.html`.
   - H2 Console (if enabled): `http://localhost:4000/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`).

4. **Test Endpoints**:
   Use Postman, curl, or Swagger to interact:
   - **Create Patient**: `POST /patients` with JSON body.
   - **Get All**: `GET /patients`.
   - **Update**: `PUT /patients/{id}`.
   - **Delete**: `DELETE /patients/{id}`.

## API Endpoints

| Method | Endpoint              | Description                  | Request Body                  | Response                  |
|--------|-----------------------|------------------------------|-------------------------------|---------------------------|
| **GET**   | `/patients`           | Retrieve all patients       | None                          | `List<PatientResponseDTO>` |
| **POST**  | `/patients`           | Create a new patient        | `PatientRequestDTO` (JSON)    | `PatientResponseDTO`      |
| **PUT**   | `/patients/{id}`      | Update an existing patient  | `PatientRequestDTO` (JSON)    | `PatientResponseDTO`      |
| **DELETE**| `/patients/{id}`      | Delete a patient by ID      | None                          | `204 No Content`          |

- **PatientRequestDTO Example** (JSON):
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "address": "123 Health St",
    "dateOfBirth": "1990-05-15",
    "registeredDate": "2025-10-04"
  }
  ```

- **PatientResponseDTO**: Mirrors the entity with ID included.

All endpoints return `200 OK` on success; use `@Valid` for request validation.

## Configuration

Key settings in `src/main/resources/application.properties`:

```properties
spring.application.name=patient-service
server.port=4000
# Swagger/OpenAPI
springdoc.api-docs.version=OPENAPI_3_0
```

- For production DB (e.g., PostgreSQL):
  ```
  spring.datasource.url=jdbc:postgresql://localhost:5432/patientdb
  spring.datasource.username=postgres
  spring.datasource.password=yourpassword
  spring.jpa.hibernate.ddl-auto=validate
  ```

## Building and Running with Docker

1. **Build the Image**:
   ```
   docker build -t patient-service .
   ```

2. **Run the Container**:
   ```
   docker run -p 4000:4000 patient-service
   ```

- The Dockerfile uses multi-stage build: Maven for compilation, OpenJDK for runtime.
- Exposes port 4000; mount volumes for custom configs if needed.

## Patient Entity Details

The core `Patient` model:

- **Fields**: `id` (UUID), `name` (String, required), `email` (String, unique/valid email), `address` (String, required), `dateOfBirth` (LocalDate, required), `registeredDate` (LocalDate, required).
- **JPA Annotations**: `@Entity`, `@Id` with auto-generation, `@Column(unique=true)` for email.

Getters/setters provided; extend as needed for audits or relations.

## Contributing

Contributions are welcome! To get started:
1. Fork the repo.
2. Create a feature branch (`git checkout -b feature/add-appointments`).
3. Commit changes (`git commit -m 'Add appointment integration'`).
4. Push to the branch (`git push origin feature/add-appointments`).
5. Open a Pull Request.

Please follow Spring Boot conventions and add tests for new features.

YourUsername (@yourgithub) – Initial Developer.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. (Create a `LICENSE` file with MIT text if not present.)

---

*For issues or questions, open a GitHub issue. Happy coding! 🚀*
