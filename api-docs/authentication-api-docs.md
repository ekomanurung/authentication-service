## Authentication API

#### Authenticate user
- Headers:
    - `requestId` : `random-request-id`
    - `username` : `username`
    - `channelId` : `channel-id`
- Endpoint : `/api/authentication/_auth`
- Method : POST
- Request Body: 
```json
{
  "username": "my-username",
  "password": "my-password"
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

- Response 401:
```json
{
  "code": 401,
  "message": "Unauthorized"
}
```