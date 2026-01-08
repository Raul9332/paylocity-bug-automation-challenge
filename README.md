# Paylocity Next Steps: Bug and Automation Challenge

## Tech
- Java 21
- Maven
- Selenium WebDriver 4 + TestNG (UI)
- RestAssured (API)

## Config
Edit `src/test/resources/config.properties`:
- ui.baseUrl
- api.baseUrl
- api.auth (Authorization header value)
- ui.username / ui.password

## Run
Run all:
```bash
mvn -q test
