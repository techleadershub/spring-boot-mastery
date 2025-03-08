# Spring Boot API Key Authentication Demo

This project demonstrates how to implement API Key Authentication in a Spring Boot 3 application using Java 17. It provides a secure way to authenticate API clients using API keys stored in a database.

## Features

- API Key Authentication for securing REST APIs
- API Key generation, validation, and revocation
- Role-based access control (ADMIN and API_USER roles)
- Secure storage of API keys in a database
- API Key expiration and rotation

## Understanding API Key Authentication

### What is API Key Authentication?

API Key Authentication is a simple authentication scheme where clients include a secret key in their requests to identify themselves. The server validates this key against a stored value to authenticate the client.

### When to Use API Key Authentication

API Key Authentication is suitable for:
- Server-to-server communication
- Internal APIs with trusted clients
- Public APIs with rate limiting requirements
- Scenarios where OAuth2 or JWT might be too complex

### API Keys vs. JWT & OAuth2

| Feature | API Keys | JWT | OAuth2 |
|---------|----------|-----|--------|
| Complexity | Low | Medium | High |
| Stateless | No (requires DB lookup) | Yes | No (requires token storage) |
| Revocation | Easy | Difficult | Easy |
| Expiration | Configurable | Built-in | Configurable |
| Use Case | Simple APIs, internal services | Stateless authentication | Complex authorization scenarios |

### Security Risks and Best Practices

1. **API Key Leakage**: API keys should be transmitted securely (HTTPS) and never exposed in client-side code or URLs.
2. **Storage**: Store API keys securely using hashing in production environments.
3. **Rotation**: Implement key rotation to limit the impact of compromised keys.
4. **Expiration**: Set expiration dates for API keys to limit their lifetime.
5. **Monitoring**: Monitor API key usage for suspicious activity.
6. **Rate Limiting**: Implement rate limiting to prevent abuse.

## Project Structure

```
src/main/java/com/example/apikeyauth/
├── ApiKeyAuthApplication.java
├── controller/
│   ├── AdminController.java
│   └── ApiController.java
├── dto/
│   ├── ApiKeyRequest.java
│   └── ApiKeyResponse.java
├── exception/
│   └── GlobalExceptionHandler.java
├── model/
│   └── ApiKey.java
├── repository/
│   └── ApiKeyRepository.java
├── security/
│   ├── ApiKeyAuthFilter.java
│   └── SecurityConfig.java
└── service/
    └── ApiKeyService.java
```

## API Endpoints

### Public Endpoints
- `GET /public` - Accessible without authentication

### Secure Endpoints (Requires API Key)
- `GET /secure` - Requires a valid API key

### Admin Endpoints (Requires Admin API Key)
- `GET /admin/api-keys` - List all API keys
- `POST /admin/generate-key` - Generate a new API key
- `PUT /admin/api-keys/{keyValue}/revoke` - Revoke an API key
- `PUT /admin/api-keys/{keyValue}/expiration` - Update API key expiration

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Building the Application
```bash
mvn clean install
```

### Running the Application
```bash
mvn spring-boot:run
```

The application will start on port 8080 by default.

## Testing the API

You can use the provided `test.http` file to test the API endpoints. If you're using Visual Studio Code, you can install the "REST Client" extension to execute the requests directly from the editor.

### Using cURL

#### Public Endpoint
```bash
curl -X GET http://localhost:8080/public
```

#### Secure Endpoint with API Key
```bash
curl -X GET http://localhost:8080/secure -H "X-API-KEY: your-api-key-here"
```

#### Generate New API Key (Admin Only)
```bash
curl -X POST http://localhost:8080/admin/generate-key \
  -H "Content-Type: application/json" \
  -H "X-API-KEY: ADMIN-API-KEY-SECRET-123" \
  -d '{"name":"Test API Key","validityInDays":30}'
```

## Configuration

The application can be configured through the `application.properties` file:

```properties
# API Key Configuration
api.key.header.name=X-API-KEY
api.key.admin.value=ADMIN-API-KEY-SECRET-123
```

## Database

The application uses an H2 in-memory database by default. For production, you can switch to PostgreSQL by uncommenting the relevant properties in `application.properties`.

## Key Takeaways

### When to Use API Keys vs JWT/OAuth2
- Use API Keys for simple authentication scenarios, internal APIs, or when client simplicity is important.
- Use JWT for stateless authentication where token validation doesn't require a database lookup.
- Use OAuth2 for complex authorization scenarios, especially when dealing with third-party applications.

### Best Practices for API Key Management
1. Use HTTPS for all API communications
2. Store API keys securely (hash them in production)
3. Implement key rotation and expiration
4. Monitor API key usage
5. Implement rate limiting
6. Use different API keys for different environments

### Preventing API Key Leaks & Abuse
1. Never expose API keys in client-side code
2. Don't include API keys in URLs
3. Set appropriate permissions for each API key
4. Implement IP restrictions when possible
5. Log and alert on suspicious activity
6. Educate developers about secure API key handling

## License

This project is licensed under the MIT License. 