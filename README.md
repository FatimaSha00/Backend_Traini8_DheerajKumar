# Backend_Traini8_DheerajKumar

🏫 Training Center API

🚀 Overview
The Training Center API is a RESTful service built with Spring Boot, enabling users to manage training centers. It provides features for adding new centers, retrieving existing ones, and ensuring data validation with robust exception handling.

📜 Features
✅ Create a new training center
✅ Retrieve all training centers
✅ Input validation with @Valid
✅ Exception handling for duplicate centers
✅ Unit testing with JUnit & Mockito

🏗️ Technologies Used
Java 17+
Spring Boot
Spring Data JPA
MySQL
Hibernate
Jackson (for JSON processing)
JUnit 5 & Mockito (for testing)
Postman (for API testing)


## ⚙️ Installation & Setup

### 🔹 **1. Clone the Repository**
```bash
git clone https://github.com/your-username/training-center-api.git
cd training-center-api

###🔹 **2. Configure Database**
spring.datasource.url=jdbc:mysql://localhost:3306/training_center_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update


###🔹 **3. Build and Run the Project**
mvn clean install
mvn spring-boot:run


📌 API Endpoints
HTTP Method	    Endpoint	                 Description
POST	    /training-centers	        Create a new training center
GET	        /getAll-training-centers	    Retrieve all training centers

📝 Request Example: Create Training Center
POST /training-centers
Request Body:
{
    "centerName": "BABA JI Institute",
    "centerCode": "ABC123XY8387",
    "address": {
        "detailedAddress": "123 Main Street",
        "city": "Delhi",
        "state": "Delhi",
        "pincode": "110001"
    },
    "studentCapacity": 100,
    "coursesOffered": [
        "Java",
        "Spring Boot"
    ],
    "createdOn": 1710000000, // User tries to send this, but it should be ignored
    "contactEmail": "contact@techcenter.com",
    "contactPhone": "9876543210"
}

Response:
{
    "centerName": "BABA JI Institute",
    "centerCode": "ABC123XY8387",
    "address": {
        "detailedAddress": "123 Main Street",
        "city": "Delhi",
        "state": "Delhi",
        "pincode": "110001"
    },
    "studentCapacity": 100,
    "coursesOffered": [
        "Java",
        "Spring Boot"
    ],
    "createdOn": 1741688735745,
    "contactEmail": "contact@techcenter.com",
    "contactPhone": "9876543210"
}

📝 Request Example: Get All Training Center
GET /getAll-training-centers
Response:
[
    {
        "centerName": "Tech Training Hub",
        "centerCode": "ABC123XYZ779",
        "address": {
            "detailedAddress": "123 Main Street",
            "city": "Delhi",
            "state": "Delhi",
            "pincode": "110001"
        },
        "studentCapacity": 100,
        "coursesOffered": [
            "Java",
            "Spring Boot"
        ],
        "createdOn": 1741679477826,
        "contactEmail": "contact@techcenter.com",
        "contactPhone": "9876543210"
    },
    {
        "centerName": "BABA JI Institute",
        "centerCode": "ABC123XY8547",
        "address": {
            "detailedAddress": "123 Main Street",
            "city": "Delhi",
            "state": "Delhi",
            "pincode": "110001"
        },
        "studentCapacity": 100,
        "coursesOffered": [
            "Java",
            "Spring Boot"
        ],
        "createdOn": 1741684607975,
        "contactEmail": "contact@techcenter.com",
        "contactPhone": "9876543210"
    },
]    

📌 Error Handling
400 Bad Request → Missing required fields
409 Conflict → Duplicate training center code
500 Internal Server Error → Unexpected errors 
