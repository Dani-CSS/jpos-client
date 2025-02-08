This project is built using the Spring ecosystem and the jPOS framework.
jPOS is an ISO-8583-based financial transaction library/framework that can be customized and extended to implement financial interchanges.

# Technologies Used
Java 22
Spring Boot
jPOS 3.0.0
Maven
 
# Setup & Installation
Prerequisites
Ensure you have the following installed:
Java 22
Maven
An IDE (IntelliJ IDEA, VS Code, Eclipse, etc.)

# Clone the Repository
git clone https://github.com/Dani-CSS/jpos-client.git
cd jpos-client

# Build the Project
mvn clean install

# Run the Application
mvn spring-boot:run

# Configuration
pom.xml includes the following dependencies:

<properties>
    <java.version>22</java.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
        <groupId>org.jpos</groupId>
        <artifactId>jpos</artifactId>
        <version>3.0.0</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>


# Contributing
Pull requests are welcome! If you find any issues or have suggestions, feel free to open an issue.

# License
MIT License
