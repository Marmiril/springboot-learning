# Spring Boot Learning - Exercises

## Current status

Current block: **Block 2 - Validation and HTTP responses**

Completed exercises: **01 to 10**

Current exercise: **Exercise 11 - Reusable validation with private methods**

---

## Exercise index

| Exercise | Topic | Method | URL | Status |
|---|---|---|---|---|
| 01 | Basic endpoint | GET | http://localhost:8080/exercise01/hello | Done |
| 02 | Path variable | GET | http://localhost:8080/exercise02/hello/{name} | Done |
| 03 | Request param | GET | http://localhost:8080/exercise03/hello?name=Angel | Done |
| 04 | JSON response | GET | http://localhost:8080/exercise04/student | Done |
| 05 | JSON + PathVariable | GET | http://localhost:8080/exercise05/student/{name} | Done |
| 06 | JSON list | GET | http://localhost:8080/exercise06/students | Done |
| 07 | Filter by query param | GET | http://localhost:8080/exercise07/students?role=developer | Done |
| 08 | POST + RequestBody | POST | http://localhost:8080/exercise08/students | Done |
| 09 | ResponseEntity | POST | http://localhost:8080/exercise09/students | Done |
| 10 | Basic validation with POST | POST | http://localhost:8080/exercise10/students | Done |
| 11 | Reusable validation with private methods | POST | http://localhost:8080/exercise11/students | Done |
---

## Exercise 01 - Basic endpoint

**Purpose:**  
...

**URL:**  
http://localhost:8080/exercise01/hello

**HTTP method:**  
GET

**Main concepts:**

- `@RestController`
- `@GetMapping`
- Basic endpoint

**File:**  
`src/main/java/.../HelloController.java`

**Status:**  
Done



## Exercise 02 - Path variable

**Purpose:**  
This exercise introduces the use of path variables to capture dynamic values directly from the URL.

**URL:**  
http://localhost:8080/exercise02/hello/{name}

**HTTP method:**  
GET

**Main concepts:**

- `@GetMapping`
- `@PathVariable`
- Dynamic URL values
- Basic REST parameter handling

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise02/PathVariableController.java`

**Status:**  
Done

---

---

## Exercise 03 - Request param

**Purpose:**  
This exercise introduces the use of query parameters to receive optional or specific values from the URL.

**URL:**  
http://localhost:8080/exercise03/hello?name=Angel

**HTTP method:**  
GET

**Main concepts:**

- `@GetMapping`
- `@RequestParam`
- Query parameters
- Basic parameter handling in HTTP requests

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise03/RequestParamController.java`

**Status:**  
Done

---

## Exercise 04 - JSON response

**Purpose:**  
This exercise introduces how Spring Boot automatically converts a Java object into a JSON response.

**URL:**  
http://localhost:8080/exercise04/student

**HTTP method:**  
GET

**Main concepts:**

- `@RestController`
- `@GetMapping`
- JSON response
- Java object serialization

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise04/JsonController.java`

**Status:**  
Done

---

## Exercise 05 - JSON with PathVariable

**Purpose:**  
This exercise combines a JSON response with a path variable to return student data based on a value received from the URL.

**URL:**  
http://localhost:8080/exercise05/students/{id}

**HTTP method:**  
GET

**Main concepts:**

- `@GetMapping`
- `@PathVariable`
- JSON response
- Dynamic resource lookup by ID

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise05/StudentByIdController.java`

**Status:**  
Done

---

## Exercise 06 - JSON list response

**Purpose:**  
This exercise introduces how Spring Boot can return a list of Java objects as a JSON array.

**URL:**  
http://localhost:8080/exercise06/students

**HTTP method:**  
GET

**Main concepts:**

- `@RestController`
- `@GetMapping`
- `List.of()`
- JSON array response
- Java record as response model

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise06/StudentListController.java`

**Status:**  
Done

---

## Exercise 07 - Optional query parameter filter

**Purpose:**  
This exercise introduces how to use an optional query parameter to filter a JSON list response.

**URLs:**  
http://localhost:8080/exercise07/students  
http://localhost:8080/exercise07/students?role=backend

**HTTP method:**  
GET

**Main concepts:**

- `@RestController`
- `@GetMapping`
- `@RequestParam(required = false)`
- Optional query parameters
- Filtering with Stream API
- `equalsIgnoreCase()`

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise07/StudentFilterController.java`

