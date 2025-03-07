# Spring Boot OAuth2 with Keycloak

This project demonstrates a secure Spring Boot application using Keycloak for OAuth2/OpenID Connect authentication and authorization.

## Features

- Spring Boot 3.1.5
- Spring Security OAuth2 Resource Server
- Keycloak Integration
- Role-Based Access Control (RBAC)
- H2 Database Console
- RESTful API Endpoints

## Prerequisites

- Java 17 or higher
- Docker (for running Keycloak)
- Maven

## Setup Instructions

### 1. Start Keycloak

```bash
docker run -p 8180:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:22.0.4 start-dev
```

### 2. Configure Keycloak

1. Access Keycloak Admin Console: http://localhost:8180
2. Login with admin/admin
3. Create a new realm: `my-realm`
4. Create a new client:
   - Client ID: `spring-boot-client`
   - Client Protocol: `openid-connect`
   - Access Type: `public`
   - Valid Redirect URIs: `http://localhost:8080/*`
5. Create roles:
   - `USER`
   - `ADMIN`
6. Create users:
   - User 1:
     - Username: `user1`
     - Password: `user1pass`
     - Assign Role: `USER`
   - Admin:
     - Username: `admin1`
     - Password: `admin1pass`
     - Assign Roles: `ADMIN`, `USER`

### 3. Configure Application

The application is configured with the following properties in `application.properties`:

```properties
# Keycloak Configuration
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8180/realms/my-realm
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=${spring.security.oauth2.resourceserver.jwt.issuer-uri}/protocol/openid-connect/certs
```

### 4. Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

## API Endpoints

### Public Endpoint
- `GET /api/public`
- No authentication required
- Returns a welcome message

### User Endpoint
- `GET /api/user`
- Requires `USER` or `ADMIN` role
- Returns user information and roles

### Admin Endpoint
- `GET /api/admin`
- Requires `ADMIN` role
- Returns admin information and token details

## Testing

You can use the provided `test.http` file to test the endpoints. Here's how to get started:

1. Get User Token:
```http
POST http://localhost:8180/realms/my-realm/protocol/openid-connect/token
Content-Type: application/x-www-form-urlencoded

grant_type=password&client_id=spring-boot-client&username=user1&password=user1pass
```

2. Get Admin Token:
```http
POST http://localhost:8180/realms/my-realm/protocol/openid-connect/token
Content-Type: application/x-www-form-urlencoded

grant_type=password&client_id=spring-boot-client&username=admin1&password=admin1pass
```

3. Test Endpoints:
- Replace `{{user_token}}` and `{{admin_token}}` in `test.http` with the actual tokens
- Use the HTTP client to send requests to the endpoints

## Security Configuration

The security configuration (`SecurityConfig.java`) includes:

- JWT authentication using Keycloak's JWK Set
- Role-based authorization
- Stateless session management
- CSRF protection disabled for API endpoints
- H2 Console security configuration
- Custom JWT authority converter for role mapping

## Additional Features

### H2 Database Console
- Available at: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:bookdb
- Username: sa
- Password: password

## Troubleshooting

1. Token Issues:
   - Ensure Keycloak is running and accessible
   - Verify client configuration in Keycloak
   - Check token expiration and roles

2. Access Denied:
   - Verify user has required roles
   - Check role mapping in SecurityConfig
   - Ensure token contains correct role claims

3. H2 Console Access:
   - Verify security configuration allows H2 Console frame display
   - Check correct database credentials

## References

- [Spring Security OAuth2 Documentation](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html)
- [Keycloak Documentation](https://www.keycloak.org/documentation)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
