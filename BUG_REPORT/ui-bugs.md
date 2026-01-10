# 🐞 Bug Report – UI Bug Challenge (Paylocity)


## 📌 General Information
- **Tester:** Raul De Leon
- **Date:** 08/01/2026
- **Application:** Paylocity – Benefits Dashboard
- **Environment:** Test / QA
- **Build / Version:** N/A (Test Environment)

## 💻 Test Environment Details
- **Operating System:** Windows 10 (64-bit)
- **Browser:** Google Chrome
- **Browser Version:** Latest Stable
- **Resolution:** 1920 x 1080
- **Device Type:** Desktop
- **Network:** Stable broadband connection

## 🔐 Authentication
- **Login Type:** Username & Password
- **Authorization:** Valid authenticated user

## 🧪 Test Scope
- **Testing Type:** Manual UI Testing
- **Modules Covered:**
    - Add Employee
    - Edit Employee
    - Delete Employee
    - Employee Table & Calculations

## 📋 Notes
- Testing performed based on provided acceptance criteria and assumptions.
- All defects were reproduced consistently.

---
### Bug ID: UI-01
**Title:** Add Employee button is enabled with empty form  
**Severity:** Medium  
**Priority:** Medium

**Module:** UI  
**Screen:** Add Employee Form

### Preconditions
Logged with correct user into Paylocity Beneficts Dashboard

### Steps to Reproduce
1. Navigate to Benefits Dashboard
2. Click on `Add Employee`
3. Observe the Add button without entering any data

### Expected Result
Add button should be disabled until required fields are filled.

### Actual Result
Add button is enabled even when all fields are empty.

### Evidence
TODO


--
### Bug ID: UI-02
**Title:** No validation message shown when submitting empty employee form  
**Severity:** High  
**Priority:** High

**Module:** UI  
**Screen:** Add Employee Form

### Preconditions
Logged with correct user into Paylocity Beneficts Dashboard

### Steps to Reproduce
1. Click `Add Employee`
2. Leave all fields empty
3. Click `Add`

### Expected Result
Validation message should be displayed for required fields.

### Actual Result
No error or validation message is shown.

### Evidence
TODO

---
### Bug ID: UI-03
**Title:** No validation error for missing dependents field  
**Severity:** Medium  
**Priority:** Medium

**Module:** UI  
**Screen:** Add Employee Form

### Preconditions
Logged with correct user into Paylocity Beneficts Dashboard

### Steps to Reproduce
1. Click `Add Employee`
2. Fill name fields
3. Leave dependents field empty
4. Click `Add`

### Expected Result
System should display validation error for missing dependents filed.

### Actual Result
Employee is added without dependents validation.

### Evidence
TODO

---
### Bug ID: UI-04
**Title:** Dependents field allows negative and text values without error message shown 
**Severity:** Medium  
**Priority:** Medium

**Module:** UI  
**Screen:** Add Employee Form

### Preconditions
Logged with correct user into Paylocity Beneficts Dashboard

### Steps to Reproduce
1. Click `Add Employee`
2. Enter valid name
3. Enter `-1` in dependents field
4. Click `Add`

### Expected Result
System should prevent negative numbers and show validation error.

### Actual Result
Employee is saved with negative dependents value.

### Evidence
TODO


---
### Bug ID: UI-05
**Title:** No success message displayed after adding employee  
**Severity:** Low  
**Priority:** Medium

**Module:** UI  
**Screen:** Benefits Dashboard

### Steps to Reproduce
1. Add employee with valid data
2. Save the employee

### Expected Result
Success confirmation message should be displayed.

### Actual Result
Employee is added but no success message is shown.

### Evidence
TODO


---
### Bug ID: UI-06
**Title:** Last Name fields allows numeric characters  
**Severity:** Medium  
**Priority:** Medium

**Module:** UI  
**Screen:** Add Employee Form

### Steps to Reproduce
1. Click `Add Employee`
2. Enter numbers in Last Name field
3. Save employee

### Expected Result
Last Name field should only allow alphabetic characters.

### Actual Result
Numbers are accepted in Last Name field.

### Evidence
TODO


---
### Bug ID: UI-07
**Title:** First Name field allows numeric characters  
**Severity:** Medium  
**Priority:** Medium

**Module:** UI  
**Screen:** Add Employee Form

### Steps to Reproduce
1. Click `Add Employee`
2. Enter numbers in First Name field
3. Save employee

### Expected Result
First Name field should only allow alphabetic characters.

### Actual Result
Numbers are accepted in First Name field.

### Evidence
TODO

---
### Bug ID: UI-08
**Title:** Employee table layout breaks with long first or last name  
**Severity:** Low  
**Priority:** Low

**Module:** UI  
**Screen:** Employee Table

### Steps to Reproduce
1. Add employee with very long first and last names
2. Observe employee table layout

### Expected Result
Table should remain aligned and responsive.

### Actual Result
Table becomes misaligned and layout breaks.

### Evidence
TODO

---
### Bug ID: UI-09
**Title:** System allows duplicate employee records  
**Severity:** Medium  
**Priority:** Medium

**Module:** UI  
**Screen:** Benefits Dashboard

### Steps to Reproduce
1. Add an employee with a specific first and last name
2. Add another employee with the same data

### Expected Result
System should prevent duplicate employee records.

### Actual Result
Duplicate employees are added successfully.

### Evidence
TODO

---
### Bug ID: UI-10
**Title:** Newly added employee appears in random order in table  
**Severity:** Low  
**Priority:** Low

**Module:** UI  
**Screen:** Employee Table

### Steps to Reproduce
1. Add multiple employees
2. Observe order of employees in table

### Expected Result
New employee should appear at top or bottom consistently.

### Actual Result
Employee appears in random position.

### Evidence
TODO





