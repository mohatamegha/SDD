package com.todo.todo_sdd_backend.controller;

import com.todo.todo_sdd_backend.dto.CreateTodoRequest;
import com.todo.todo_sdd_backend.model.Todo;
import com.todo.todo_sdd_backend.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.findAll();
        return ResponseEntity.ok(todos);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody CreateTodoRequest request) {
        Todo created = todoService.create(request.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
