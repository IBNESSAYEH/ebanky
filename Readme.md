Spring Boot Banking Application
A robust and secure banking application built with Spring Boot, offering comprehensive banking services with a modular RESTful architecture.

🌟 Features
👥 Multi-Role Authentication & Authorization

Role-based access control (ADMIN, USER, EMPLOYEE)
Secure password encryption with BCrypt
JWT-based authentication
Fine-grained permission management

💰 Account Management

Multiple account types support
Real-time balance tracking
Account status monitoring
Transaction history

💸 Transaction Services

Instant transfers
Scheduled/recurring transactions
Inter-bank transfers with fee calculation
Transaction approval workflow for high-value transfers

💳 Loan Management

Loan application processing
Automated eligibility assessment
Payment scheduling
Interest calculation

📊 Billing System

Bill generation and tracking
Payment status monitoring
Due date management

🔧 Technical Stack
Backend

Framework: Spring Boot 3.1.x
Security: Spring Security with JWT
Database: MySQL/PostgreSQL
ORM: Spring Data JPA
Migration: Liquibase
Build Tool: Maven
Documentation: SpringDoc OpenAPI (Swagger)

Development Tools

Lombok: Reduces boilerplate code
MapStruct: Object mapping
JUnit 5: Testing framework
SonarQube: Code quality analysis

📋 Prerequisites

JDK 17 or later
Maven 3.8.x
MySQL 8.0 or PostgreSQL 12+
Git

🚀 Quick Start

Clone the repository

bashCopygit clone https://github.com/IBNESSAYEH/ebanky.git
cd banking-application

Configure database

propertiesCopy# src/main/resources/application.yml
spring:
datasource:
url: jdbc:mysql://localhost:3306/banking_db
username: your_username
password: your_password

Build the application

bashCopymvn clean install

Run the application

bashCopymvn spring-boot:run
The application will be available at http://localhost:8080
📚 API Documentation
Once the application is running, you can access the API documentation at:

Swagger UI: http://localhost:8080/swagger-ui.html
OpenAPI Spec: http://localhost:8080/v3/api-docs

🔒 Security
Role Hierarchy

ADMIN: Full system access
EMPLOYEE: Customer service and transaction approval
USER: Personal banking operations

Authentication

JWT-based authentication
Token expiration management
Refresh token support

🧪 Testing
bashCopy# Run unit tests
mvn test

# Run integration tests
mvn verify

# Generate test coverage report
mvn jacoco:report
📊 Database Schema
The application uses Liquibase for database version control. Key tables include:

Users & Roles
Accounts
Transactions
Loans
Bills

🛠️ Development
Code Style

Follow Google Java Style Guide
Use Lombok annotations
Implement builder pattern
Use MapStruct for DTOs

Best Practices

Exception handling via @ControllerAdvice
Request validation using Bean Validation
Comprehensive logging
Unit test coverage > 80%

🤝 Contributing

Fork the repository
Create your feature branch (git checkout -b feature/AmazingFeature)
Commit your changes (git commit -m 'Add some AmazingFeature')
Push to the branch (git push origin feature/AmazingFeature)
Open a Pull Request

📝 License
This project is licensed under the MIT License - see the LICENSE.md file for details
👥 Authors

Your Name - Initial work - YourGithub

🙏 Acknowledgments

Spring Boot Documentation
Spring Security Reference
Banking System Design Patterns