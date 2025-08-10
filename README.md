
# 🗳️ Spring Boot Voting System

A simple **online voting application** built with **Spring Boot**, **Thymeleaf**, **MySQL**, and **HTML/CSS**.  
Users (citizens) can log in by name, view a list of candidates, and cast exactly **one** vote.  
The system stores all data in a MySQL database and displays live results.

---

## 📌 Features
- User login via name (case-insensitive)
- Prevents multiple voting by the same user
- Candidate listing with clickable vote buttons
- Tracks votes in MySQL
- Live election results page
- Responsive and attractive UI with CSS styling
- Auto-loads sample candidates on first run

---

## 🏗️ Tech Stack
- **Backend**: Java 17, Spring Boot, Spring MVC, Spring Data JPA
- **Frontend**: Thymeleaf, HTML5, CSS3
- **Database**: MySQL 8+
- **Build Tool**: Maven

---
VotingSystem/
├─ src/main/java/com/example/votingsystem/
│ ├─ VotingSystemApplication.java
│ ├─ controller/
│ │ └─ VotingController.java
│ ├─ model/
│ │ ├─ Candidate.java
│ │ └─ Citizen.java
│ ├─ repository/
│ │ ├─ CandidateRepo.java
│ │ └─ CitizenRepo.java
│
├─ src/main/resources/
│ ├─ application.properties
│ ├─ templates/ (Thymeleaf HTML files)
│ └─ static/css/ (CSS styles)
│
└─ pom.xml


---

## ⚙️ Installation & Setup

### 1️⃣ Prerequisites
- Java 17+
- Maven 3+
- MySQL 8+

### 2️⃣ Database Setup
Open MySQL and run:
```sql
CREATE DATABASE voting_system;

spring.datasource.url=jdbc:mysql://localhost:3306/voting_system
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

mvn clean package
mvn spring-boot:run

Or run VotingSystemApplication from your IDE.

🌐 Usage
Open browser: http://localhost:8080/vote

Enter your name and click Continue

If you haven’t voted yet → choose a candidate and click Vote

If you already voted → you’ll see a message saying so

View live results at: http://localhost:8080/results

📸 Screenshots
Login Page

Candidate List

Results Page

## 📂 Project Structure
