# Spring Boot AOP Examples

This project demonstrates how to use Spring AOP (Aspect-Oriented Programming) for logging and performance monitoring in a Spring Boot application.

## What is AOP?

Aspect-Oriented Programming (AOP) is a programming paradigm that aims to increase modularity by allowing the separation of cross-cutting concerns. It does so by adding additional behavior to existing code without modifying the code itself.

## Examples Included

This project includes the following AOP examples:

1. **Logging Aspect**: Logs method entry, exit, and exceptions.
2. **Performance Monitoring Aspect**: Monitors and logs method execution time.
3. **Parameter Validation Aspect**: Validates method parameters before execution.
4. **Caching Aspect**: Caches method results to improve performance.

## Project Structure

```
src/main/java/com/example/demo/
├── DemoApplication.java
├── aspect/
│   ├── annotation/
│   │   └── LogExecutionTime.java
│   ├── CachingAspect.java
│   ├── LoggingAspect.java
│   ├── ParameterValidationAspect.java
│   └── PerformanceMonitoringAspect.java
├── controller/
│   └── ExampleController.java
└── service/
    ├── CachingExampleService.java
    └── ExampleService.java
```

## Key Components

### Custom Annotation

- `@LogExecutionTime`: Custom annotation to mark methods for execution time logging.

### Aspects

1. **LoggingAspect**: Uses `@Before`, `@AfterReturning`, and `@AfterThrowing` advice to log method entry, exit, and exceptions.
2. **PerformanceMonitoringAspect**: Uses `@Around` advice to measure and log method execution time.
3. **ParameterValidationAspect**: Uses `@Before` advice to validate method parameters.
4. **CachingAspect**: Uses `@Around` advice to cache method results.

### Pointcuts

The aspects use various pointcuts to target specific methods:

- `execution(* com.example.demo.service.*.*(..))`: Matches all methods in the service package.
- `execution(* com.example.demo.controller.*.*(..))`: Matches all methods in the controller package.
- `@annotation(com.example.demo.aspect.annotation.LogExecutionTime)`: Matches methods annotated with `@LogExecutionTime`.
- `execution(* com.example.demo.service.ExampleService.timeConsumingMethod(..))`: Matches a specific method.

## API Endpoints

- `GET /api/simple`: Simple method with no delay.
- `GET /api/time-consuming`: Method with configurable delay.
- `GET /api/exception`: Method that can throw an exception.
- `GET /api/fibonacci/{n}`: Method that calculates Fibonacci numbers (with caching).

## Running the Application

1. Build the project:
   ```bash
   mvn clean install
   ```

2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

3. Test the endpoints using the provided `test.http` file or with cURL:
   ```bash
   curl http://localhost:8080/api/simple?name=AOP
   curl http://localhost:8080/api/time-consuming?task=LongRunningTask&delay=2000
   curl http://localhost:8080/api/fibonacci/10
   ```

## Logging Output

When you run the application and access the endpoints, you'll see detailed logging output showing:

- Method entry and exit logs
- Parameter values
- Execution time measurements
- Cache hits and misses
- Parameter validation warnings
- Exception details

## Best Practices

1. **Keep aspects focused**: Each aspect should handle a single cross-cutting concern.
2. **Use meaningful pointcuts**: Define precise pointcuts to target only the methods you need.
3. **Be careful with around advice**: `@Around` advice is powerful but can be complex.
4. **Consider performance**: AOP adds some overhead, so use it judiciously.
5. **Test thoroughly**: AOP can introduce subtle bugs, so test your aspects carefully.

## Further Reading

- [Spring AOP Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop)
- [AspectJ Documentation](https://www.eclipse.org/aspectj/doc/released/progguide/index.html) 