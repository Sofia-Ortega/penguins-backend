# Penguins-backend

Frontend found [here](https://github.com/Sofia-Ortega/penguins-frontend)

Project to practice Test Driven Development using mockito and the basics of Spring Boot

Gets and Creates Penguins. Also gives the ability to feed partitular Penguins

## Penguin API Documentation
Base URL:
`/penguins`

### 1. Create a Penguin
   
**Endpoint:**
`POST /penguins`

**Description:**
Creates a new penguin and stores it in the database.

**Request Body:**
```json

{
  "name": "Pingu",
  "species": "EMPEROR",
  "hunger": 5
}
```

**Response:**

- **201 Created** – Penguin successfully created

### 2. Get All Penguins

**Endpoint:**
`GET /penguins`

**Description:**
Retrieves a list of all penguins in the database.

**Response:**
- **200 OK**

```json
[
  {
    "id": 1,
    "name": "Pingu",
    "species": "EMPEROR",
    "hunger": 5
  },
  {
    "id": 2,
    "name": "Chilly",
    "species": "ROCKHOPPER",
    "hunger": 3
  }
]
```
### 3. Feed a Penguin

**Endpoint:**
`POST /penguins/feed/{penguinId}`

**Description:**
Feeds a penguin by decreasing its hunger level by 1.

**Path Parameter:**
- `penguinId` (Integer) – The ID of the penguin to feed.

**Response:**
- **200 OK** – Returns the updated hunger level as an integer.
  - Example, if updated hunger level is 2, will return:

      ```
      2
      ```
- **400 Bad Request** – The penguin is not hungry (hunger level is already 0).

- **404 Not Found** – Penguin with the given ID does not exist.


### 4. Delete a Penguin

**Endpoint:**
`DELETE /penguins/{penguinId}`

**Description:**
Deletes a penguin from the database.

**Path Parameter:**
- `penguinId` (Integer) – The ID of the penguin to delete.

**Response:**
- **204 No Content** – Penguin successfully deleted.
- **404 Not Found** – Penguin with the given ID does not exist.

### 5. Update a Penguin

**Endpoint:**
`PUT /penguins/{penguinId}`

**Description:**
Updates an existing penguin's name and species.

**Path Parameter:**
- `penguinId` (Integer) – The ID of the penguin to update.

**Request Body:**
```json
{
  "name": "Updated Name",
  "species": "ADELIE"
}
```

**Responses**
- **200 OK** – Returns the updated penguin object.
  ```
  {
    "id": 1,
    "name": "Updated Name",
    "species": "ADELIE",
    "hunger": 5
  }
  ```
- **400 Bad Request** – Invalid species name provided.
- **404 Not Found** – Penguin with the given ID does not exist.


