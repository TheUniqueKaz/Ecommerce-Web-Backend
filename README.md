#  E-commerce Web Backend API

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-green?style=for-the-badge&logo=spring-boot)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-lightgrey?style=for-the-badge&logo=hibernate)

## 📖 Introduction

This is a robust **RESTful API Backend** for an E-commerce platform, designed to handle core business operations such as user management, product inventory, shopping cart functionality, and order processing.

The project is architected using **Java Spring Boot** following the **Layered Architecture (MVC)** pattern to ensure scalability, maintainability, and clean code principles.

##  Tech Stack

* **Core:** Java 17, Spring Boot.
* **Database:** MySQL, Hibernate/JPA.
* **Security:** Spring Security, JWT (JSON Web Token).
* **Build Tool:** Maven.
* **Testing:** Postman.
* **Version Control:** Git & GitHub.

##  Key Features

* **User Authentication:** Secure registration and login using JWT Authentication.
* **Product Management:** CRUD operations (Create, Read, Update, Delete) for products.
* **Shopping Cart:** Add items, update quantities, remove items, and view cart details.
* **Order Processing:** Place orders and manage order history.
* **Error Handling:** Global exception handling for consistent API responses.

##  Architecture

The project follows a modular **Layered Architecture**:
1.  **Controller Layer:** Handles HTTP requests and responses.
2.  **Service Layer:** Contains business logic and transaction management.
3.  **Repository Layer (DAO):** Interacts with the MySQL database using Spring Data JPA.
4.  **Model/Entity:** Represents database tables.

##  Installation & Setup

Follow these steps to run the project locally:

### 1. Prerequisites
* Java Development Kit (JDK) 17 or higher.
* Maven.
* MySQL Server.

### 2. Clone the Repository
```bash
git clone [https://github.com/TheUniqueKaz/Ecommerce-Web-Backend.git](https://github.com/TheUniqueKaz/Ecommerce-Web-Backend.git)
cd Ecommerce-Web-Backend