**Status:**  
Done



---

## Exercise 08 - Receive JSON with POST

**Purpose:**  
This exercise introduces how Spring Boot can receive JSON data in the request body and convert it into a Java object.

**URL:**  
http://localhost:8080/exercise08/students

**HTTP method:**  
POST

**Main concepts:**

- `@RestController`
- `@PostMapping`
- `@RequestBody`
- JSON request body
- Java record as request model
- Java record as response model

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise08/StudentPostController.java`

**Status:**  
Done

---

## Exercise 09 - ResponseEntity with POST

**Purpose:**  
This exercise introduces how to return an HTTP status code together with a JSON response using `ResponseEntity`.

**URL:**  
http://localhost:8080/exercise09/students

**HTTP method:**  
POST

**Main concepts:**

- `@RestController`
- `@PostMapping`
- `@RequestBody`
- `ResponseEntity`
- `HttpStatus.CREATED`
- HTTP 201 Created

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise09/StudentResponseEntityController.java`

**Status:**  
Done

---

## Exercise 10 - Basic validation with POST

**Purpose:**  
This exercise introduces basic manual validation before creating a response. If the received JSON contains invalid data, the controller returns an HTTP 400 Bad Request response.

**URL:**  
http://localhost:8080/exercise10/students

**HTTP method:**  
POST

**Main concepts:**

- `@RestController`
- `@PostMapping`
- `@RequestBody`
- `ResponseEntity`
- `HttpStatus.CREATED`
- `HttpStatus.BAD_REQUEST`
- Manual validation
- Error response model

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise10/StudentValidationController.java`

**Status:**  
Done

## Exercise 11 - Reusable validation with private methods

**Purpose:**
This exercise improves the previous manual validation by moving repeated validation logic into private helper methods.

**URL:**
http://localhost:8080/exercise11/students

**HTTP method:**
POST

**Main concepts:**

* Manual validation
* Private helper methods
* `ResponseEntity<?>`
* `HttpStatus.CREATED`
* `HttpStatus.BAD_REQUEST`
* Custom error response
* Cleaner controller logic
* Reading `400 Bad Request` responses in PowerShell

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise11/StudentReusableValidationController.java`

**PowerShell valid request:**

```powershell
$body = @{
    name = "Angel"
    role = "backend"
} | ConvertTo-Json

Invoke-RestMethod `
  -Uri "http://localhost:8080/exercise11/students" `
  -Method Post `
  -ContentType "application/json" `
  -Body $body
```

**PowerShell invalid request:**

```powershell
$body = @{
    name = ""
    role = "backend"
} | ConvertTo-Json

try {
    Invoke-WebRequest `
      -Uri "http://localhost:8080/exercise11/students" `
      -Method Post `
      -ContentType "application/json" `
      -Body $body
}
catch {
    $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
    $reader.ReadToEnd()
}
```

**Expected error response:**

```json
{
  "message": "The student name is required!"
}
```

**Status:**
Done

## Exercise 12 - Validation of several fields

**Purpose:**
This exercise extends manual validation by checking several fields before creating a response: name, role, age and email.

**URL:**
http://localhost:8080/exercise12/students

**HTTP method:**
POST

**Main concepts:**

* Manual validation
* Validation of several fields
* Private helper methods
* `ResponseEntity<?>`
* `HttpStatus.CREATED`
* `HttpStatus.BAD_REQUEST`
* Custom error response
* Basic email validation
* Numeric range validation
* Cleaner controller logic

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise12/StudentSeveralFieldsValidationController.java`

**Tested cases:**

* Valid request returns HTTP 201 Created.
* Empty name returns HTTP 400 Bad Request.
* Empty role returns HTTP 400 Bad Request.
* Invalid age returns HTTP 400 Bad Request.
* Invalid email returns HTTP 400 Bad Request.

**Status:**
Done

## Exercise 13 - Structured error responses

**Purpose:**
This exercise improves error handling by returning a more structured error response. Instead of returning only a message, the API returns the HTTP status code, the HTTP error name and a custom validation message.

**URL:**
http://localhost:8080/exercise13/students

**HTTP method:**
POST

**Main concepts:**

* Manual validation
* Structured error response
* `ResponseEntity<?>`
* `HttpStatus.BAD_REQUEST`
* `status.value()`
* `status.getReasonPhrase()`
* Custom `ErrorResponse`
* Basic regex validation for email

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise13/StudentStructuredErrorController.java`

