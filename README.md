# Spring Boot Security with JWT

A secure Spring Boot application demonstrating JWT-based authentication and authorization, with role-based access control and comprehensive exception handling.

## Features

- 🔐 JWT-based authentication
- 👥 Role-based access control (RBAC)
- 🚫 Custom exception handling
- 🔒 Secure password hashing
- 🍪 HTTP-only cookie-based token storage
- 📝 Request validation
- 🗄️ H2 in-memory database
- 📚 Comprehensive API documentation

## Technologies

- Java 17
- Spring Boot 3.1.5
- Spring Security
- Spring Data JPA
- H2 Database
- JSON Web Token (JWT)
- Maven

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd spring-security-jwt
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`.

### Configuration

Key configuration properties in `application.properties`:

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:h2:mem:jwtdb
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JWT
jwt.secret=your-secret-key
jwt.expiration=86400000  # 24 hours
jwt.cookie.name=jwt-token
```

## API Documentation

### Authentication Endpoints

#### Register User
```http
POST /auth/register
Content-Type: application/json

{
    "username": "user",
    "email": "user@example.com",
    "password": "password123",
    "role": ["user"]
}
```

#### Login
```http
POST /auth/login
Content-Type: application/json

{
    "username": "user",
    "password": "password123"
}
```

#### Logout
```http
POST /auth/logout
```

### Protected Endpoints

#### Public Endpoint (No Auth Required)
```http
GET /api/public
```

#### User Endpoint (Requires Authentication)
```http
GET /api/user
```

#### Admin Endpoint (Requires ADMIN Role)
```http
GET /api/admin
```

### User Management (Admin Only)

#### Get All Users
```http
GET /api/admin/users
```

#### Get User by ID
```http
GET /api/admin/users/{id}
```

#### Update User Roles
```http
PUT /api/admin/users/{id}/roles
Content-Type: application/json

{
    "roles": ["admin", "user"]
}
```

#### Delete User
```http
DELETE /api/admin/users/{id}
```

## Error Handling

The application includes comprehensive exception handling for:

- Authentication failures
- Authorization failures
- Resource not found
- Validation errors
- Duplicate resources
- General server errors

Each error response includes:
- Timestamp
- HTTP status code
- Error message
- Error details
- Request path
- Validation errors (if applicable)

## Testing

Use the provided `test.http` file to test all endpoints. It includes:
1. Authentication flows
2. Protected endpoint access
3. User management operations
4. Error scenarios

## Security Features

1. Password Hashing
   - Secure password hashing using BCrypt

2. JWT Token Security
   - Tokens stored in HTTP-only cookies
   - Automatic token refresh
   - Token invalidation on logout

3. CORS Configuration
   - Configurable CORS policies
   - Protected against CSRF attacks

4. Role-Based Access Control
   - Fine-grained access control
   - Hierarchical roles (ADMIN > USER)

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
