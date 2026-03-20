# Design

## POST /todos

### Purpose
Create a new todo.

### Request
- Accept "title" from user

### Steps
1. Validate title (not empty)
2. Create Todo object:
    - id → generated
    - title → from request
    - completed → false
    - createdAt → current time
3. Save todo
4. Return created todo

### Response
- 201 Created
- Returns full Todo object

### Edge Cases
- Empty title → error