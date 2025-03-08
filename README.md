# Spring Security with Database-Backed User Management

This project demonstrates a secure Spring Boot 3 application with database-backed user management, role-based access control, and secure authentication.

## Features

- **User Management**:
  - Database-backed user storage
  - Secure password hashing with BCrypt
  - Role-based access control (USER and ADMIN roles)
  - User registration and authentication
- **Security**:
  - Form-based authentication
  - Role-based authorization
  - Password encryption
  - Session management
- **Database**:
  - H2 in-memory database (development)
  - JPA/Hibernate integration
  - Automatic schema generation

## Technical Stack

- Java 17
- Spring Boot 3.1.5
- Spring Security
- Spring Data JPA
- H2 Database
- Maven

## Project Structure

```
src/main/java/com/example/security/
├── SecurityDemoApplication.java
├── config/
│   └── SecurityConfig.java
├── controller/
│   └── UserController.java
├── entity/
│   └── User.java
├── repository/
│   └── UserRepository.java
├── service/
│   ├── UserService.java
│   └── CustomUserDetailsService.java
└── dto/
    ├── UserRegistrationRequest.java
    ├── UserResponse.java
    └── LoginRequest.java
```

## Getting Started

1. Clone the repository
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the application at `http://localhost:9000`

## API Endpoints

### Authentication Endpoints
```
POST /auth/register - Register a new user
POST /auth/login    - Authenticate user
POST /auth/logout   - Logout user
```

### User Management Endpoints
```
GET    /users/me           - Get current user details
GET    /users             - List all users (ADMIN only)
GET    /users/{id}        - Get user by ID (ADMIN only)
PUT    /users/{id}/roles  - Update user roles (ADMIN only)
DELETE /users/{id}        - Delete user (ADMIN only)
```

## User Registration

To register a new user, send a POST request to `/auth/register`:

```json
{
    "username": "user1",
    "email": "user1@example.com",
    "password": "password123",
    "roles": ["USER"]
}
```

## Authentication

To authenticate, send a POST request to `/auth/login`:

```json
{
    "username": "user1",
    "password": "password123"
}
```

## Database Configuration

### Development (H2)
```properties
spring.datasource.url=jdbc:h2:mem:userdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

### Production (PostgreSQL)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/userdb
spring.datasource.username=postgres
spring.datasource.password=your_password
```

## Security Features

1. **Password Security**:
   - BCrypt password hashing
   - Configurable password strength
   - Password validation rules

2. **Role-Based Access**:
   - USER role for basic access
   - ADMIN role for management functions
   - Method-level security with @PreAuthorize

3. **Database Security**:
   - Prepared statements prevent SQL injection
   - Encrypted password storage
   - No sensitive data exposure

## Testing

Use the provided `test.http` file for API testing. Example tests include:

1. User Registration:
   - Register new users
   - Test duplicate username handling
   - Validate password requirements

2. Authentication:
   - Login with valid credentials
   - Test invalid credentials
   - Test token expiration

3. User Management:
   - Create/Read/Update/Delete users
   - Role management
   - Access control testing

## Development

### H2 Console
- Available at: `http://localhost:9000/h2-console`
- JDBC URL: `jdbc:h2:mem:userdb`
- Username: `sa`
- Password: ` ` (empty)

### Logging
- Security debug logs enabled
- SQL query logs enabled
- JPA statistics logging

## Error Handling

The application includes comprehensive error handling for:
- Invalid credentials
- Duplicate usernames
- Password validation
- Role validation
- Database constraints
- Authorization failures

## Best Practices

1. **Security**:
   - No password storage in plain text
   - Role-based access control
   - Input validation
   - Secure session management

2. **Database**:
   - Connection pooling
   - Prepared statements
   - Transaction management
   - Index optimization

3. **API Design**:
   - RESTful endpoints
   - Proper HTTP status codes
   - Comprehensive error responses
   - Input validation

## License

This project is licensed under the MIT License.
