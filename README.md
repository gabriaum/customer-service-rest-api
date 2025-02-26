# Spring Service API

This project implements a Spring Boot API to manage users and support tickets. The project uses a relational database to store information about users and tickets.

## Project Structure

### 1. **Entities**
The project uses the following main entities:

- **User**: Represents the system's users with attributes such as `email`, `password`, `firstName`, `lastName`, and `role`.
- **Ticket**: Represents support tickets with attributes such as `message`, `response`, and relationships with users who created and responded to the ticket.

### 2. **APIs**
The API offers endpoints to manage users and tickets. The APIs are divided into two controllers:

#### **UserAPI**
- **POST /api/user/register**: Registers a new user.
- **PUT /api/user/update/{id}**: Updates an existing user's information.
- **DELETE /api/user/delete/{id}**: Deletes a user.
- **GET /api/user/email/{email}**: Retrieves a user by email.
- **GET /api/user/id/{id}**: Retrieves a user by ID.
- **GET /api/user**: Retrieves all registered users.

#### **TicketAPI**
- **POST /api/ticket/create/{secretId}**: Creates a new ticket with the provided message.
- **PUT /api/ticket/reply/{secretId}/{ticketId}**: Replies to a ticket. Only users with the "SUPPORTER" role can reply.
- **POST /api/ticket/close/{secretId}/{ticketId}**: Closes a ticket.
- **GET /api/ticket/user/{secretId}**: Lists all tickets for the user with the provided `secretId`.
- **GET /api/ticket**: Lists all tickets.

## Technologies Used

- **Spring Boot**: The main framework for building the API.
- **JPA (Hibernate)**: For data persistence in the relational database.
- **Lombok**: To reduce boilerplate code.
- **PostgreSQL/MySQL**: Relational database.

## Running the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/gabriaum/customer-service-rest-api.git
   ```
2. Navigate to the project directory:
   ```bash
    cd customer-service-rest-api
    ```
3. Build the project:
   ```bash
   ./mvnw spring-boot:run
   ```

# Accessing the API
Once the server is running, you can access the endpoints through HTTP requests. Below are some example requests using tools like Postman or CURL.
## Register User
* Endpoint: POST /api/user/register 

Request Body:
```json
{
"email": "user@example.com",
"password": "password123",
"firstName": "First",
"lastName": "Last",
"role": "CLIENT or SUPPORT"
}
```

## Create Ticket
* Endpoint: POST /api/ticket/create/{secretId}

Request Body: String

## Reply to Ticket
* Endpoint: PUT /api/ticket/reply/{secretId}/{ticketId}

Request Body:
```json
{
"message": "I am investigating the issue and will have a solution soon."
}
```

## Close Ticket
* Endpoint: POST /api/ticket/close/{secretId}/{ticketId}

Request Body: None (Ticket will be closed by the user with the correct role).

## Get User by ID
* Endpoint: GET /api/user/id/{id}

Response:
```json
{
"id": 1,
"email": "user@example.com",
"firstName": "First",
"lastName": "Last",
"role": "USER"
}
```

## Error Responses
The API returns standardized error responses when things go wrong. Example:

```json
{
"message": "User not found",
"status": 404
}
```

# Technologies Used

* Spring Boot: The main framework for building the API.
* Spring Data JPA: For ORM and database interactions.
* PostgreSQL/MySQL: Relational database management system.
* Lombok: Reduces boilerplate code (e.g., getters, setters, constructors).
* Spring Boot Starter Web: For building RESTful APIs.
* Spring Boot Starter Data JPA: For database interactions.
* Spring Boot Starter Validation: For validating request bodies.
* Lombok: For reducing boilerplate code.
* Spring Boot Starter Test: For testing the API.

Thanks :D