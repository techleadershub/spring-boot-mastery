# Spring Boot JWT Authentication Demo

This project demonstrates a secure implementation of JWT (JSON Web Token) authentication in a Spring Boot 3 application using Java 17.

## Features

- Spring Boot 3.1.5
- Java 17
- JWT Authentication
- Role-Based Access Control (RBAC)
- In-Memory User Management
- Custom JWT Authentication Filter
- Exception Handling
- H2 Database Console Access

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Your favorite IDE (IntelliJ IDEA, Eclipse, VS Code)

## Project Structure

```
src/main/java/com/example/
├── demo/
│   └── DemoApplication.java
└── security/
    ├── config/
    │   └── SecurityConfig.java
    ├── controller/
    │   └── AuthController.java
    ├── dto/
    │   ├── LoginRequest.java
    │   └── TokenResponse.java
    ├── exception/
    │   └── JwtAuthExceptionHandler.java
    └── jwt/
        ├── JwtAuthenticationFilter.java
        └── JwtUtils.java
```

## Quick Start

1. Clone the repository:
```bash
git clone <repository-url>
cd jwt-auth-demo
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Security Configuration

The security configuration is defined in `SecurityConfig.java`:

- JWT Authentication Filter
- BCrypt Password Encoder
- Stateless Session Management
- Role-Based Access Control
- In-Memory User Store with two default users:
  - Username: "user", Password: "password", Role: USER
  - Username: "admin", Password: "password", Roles: USER, ADMIN

## API Endpoints

### Authentication

```http
POST /auth/login
Content-Type: application/json

{
    "username": "user",
    "password": "password"
}
```

### Protected Resources

- `GET /auth/test` - Public endpoint (no authentication required)
- `GET /auth/user` - Protected endpoint (requires authentication)
- `GET /auth/admin` - Admin endpoint (requires ADMIN role)

## Testing the API

You can use the provided `test.http` file to test the endpoints. Here's how:

1. **Login to get JWT token:**
```http
POST http://localhost:8080/auth/login
Content-Type: application/json

{
    "username": "user",
    "password": "password"
}
```

2. **Use the token in subsequent requests:**
```http
GET http://localhost:8080/auth/user
Authorization: Bearer <your-jwt-token>
```

## Error Handling

The application includes comprehensive error handling for:

- Invalid JWT tokens
- Expired tokens
- Authentication failures
- Authorization failures
- Invalid requests

Error responses include:
- HTTP status code
- Error message
- Timestamp
- Path
- Error details

## Security Best Practices Implemented

1. **JWT Security:**
   - Secure secret key management
   - Token expiration
   - Signature validation
   - Role-based claims

2. **Authentication:**
   - Password encoding with BCrypt
   - Stateless session management
   - Custom authentication filter

3. **Authorization:**
   - Role-based access control
   - Method-level security
   - Protected endpoints

4. **Error Handling:**
   - Comprehensive exception handling
   - Secure error messages
   - Proper logging

## Configuration

Key application properties (in `application.properties`):

```properties
# Server Configuration
server.port=8080

# JWT Configuration
jwt.secret=your-256-bit-secret-key-for-jwt-token-generation-and-validation
jwt.expiration=86400000

# H2 Console (for development)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## Development

To add new protected endpoints:

1. Create a new controller method
2. Add appropriate security annotations
3. Configure path security in `SecurityConfig`

Example:
```java
@GetMapping("/protected")
@PreAuthorize("hasRole('USER')")
public ResponseEntity<?> protectedEndpoint() {
    // Your code here
}
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
