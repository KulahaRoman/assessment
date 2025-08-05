# assessment
### 📘 Project Overview

A Spring Boot REST API that returns all non-fork GitHub repositories of a given user, including branch names and the latest commit SHA for each branch.

Handles non-existent users with a clear 404 response.

#### 🔹 Usage

```http
GET /users/<github-login>
```
#### 🔹 Example

Request:
```http
GET /users/RomanKulaha
```

Response:
```json
{
    "repositories": [
        {
            "name": "cppstreams",
            "owner": "KulahaRoman",
            "branches": [
                {
                    "name": "master",
                    "hash": "c25cfe2fe8d8641e2e8d93e2842c1b64de84e574"
                }
            ]
        },
        {
            "name": "cpputils",
            "owner": "KulahaRoman",
            "branches": [
                {
                    "name": "master",
                    "hash": "97a16433a54fea11c467fc6ee10f94ae8b5fc981"
                }
            ]
        }
    ]
}
```

### Required environment variables:

- SERVER_PORT
- GITHUB_TOKEN

### 🧪 Testing

Project includes a single integration test for the only controller (happy path scenario).

### ⚙️ Technologies

- Java 21
- Spring Boot
- Spring Web
- JUnit
- Mockito
- Maven

### 📦 Dependencies
GitHub API for Java - https://hub4j.github.io/github-api/