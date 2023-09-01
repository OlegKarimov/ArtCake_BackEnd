# ArtCake_BackEnd
Final project ArtCake

## Cakes

- get all cakes

- Validation for all cakes fields are:
name can not be null and is unique, the fields ingredients,price,category,state
can not be null.

```
Response:

GET /api/cakes

Request:

{
    "cakes": [
        // list cakes with fields
        // id, name, ingredients, price, category, state
    ],
    "count" : // how much data was returned
    "pagesCount" : //how much pages was returned
}
```

- get cake

```
Response:

GET /api/cakes/{cake-id}

Request:

{
    "cake": [
        // cake with fields
        // id, name, ingredients, price, category, state
    ]
}
```

- get cakes by category

```
Request: GET http://localhost:8080/api/cakes/category/MOUSSE

Response: 

Response body
Download
{
  "cakes": [
    {
      "id": 16,
      "name": "Coconut-Mousse",
      "ingredients": "sugar, coconut, eggs, flour, milk, gelatin, cream-35%",
      "price": 190,
      "category": "MOUSSE",
      "state": "CREATED",
      "ordersId": null
    },
  {
      "id": 20,
      "name": "Blackberry-Mousse",
      "ingredients": "sugar, blackberry, eggs, flour, milk, gelatin, cream-35%",
      "price": 180,
      "category": "MOUSSE",
      "state": "CREATED",
      "ordersId": null
    }
  ],
  "count": null,
  "pagesCount": null
}

```

## User.Role.MANAGER

- Validation for user: email,fullname,hashpassword,town,street,housenumber,state 
and role field cannot be null.
- PhoneNumber field - min 10 characters.

### MANAGER can:

- get order of client

```
Request:GET/api/users/clients/orders
{
  "page": 0,
  "orderBy": "state",
  "desc": true,
  "filterBy": "confectionerId",
  "filterValue": "1",
  "orders": "string"
}
RESPONSE: 

{
  "orders": [
    {
      "id": 1,
      "cake": {
        "id": 1,
        "name": "BlueBerry-Cupcake",
        "ingredients": "eggs, milk, salt",
        "price": 100,
        "category": "CUPCAKES",
        "state": "DELETED",
        "ordersId": 1
      },
      "count": 1,
      "clientWishes": "Make in blue and white colours",
      "totalPrice": 0,
      "creationDate": "2023-02-02",
      "deadline": "2023-02-02",
      "state": "CREATED"
    }
  ],
  "count": 2,
  "pagesCount": 1
}
```

- Get all Confectioners

```
Response:
GET /api/users/role/{role}

Request:
{
  "users": [
    {
      "id": 2,
      "fullName": "Konditer Konditerow",
      "email": "konditerow@gmail.com",
      "town": "Kiel",
      "street": "StrandStrasse",
      "houseNumber": 78,
      "phoneNumber": "+4917688359755",
      "role": "CONFECTIONER",
      "state": "CONFIRMED"
    },
       {
      "id": 5,
      "fullName": "Konditer Konditerenko",
      "email": "konditerenko@gmail.com",
      "town": "Kiel",
      "street": "StrandStrasse",
      "houseNumber": 19,
      "phoneNumber": "+4917612359755",
      "role": "CONFECTIONER",
      "state": "CONFIRMED"
    }
  ]
}
```

- delete cake

```
Request: DELETE /api/cakes/{cake-id} http://localhost:8080/api/cakes/2

Response 200:

{
  "id": 1,
  "name": "BlueBerry-Cupcake",
  "ingredients": "eggs, milk, salt",
  "price": 100,
  "category": "CUPCAKES",
  "state": "DELETED",
  "ordersId": 1
}

Ответ 404: cake not found
```

- update order, cake, confectioner, client and state by id

```
Request: PUT /api/cakes/{cake-id}

{
  "name": "Chocolate-Cheesecake",
  "ingredients": "cookies, chocolate, butter, cream-cheese, sugar, eggs, cream-35%",
  "price": 220,
  "state": "CREATED"
}

Response:

{
  "id": 1,
  "name": "Chocolate-Cheesecake",
  "ingredients": "cookies, chocolate, butter, cream-cheese, sugar, eggs, cream-35%",
  "price": 220,
  "category": "CHEESECAKES",
  "state": "CREATED",
  "ordersId": null
}
```

- add cake POST /api/cakes

```
"id": 1,
  "name": "Chocolate-Cheesecake",
  "ingredients": "cookies, chocolate, butter, cream-cheese, sugar, eggs, cream-35%",
  "price": 220,
  "category": "CHEESECAKES",
  "state": "CREATED"
```

## User.Role.CONFECTIONER

- Validation for user: email,fullname,hashpassword,town,street,housenumber,state 
and role field cannot be null.
- PhoneNumber field - min 10 characters.

### CONFECTIONER can:

- get orders of your confectioner

```
Request:
GET/api/users/confectioner/orders

{
  "orders": [
    {
      "id": 1,
      "cake": {
        "id": 1,
        "name": "BlueBerry-Cupcake",
        "ingredients": "eggs, milk, salt",
        "price": 100,
        "category": "CUPCAKES",
        "state": "DELETED",
        "ordersId": 1
      },
      "count": 1,
      "clientWishes": "Make in blue and white colours",
      "totalPrice": 0,
      "creationDate": "2023-02-02",
      "deadline": "2023-02-02",
      "state": "CREATED"
    }
  ],
  "count": 2,
  "pagesCount": 1
}
```
## User.Role.CLIENT

- Validation for user: email,fullname,hashpassword,town,street,housenumber,state and 
role field cannot be null.
- PhoneNumber field - min 10 characters.

### CLIENT can:

- get my orders 

```
Request:GET/api/users/clients/orders
{
  "page": 0,
  "orderBy": "state",
  "desc": true,
  "filterBy": "confectionerId",
  "filterValue": "1",
  "orders": "string"
}
RESPONSE: 

{
  "orders": [
    {
      "id": 1,
      "cake": {
        "id": 1,
        "name": "BlueBerry-Cupcake",
        "ingredients": "eggs, milk, salt",
        "price": 100,
        "category": "CUPCAKES",
        "state": "DELETED",
        "ordersId": 1
      },
      "count": 1,
      "clientWishes": "Make in blue and white colours",
      "totalPrice": 0,
      "creationDate": "2023-02-02",
      "deadline": "2023-02-02",
      "state": "CREATED"
    }
  ],
  "count": 2,
  "pagesCount": 1
}
```
