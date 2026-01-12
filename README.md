# 🧪 Paylocity – Bug & Automation Challenge  
### UI & API Automation Framework (Java)

**Author:** Raul De Leon  
**Role:** Senior SDET / Automation Engineer  
**Tech Stack:** Java · Selenium · TestNG · Rest Assured · Maven  

This repository presents a **comprehensive UI and API automation solution**, designed following **real-world enterprise best practices** with a strong focus on **readability, scalability, and maintainability**.

---

## 🎯 Project Objective

- Validate **critical business flows** through automated testing
- Demonstrate expertise in:
  - UI Automation (Selenium WebDriver)
  - API Automation (Rest Assured)
  - Automation framework design
  - Test architecture and best practices
- Provide a **reusable and scalable foundation** for enterprise-level projects

---

## 🏗️ Framework Architecture

The framework is built using **Clean Code**, **SOLID principles**, and a **clear separation of responsibilities**, ensuring long-term maintainability.

### 🔹 Design Patterns & Approaches
- **Page Object Model (POM)** for UI automation
- **Data-Driven Testing (DDT)** for flexibility and scalability
- **Base Test Layer** for shared setup and utilities
- **Explicit waits** for UI stability
- **Independent and repeatable tests**

---

## 🧰 Tech Stack

### UI Automation
- Java 21
- Selenium WebDriver 4
- TestNG
- Page Object Model (POM)
- Allure Report

### API Automation
- Rest Assured
- JSON & HTTP validations
- Full CRUD coverage

### Build & Tools
- Maven
- IntelliJ IDEA
- Git / GitHub

---
## Test UI executed
<img width="1782" height="978" alt="image" src="https://github.com/user-attachments/assets/f80eb834-6c65-4fbe-a4bb-d3266db0a3ed" />


## 📁 Project Structure

```text
src
 └── test
     ├── java
     │   ├── core          # Base test setup, waits, config
     │   ├── pages         # Page Objects (UI)
     │   ├── tests
     │   │   ├── ui        # UI Test Scenarios
     │   │   └── api       # API Test Scenarios
     └── resources
         ├── testng.xml
         └── config.properties


