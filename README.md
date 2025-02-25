# Customer service REST API

This project is a REST API for customer management, developed with Java and Spring Boot.

## Technologies Used
- **Java** (version 17)
- **Spring Boot** (version 2.5.5)
- **Gradle**

## Main Endpoints

### Create a Customer
``` http
POST /account/register
```
**Request body:**
```json
{
  "user": "",
  "password": "",
  "displayName": "",
  "rank": "" ## CLIENT or SUPPORT
}
```

### List Customers
``` http
GET /account
```

### Search Customer
``` http
GET /account/id/{id}
GET /account/user/{user}
```

### Update Client
``` http
PUT /account/{id}
```

### Delete Customer
``` http
DELETE /account/id/{id}
DELETE /account/user/{user}
```

## Contribution
1. Fork this repository
2. Create a branch: `git checkout -b my-feature`
3. Commit your changes: `git commit -m 'Adding a new feature'`
4. Push to branch: `git push origin my-feature`
5. Open a pull request

---
This is an initial model. If you need configurations, just let me know!