**Status:**
Done

---

## Exercise 14 - HTTP 404 Not Found

**Purpose:**
This exercise introduces how to return an HTTP 404 Not Found response when a requested resource does not exist.

**URL:**
http://localhost:8080/exercise14/students/{id}

**HTTP method:**
GET

**Main concepts:**

* `@GetMapping`
* `@PathVariable`
* `ResponseEntity<?>`
* HTTP 200 OK
* HTTP 404 Not Found
* Simulated resource lookup
* Structured error response

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise14/StudentNotFoundController.java`

**Status:**
Done

---

## Exercise 15 - HTTP 204 No Content

**Purpose:**
This exercise introduces how to return an HTTP 204 No Content response when an operation is completed successfully but does not need to return a response body.

**URL:**
http://localhost:8080/exercise15/students/{id}

**HTTP method:**
DELETE

**Main concepts:**

* `@DeleteMapping`
* `@PathVariable`
* `ResponseEntity<?>`
* HTTP 204 No Content
* HTTP 404 Not Found
* Simulated delete operation
* Response without body

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise15/StudentNoContentController.java`

**Status:**
Done

---

## Exercise 16 - HTTP 409 Conflict

**Purpose:**
This exercise introduces how to return an HTTP 409 Conflict response when the request is valid but conflicts with existing data.

**URL:**
http://localhost:8080/exercise16/students

**HTTP method:**
POST

**Main concepts:**

* `@PostMapping`
* `@RequestBody`
* `ResponseEntity<?>`
* HTTP 201 Created
* HTTP 400 Bad Request
* HTTP 409 Conflict
* Duplicate value validation
* Simulated existing data

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise16/StudentConflictController.java`

**Status:**
Done

---

## Exercise 17 - HTTP 422 Unprocessable Content

**Purpose:**
This exercise introduces how to return an HTTP 422 response when the received JSON is valid but does not follow a business rule.

**URL:**
http://localhost:8080/exercise17/students

**HTTP method:**
POST

**Main concepts:**

* `@PostMapping`
* `@RequestBody`
* Manual validation
* Business rule validation
* `HttpStatus.UNPROCESSABLE_CONTENT`
* HTTP 422 Unprocessable Content
* Allowed values validation
* Structured error response

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise17/StudentUnprocessableEntityController.java`

**Status:**
Done

---

## Exercise 18 - Common HTTP status codes

**Purpose:**
This exercise consolidates several common HTTP status codes used in REST APIs in a single controller.

**URL:**
http://localhost:8080/exercise18/students

**HTTP methods:**
GET, POST, DELETE

**Main concepts:**

* `ResponseEntity`
* HTTP 200 OK
* HTTP 201 Created
* HTTP 204 No Content
* HTTP 400 Bad Request
* HTTP 404 Not Found
* HTTP 409 Conflict
* `@GetMapping`
* `@PostMapping`
* `@DeleteMapping`
* `@PathVariable`
* `@RequestBody`
* Structured error responses
* Simulated student data

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise18/StudentHttpStatusController.java`

**Tested cases:**

* GET all students returns HTTP 200 OK.
* GET existing student by id returns HTTP 200 OK.
* GET missing student by id returns HTTP 404 Not Found.
* POST valid student returns HTTP 201 Created.
* POST empty required field returns HTTP 400 Bad Request.
* POST duplicated student name returns HTTP 409 Conflict.
* DELETE existing student returns HTTP 204 No Content.
* DELETE missing student returns HTTP 404 Not Found.

**Status:**
Done
