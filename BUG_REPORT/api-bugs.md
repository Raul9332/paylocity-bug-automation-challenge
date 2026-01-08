# 🐞 Bug Report – API Bug Challenge (Paylocity)

## 📌 General Information
- **Tester:** Raul De Leon
- **Date:** 08/01/2026
- **Application:** Paylocity – Benefits API
- **Environment:** Test / QA
- **API Version:** v1

## 🔗 API Details
- **Base URL:**  
  `https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod`

- **Swagger Documentation:**  
  `/swagger/v1/swagger.json`

## 🧪 Test Environment
- **API Client:** Postman
- **Postman Version:** Latest Stable
- **Operating System:** Windows 10 (64-bit)
- **Network:** Stable broadband connection

## 🔐 Authentication
- **Auth Type:** Basic Authentication
- **Header Used:**  

---
### Bug ID: API-01
**Title:** POST /api/Employees returns 200 instead of 201 for resource creation  
**Severity:** Medium  
**Priority:** Medium

**Module:** API  
**Endpoint:** `POST /api/Employees`

### Steps to Reproduce
1. Open Swagger spec
2. Locate `POST /api/Employees`
3. Check defined responses

### Expected Result
For a successful create operation, API contract should define **201 Created** (and ideally include the created employee in the response body).

### Actual Result
Swagger defines only **200 Success** for `POST /api/Employees`.

### Evidence
Swagger `POST /api/Employees` -> responses: `200: Success`


--
### Bug ID: API-02
**Title:** PUT /api/Employees has no employee identifier (no {id} in path or parameter)  
**Severity:** High  
**Priority:** High

**Module:** API  
**Endpoint:** `PUT /api/Employees`

### Steps to Reproduce
1. Open Swagger spec
2. Locate `PUT /api/Employees`
3. Verify if it requires an `id` in path/query/body to identify which employee to update

### Expected Result
Update operation should clearly specify the target employee, typically:
- `PUT /api/Employees/{id}` (preferred), or
- `PUT /api/Employees` with **required** `id` in the request body and documented behavior.

### Actual Result
Swagger defines `PUT /api/Employees` with request body `Employee` but does not require `id` and provides no clear identifier in path or parameters.

### Evidence
Swagger path exists for `GET/DELETE /api/Employees/{id}`, but **PUT is only on `/api/Employees`**.


--
### Bug ID: API-03
**Title:** Required fields allow empty strings (minLength = 0 for username/firstName/lastName)  
**Severity:** High  
**Priority:** High

**Module:** API  
**Schema:** `Employee`

### Steps to Reproduce
1. Open Swagger spec
2. Locate `components.schemas.Employee.required`
3. Check `minLength` constraints for required string fields:
    - `username`
    - `firstName`
    - `lastName`

### Expected Result
If fields are required, they should not allow empty strings.
Example: `minLength: 1` (or additional validation rules).

### Actual Result
Fields are required but `minLength` is set to `0`, so empty values are allowed by contract.

### Evidence
`required: ["firstName","lastName","username"]` AND each has `minLength: 0`.


--
### Bug ID: API-04
**Title:** Dependents field is inconsistently named as "dependants" (likely mismatch with UI/requirements)  
**Severity:** Medium  
**Priority:** Medium

**Module:** API  
**Schema:** `Employee`

### Steps to Reproduce
1. Open Swagger spec
2. Locate `Employee.properties`
3. Find the field name for dependents

### Expected Result
Field name should match common naming and likely UI/requirements (typically `dependents`).
Consistent naming reduces integration bugs.

### Actual Result
Field is defined as `dependants`, which is inconsistent with typical US spelling and may not match UI or test data.

### Evidence
Schema property name: `dependants`


--
### Bug ID: API-05
**Title:** Swagger responses do not define response body schemas for Employees endpoints  
**Severity:** Medium  
**Priority:** Medium

**Module:** API  
**Endpoints:**
- `GET /api/Employees`
- `GET /api/Employees/{id}`
- `POST /api/Employees`
- `PUT /api/Employees`
- `DELETE /api/Employees/{id}`

### Steps to Reproduce
1. Open Swagger spec
2. Check responses for each Employees endpoint
3. Verify if response includes `content` with JSON schema

### Expected Result
Responses should define `content: application/json` with schemas, for example:
- `GET /api/Employees` -> array of `Employee`
- `GET /api/Employees/{id}` -> `Employee`
- `POST /api/Employees` -> created `Employee`
- Error responses -> 400/401/404 with error schema

### Actual Result
Responses only include `200: Success` with no response body schema/content, making contract incomplete for consumers and testing.

### Evidence
All endpoints show only:
`"responses": { "200": { "description": "Success" } }`


