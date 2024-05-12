Sample Store Project
This repository contains a Spring Boot application for managing a store with basic CRUD (Create, Read, Update, Delete) operations for products. The application uses Spring Security for role-based access controls.

Features
CRUD Operations: Supports creating, reading, updating, and deleting products.
Role-Based Access Control: There are two roles:
Admin: Can perform all CRUD operations.
Customer: Can only read product information.
API Endpoints
The application provides several RESTful endpoints under /products:

Public Endpoints
GET /products: Retrieves all products. Available to Admins and Customers.

Admin-Only Endpoints:

POST /products: Creates a new product. Only for Admins.
PUT /products/{id}: Updates an existing product by ID. Only for Admins.
DELETE /products/{id}: Deletes a product by ID. Only for Admins.
Security
The API uses HTTP Basic Authentication to secure the endpoints based on user roles:

Admin Role: Access to all endpoints.
Customer Role: Limited to viewing products.

Swagger UI
Access the Swagger UI for this project at http://localhost:8080/swagger-ui/index.html to view and interact with the API documentation.

Prerequisites
JDK 17
Maven
MySQL
