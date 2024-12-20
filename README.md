# Java - Spring

## My Event

- My Event is an application to manage musical events.

## Technical stack

- This is a maven project.
- It uses HSQLDB as an in-memory database.
- It starts using this maven lifecycle `mvn spring-boot:run` or using the IDE
- The user interface is available at [http://localhost:8080]
- The API resources are available at [http://localhost:8080/api/]

## Context

- The user interface is tested and holds no identified issues.
- We Identified a few things not working on the API.
- Your job is to fix the issues and add a new feature to the API.

## Identified Issues:

```
Please keep track (notes) of how you analysed and fixed the issues to help us
understand the steps during the interview
```

1. Adding review does not work
2. Using the delete button works but elements comes back when i refresh the page

# Summary of Issues and Solutions

## 1. Adding a Review Does Not Work

### Identified Issues:
- The controller method for updating an event is empty and does not handle the incoming request.
- The service does not have any implementation to update an event.

### Entity Relationship Conflicts:
- An error occurs during the update due to the dependency between the `Event` and `Bands` entities.

### Proposed Solutions:
1. **Separate Responsibilities**:
   - Avoid coupling issues caused by relationships.
   - Do not send entities (`Event`, `Bands`) directly in JSON responses.
   - Introduce **DTOs (Data Transfer Objects)** for serialization.
2. **Use Immutable Data Structures**:
   - Use **records** for DTOs to ensure immutability.
3. **Optimize Updates**:
   - Avoid updating the entire entity when only a review or comment is modified.
   - Perform **conditional updates** to modify only the fields that have actually changed.
4. **Independent Update Management**:
   - Create two separate methods for handling review and comment updates independently.
   - Reduce redundant database updates (e.g., avoid triggering both updates when only one is required).

---

## 2. Using the Delete Button Works But Items Reappear After Refreshing the Page

### Identified Issue:
- The `@Transactional(readOnly=true)` annotation is applied at the repository level, which prevents proper deletion.

### Proposed Solution:
1. **Transaction Management**:
   - Move transaction handling to the service layer.
   - Apply the `@Transactional` annotation with write permissions to the `delete` method in the service.
2. **Structural Improvements**:
   - Introduce a **DTO** for the `getEvents` method to separate data returned to the client from internal entity representations.

---

## Key Improvements:
- Separate responsibilities between entities and DTOs.
- Optimize updates using targeted and conditional methods.
- Manage transactions at the service layer instead of the repository layer.
- Use **records** for immutable data structures.
- Introduce a dedicated DTO for events returned by the `getEvents` method.


## New Feature

```
Except for the testing libraries, No library/modules should be added to the dependencies
(use only pure java)
```

1. We would like to enable a new route for the API `/search/{query}`. It will allow us
   to display filtered `events`.
   The events are displayed only if at least one band has a member with the name matching the given
   pattern.

Example: `/search/Wa`

```json
[{
    "title": "GrasPop Metal Meeting",
    "imgUrl": "img/1000.jpeg",
    "bands": [{
        "name": "Metallica",
        "members": [
            {
              "name": "Queen Anika Walsh"
            }
        ]
    },…
}…]
```

2. (BONUS) Add a `[count]` at each event and band
   to display the number of child items.

Example: `/search/Wa`

```json
[{
    "title": "GrasPop Metal Meeting [2]",
    "imgUrl": "img/1000.jpeg",
    "bands": [{
        "name": "Metallica [1]",
        "members": [
            {
              "name": "Queen Anika Walsh"
            }
        ]
    },…
}…]
```

## Requirements

The code must be available in a GIT repository

## Team Appreciation

Team overall appreciation will be based on:

- Code readability, structure and consistency
- Tests, how they are written
- Bonus: usage of Functional concepts


# Improvement Areas

## 1. Simplify Mappings
- Use **MapStruct** or **ModelMapper** to automate and simplify conversions between DTOs and entities.

## 2. Enhance Controller Security
- Validate all user inputs at the controller level to prevent security vulnerabilities such as SQL injection, XSS, and other attacks. Additionally, sanitize inputs and configure HTTP headers appropriately for added security.

## 3. Improve Readability
- Add **Lombok** to reduce boilerplate code like getters, setters, and constructors, improving code clarity.

## 4. Separate Responsibilities
- Split update methods at the interface level to ensure the backend doesn't overly adapt to the frontend.
- Follow the **Dependency Inversion Principle**: high-level rules should not depend on low-level implementation details.

## 5. Architectural Evolution
- Consider adopting **hexagonal** or **modular** architecture to separate technical and business logic.
- This will prepare the system for future scalability and maintainability.

## 6. Enhance HTTP Responses
- Use **ResponseEntity** to include additional metadata in HTTP responses.
- For performance-sensitive use cases, implement **WebFlux** with **Flux** and **Mono** for reactive, non-blocking programming.

## 7. Optimize JPA Relations
- Change JPA relationships from **Eager** loading to **Lazy** loading to prevent unnecessary data fetching.

## 8. Optimize Queries
- Monitor the `getFilteredEvents` method for performance issues in large databases.
- Avoid loading all data into memory and filtering; instead, use efficient SQL queries with clear conditions.

## 9. Manage Responsibilities Effectively
- Handle data filtering in the service layer.
- Perform data mapping in the mapper layer to maintain separation of concerns.

## 10. Add Logging
- Introduce a logging system to track key operations and critical steps.
- This will improve debugging and traceability.

## 11. More tests
- do more functional test.

## 12. More handle exceptions


---

## Summary
These improvements aim to:
- Enhance readability and maintainability.
- Optimize performance for large datasets.
- Better prepare the application for future growth while adhering to clean coding practices.