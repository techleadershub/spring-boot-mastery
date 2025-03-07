# Spring Boot Method-Level Security Demo

This project demonstrates method-level security in a Spring Boot 3 application using various security annotations and configurations.

## Features

- Spring Boot 3.1.5
- Java 17
- Method-level security using `@PreAuthorize`, `@PostAuthorize`, and `@Secured`
- Role-based access control (RBAC)
- In-memory user authentication
- H2 Database for data storage
- JUnit tests for security scenarios

## Security Implementation

### 1. Method-Level Security Annotations

The application demonstrates three types of security annotations:

- `@PreAuthorize`: Pre-execution authorization check
  ```java
  @PreAuthorize("hasRole('ADMIN')")
  public List<BusinessData> getAllData()
  ```

- `@PostAuthorize`: Post-execution authorization check
  ```java
  @PostAuthorize("returnObject.owner == authentication.name")
  public BusinessData getDataById(Long id)
  ```

- `@Secured`: Simple role-based check
  ```java
  @Secured("ROLE_USER")
  public void updateData(Long id, String newData)
  ```

### 2. Security Configuration

The security configuration (`SecurityConfig.java`) includes:

```java
@EnableMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true
)
```

- Basic authentication enabled
- In-memory user store with USER and ADMIN roles
- Path-based security using `AntPathRequestMatcher`
- H2 Console access configured

## Project Structure

```
src/main/java/com/example/demo/
├── config/
│   └── SecurityConfig.java         # Security configuration
├── controller/
│   └── BusinessController.java     # REST endpoints
├── model/
│   └── BusinessData.java          # Data entity
├── repository/
│   └── BusinessDataRepository.java # JPA repository
└── service/
    └── BusinessService.java       # Secured business logic
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Build and Run
```bash
mvn clean install
mvn spring-boot:run
```

### Default Users
1. Regular User:
   - Username: `user`
   - Password: `user123`
   - Role: `USER`

2. Admin User:
   - Username: `admin`
   - Password: `admin123`
   - Roles: `ADMIN`, `USER`

## API Endpoints

### Public Endpoints
- `GET /api/public` - Accessible to all users

### Protected Endpoints
- `GET /api/user` - Requires USER or ADMIN role
- `GET /api/admin` - Requires ADMIN role
- `GET /api/data/{id}` - Requires ownership of the data
- `POST /api/data` - Requires USER or ADMIN role
- `PUT /api/data/{id}` - Requires USER role
- `DELETE /api/data/{id}` - Requires ADMIN role

## Testing

### Using test.http
The project includes a `test.http` file with sample requests:

```http
### Public Endpoint
GET http://localhost:8080/api/public

### User Endpoint (USER role)
GET http://localhost:8080/api/user
Authorization: Basic dXNlcjp1c2VyMTIz

### Admin Endpoint (ADMIN role)
GET http://localhost:8080/api/admin
Authorization: Basic YWRtaW46YWRtaW4xMjM=
```

### Running Security Tests
The project includes JUnit tests demonstrating security scenarios:

```bash
mvn test
```

Test cases cover:
- Role-based access control
- Data ownership validation
- Authorization failures
- Method-level security annotations

## H2 Database Console

Access the H2 Console at: http://localhost:8080/h2-console

Configuration:
- JDBC URL: `jdbc:h2:mem:demodb`
- Username: `sa`
- Password: `password`

## Security Best Practices Demonstrated

1. **Method-Level Security**
   - Fine-grained access control at service layer
   - Role-based authorization
   - Data ownership validation

2. **Multiple Security Layers**
   - Path-based security at web layer
   - Method security at service layer
   - Data-level security with ownership checks

3. **Proper Configuration**
   - CSRF protection for H2 Console
   - Frame options configured for H2 Console
   - Explicit servlet path handling

## Troubleshooting

1. **Access Denied Errors**
   - Verify user roles match required authorities
   - Check method security annotations
   - Ensure proper authentication headers

2. **H2 Console Access**
   - Verify security configuration allows frame options
   - Check H2 Console URL and credentials
   - Ensure CSRF is properly configured

3. **Authentication Issues**
   - Verify Basic Auth credentials are correctly encoded
   - Check user exists in UserDetailsService
   - Confirm roles are properly assigned

## References

- [Spring Security Reference](https://docs.spring.io/spring-security/reference/index.html)
- [Method Security Documentation](https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
