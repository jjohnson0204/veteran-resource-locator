# Veteran Resource Locator API

A RESTful API built with Java and Spring Boot that helps veterans find resources such as benefits, healthcare facilities, and support services.

Built as a portfolio project to demonstrate enterprise-grade backend development practices.

## Tech Stack

- Java 21
- Spring Boot 4
- PostgreSQL
- Docker & Docker Compose
- Maven
- Spring Security
- Spring Data JPA

## Getting Started

### Prerequisites
- Java 21
- Docker Desktop

### Run Locally

1. Clone the repository
```bash
   git clone https://github.com/jjohnson0204/veteran-resource-locator.git
```

2. Start the database
```bash
   docker compose up -d
```

3. Run the application
```bash
   ./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## Project Structure
src/main/java/com/veteranresources/veteranresourcelocator/
├── controller/    # HTTP request handlers
├── service/       # Business logic
├── repository/    # Database access
├── model/         # Database entities
├── dto/           # API request/response objects
└── exception/     # Custom error handling

## Features (In Progress)
- [ ] Veteran resource CRUD operations
- [ ] Search by location and category
- [ ] JWT authentication
- [ ] Integration with VA Facilities API