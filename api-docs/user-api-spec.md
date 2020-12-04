## User API

#### Find User

- Headers:
    - `requestId` : `random-request-id`
    - `username` : `username`
    - `channelId` : `channel-id`
- Endpoint : `/api/users/{username}`
- Method : GET
- Response Body Success:
```json
{
  "code": 200,
  "message": "OK",
  "errors": null,
  "data": {
    "username": "myusername",
    "active": true,
    "enable": true,
    "lastSuccessLogin": "2018-10-11T12:34:23"
  }
}
```
- Response 400 Bad Request:
```json
{
  "code": 400,
  "message": "BAD_REQUEST",
  "errors": {
    "username": ["username must not be blank"]
  }
}
```
        
- Response 404 Not Found:
```json
{
  "code": 404,
  "message": "User Not Found"
}
```


#### Create User 
- Headers:
    - `requestId` : `random-request-id`
    - `username` : `username`
    - `channelId` : `channel-id`
- Endpoint : `/api/users/registration`
- Method : POST
- Request Body :
```json
{
  "username": "my-username",
  "password": "my-password"
}
```     
- Response Success:
```json
{
  "code": 200,
  "message": "OK",
  "errors": null,
  "data": true
}
```

- Response 400
```json
{
  "code": 400,
  "message": "Bad Request",
  "errors": {
    "username": ["username must not be blank"],
    "password": ["password must not be blank"]
  }
}
```

#### Delete User
- Headers:
    - `requestId` : `random-request-id`
    - `username` : `username`
    - `channelId` : `channel-id`
- Endpoint : `/api/users/{username}`
- Method : DELETE
- Response Success:
```json
{
  "code": 200,
  "message": "OK",
  "errors": null,
  "data": true
}
```

- Response 400:
```json
{
  "code": 400,
  "message": "BAD_REQUEST",
  "errors": {
    "username": [
      "Username may not be empty"
    ]
  }
}
```

#### Change Password
- Headers:
    - `requestId` : `random-request-id`
    - `username` : `username`
    - `channelId` : `channel-id`
- Endpoint : `/api/users/change-password`
- Method : POST
- Request Body:
```json
{
  "username": "username",
  "oldPassword": "my-old-password",
  "newPassword": "my-new-password"
}
```

- Response 200:
```json
{
  "code": 200,
  "message": "OK",
  "errors": null,
  "data": true
}
```

- Response 400:
```json
{
  "code": 400,
  "message": "BAD_REQUEST",
  "errors": {
    "username": [
      "Username may not be empty"
    ],
    "password": [
      "Password may not be empty"
    ]
  }
}
```