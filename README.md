# Spring 6 Dependency Injection Practice

This project demonstrates various Dependency Injection (DI) techniques in Spring 6. It's designed as a learning resource to understand the core concepts of DI in Spring Framework.

## Concepts Covered

1. **Types of Dependency Injection**
   - Constructor Injection (recommended approach)
   - Setter Injection
   - Field Injection (not recommended for production)

2. **Bean Configuration Methods**
   - Java Configuration with `@Configuration` and `@Bean`
   - Component Scanning with `@Component`, `@Service`, `@Repository`, etc.
   - XML Configuration (legacy approach)

3. **Dependency Resolution**
   - Autowiring by Type
   - Autowiring by Name
   - Qualifier annotations
   - Primary beans

4. **Bean Scopes**
   - Singleton (default)
   - Prototype
   - Request, Session, etc.

5. **Bean Lifecycle**
   - Initialization and Destruction callbacks
   - `@PostConstruct` and `@PreDestroy` annotations

6. **Profiles**
   - Environment-specific beans with `@Profile`

## Project Structure

```
src/main/java/com/example/springdi/
├── config/                  # Configuration classes
├── model/                   # Domain model classes
├── repository/              # Data access layer
├── service/                 # Business logic layer
└── SpringDiApplication.java # Main application class
```

## How to Run

1. Clone this repository
2. Build the project: `mvn clean package`
3. Run the application: `java -jar target/spring-di-practice-1.0-SNAPSHOT.jar`

## Requirements

- Java 17 or higher
- Maven 3.6 or higher 