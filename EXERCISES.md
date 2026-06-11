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
