# Spring Boot 3 Basic Authentication Demo

This project demonstrates Basic Authentication with role-based access control using Spring Boot 3 and Spring Security.

## Features

- Basic Authentication with Spring Security
- Role-based access control
- In-memory user management
- Different access levels for different endpoints

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application using Maven:

```bash
mvn spring-boot:run
```

The application will start on port 8080.

## User Credentials

The application has two predefined users:

1. **Admin User**
   - Username: `admin`
   - Password: `adminpass`
   - Role: `ADMIN`

2. **Regular User**
   - Username: `user`
   - Password: `userpass`
   - Role: `USER`

## Endpoints

The application provides three endpoints with different access levels:

1. `/admin` - Only accessible to users with the `ADMIN` role
2. `/user` - Accessible to users with either the `USER` or `ADMIN` role
3. `/public` - Accessible to everyone, no authentication required

## Testing with cURL

### Public Endpoint (No Authentication)

```bash
curl http://localhost:8080/public
```

### User Endpoint (USER or ADMIN role required)

With USER credentials:
```bash
curl -u user:userpass http://localhost:8080/user
```

With ADMIN credentials:
```bash
curl -u admin:adminpass http://localhost:8080/user
```

### Admin Endpoint (ADMIN role required)

```bash
curl -u admin:adminpass http://localhost:8080/admin
```

Trying to access with USER credentials (should fail):
```bash
curl -u user:userpass http://localhost:8080/admin
```

## Security Configuration

The security configuration is defined in `SecurityConfig.java`. Key points:

- Uses `InMemoryUserDetailsManager` for user management
- Passwords are encoded using `BCryptPasswordEncoder`
- HTTP Basic Authentication is enabled
- URL-based authorization rules are configured

## Notes

- This is a demonstration project and not intended for production use
- In a real-world application, you would use a database for user management
- Passwords should never be stored in plain text in configuration files
- Consider using HTTPS in production to secure Basic Authentication
