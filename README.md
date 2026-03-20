Todo SDD Backend — README

Overview
- This is a small Spring Boot backend implemented as part of a spec-driven development (SDD) exercise.
- Implemented a minimal POST /todos endpoint driven by the OpenAPI spec in todo-sdd/specs/api.yml.
- Storage is in-memory (thread-safe List + AtomicLong id generator).

What I added/changed
- Controller
  - src/main/java/com/todo/todo_sdd_backend/controller/TodoController.java
    - POST /todos: accepts JSON { "title": "..." }, validates title, returns 201 with the created Todo.
- Request DTO
  - src/main/java/com/todo/todo_sdd_backend/dto/CreateTodoRequest.java
    - Field: title (validated with @NotBlank).
- Model
  - src/main/java/com/todo/todo_sdd_backend/model/Todo.java
    - Fields: id (Long), title (String), completed (boolean), createdAt (Instant).
    - Factory method Todo.createNew(id, title) ensures createdAt is set to Instant.now().
- Service
  - src/main/java/com/todo/todo_sdd_backend/service/TodoService.java
    - In-memory store using Collections.synchronizedList(new ArrayList<>()) and AtomicLong id generator.
- OpenAPI spec files
  - todo-sdd/specs/api.yml — authoritative API spec used for the exercise.
  - todo-sdd/specs/models.yaml — model definitions for Todo.
- Build / dependencies
  - pom.xml updated with:
    - org.springframework.boot:spring-boot-starter-web
    - org.springframework.boot:spring-boot-starter-validation
    - lombok (optional)
    - (springdoc-openapi dependency was added earlier and is available; you can enable it to expose Swagger UI.)
  - maven-compiler-plugin configured to use the project java.version (17).
- Configuration
  - src/main/resources/application.properties contains springdoc.swagger-ui.url=/api.yml so the UI loads the provided spec.

How to build and run
1. Build:
   mvn -DskipTests package
2. Run:
   mvn spring-boot:run
   or
   java -jar target/todo-sdd-backend-0.0.1-SNAPSHOT.jar
3. Test POST /todos (example):
   curl -i -X POST http://localhost:8080/todos \
     -H "Content-Type: application/json" \
     -d '{"title":"Buy milk"}'
   Expected response: HTTP/1.1 201 Created with JSON body containing id, title, completed, createdAt.

OpenAPI / Swagger UI
- The app serves the bundled spec at: http://localhost:8080/api.yml
- To enable Swagger UI:
  - Add or enable the springdoc dependency in pom.xml: org.springdoc:springdoc-openapi-starter-webmvc-ui
  - Start the app and open: http://localhost:8080/swagger-ui/index.html
  - The UI is configured to load /api.yml so it will reflect the spec-driven contract.

Notes and recommendations
- The current implementation stores data in memory and is not persistent. For production or more realistic testing, replace the storage with a database (H2, Postgres) and a Repository layer.



