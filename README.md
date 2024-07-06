# Library System

A simple library management system API built with Spring Boot and Maven.

## Features

- Register a new borrower to the library.
- Register a new book to the library.
- Get a list of all books in the library.
- Borrow a book with a particular book id.
- Return a borrowed book.

## Data Models

### Borrower
- `id`: Unique identifier.
- `name`: Name of the borrower.
- `email`: Email address of the borrower.

### Book
- `id`: Unique identifier.
- `isbn`: ISBN number of the book.
- `title`: Title of the book.
- `author`: Author of the book.
## Prerequisites

- JDK 17 or higher
- Docker (optional, for containerization)
- Maven (if not using the provided Maven Wrapper)

## Build and Run the Project

### Using Maven Wrapper

You can use the provided Gradle Wrapper to build and run the project.

#### Build the Project

```bash
./mvnw clean package

#### Run the Project

./mvnw spring-boot:run
```

### Build the Docker Image
1. Ensure that Docker is installed and running on your machine.
2. Build the Docker image using the following command:
```bash
docker build -t library-service .
```

### Accessing Swagger UI
Once the application is running, you can access the API documentation using Swagger UI:

[Swagger UI](http://localhost:9090/swagger-ui/index.html)

![Library System Swagger_UI](https://github.com/Gajendrasinh/library-management-system/blob/master/Swagger-UI.png)

### Postman Collection
For easier API testing, import the following Postman collection:

[Library System API Postman Collection](https://github.com/Gajendrasinh/library-management-system/blob/master/library-system.postman_collection.json)

![Library System Postman_Collection](https://github.com/Gajendrasinh/library-management-system/blob/master/Postman-Collection.png)
