# Spring Security Demo with Form Login and Basic Auth

This project demonstrates the implementation of both Form-based Login and HTTP Basic Authentication in a Spring Boot 3 application using Java 17.

## Features

- **Dual Authentication Methods**:
  - Form-based Login for web browser access
  - HTTP Basic Authentication for API access
- **Role-based Access Control**
- **H2 In-memory Database**
- **Thymeleaf Templates with Bootstrap UI**
- **Comprehensive Security Configuration**

## Technical Stack

- Java 17
- Spring Boot 3.1.5
- Spring Security
- Spring Data JPA
- H2 Database
- Thymeleaf
- Bootstrap 5
- Maven

## Project Structure

```
src/main/java/com/example/security/
├── SecurityDemoApplication.java
├── config/
│   └── SecurityConfig.java
├── controller/
│   └── AuthController.java
├── entity/
│   └── User.java
├── repository/
│   └── UserRepository.java
└── service/
    └── CustomUserDetailsService.java
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

## Default Users

The application comes with two pre-configured users:

1. Regular User:
   - Username: `user`
   - Password: `password`
   - Role: `USER`

2. Admin User:
   - Username: `admin`
   - Password: `password`
   - Roles: `USER`, `ADMIN`

## Available Endpoints

### Web Endpoints (Form Login)
- `GET /login` - Login page
- `GET /home` - Home page (authenticated)
- `GET /public` - Public endpoint
- `GET /user` - User endpoint (authenticated)
- `GET /admin` - Admin endpoint (ADMIN role)
- `POST /logout` - Logout

### API Endpoints (Basic Auth)
- `GET /api/public` - Public API endpoint
- `GET /api/secure` - Secured API endpoint (authenticated)

## Security Features

1. **Form Login Configuration**:
   - Custom login page
   - Success/failure handlers
   - Remember-me functionality
   - CSRF protection

2. **Basic Auth Configuration**:
   - Stateless session management
   - Basic authentication for API endpoints
   - CSRF disabled for API endpoints

3. **Security Measures**:
   - Password encryption using BCrypt
   - Role-based access control
   - Session management
   - CSRF protection
   - XSS protection

## Testing

1. **Web Application Testing**:
   - Open `http://localhost:9000` in a browser
   - Navigate to the login page
   - Use provided credentials to log in
   - Try accessing different endpoints based on roles

2. **API Testing**:
   Use the provided `test.http` file or cURL commands:

   ```bash
   # Public endpoint
   curl http://localhost:9000/api/public

   # Secured endpoint (Basic Auth)
   curl -u user:password http://localhost:9000/api/secure
   ```

## Development

### H2 Console
- Available at: `http://localhost:9000/h2-console`
- JDBC URL: `jdbc:h2:mem:authdb`
- Username: `sa`
- Password: ` ` (empty)

### Logging
- Security debug logs enabled
- SQL query logs enabled

## License

This project is licensed under the MIT License.
