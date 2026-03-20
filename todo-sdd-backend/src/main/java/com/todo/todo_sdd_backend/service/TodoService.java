package com.todo.todo_sdd_backend.service;

import com.todo.todo_sdd_backend.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Comparator;

@Service
public class TodoService {
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final List<Todo> store = Collections.synchronizedList(new ArrayList<>());

    public Todo create(String title) {
        Long id = idGenerator.getAndIncrement();
        Todo todo = Todo.createNew(id, title);
        store.add(todo);
        return todo;
    }

    public List<Todo> findAll() {
        synchronized (store) {
            List<Todo> copy = new ArrayList<>(store);
            copy.sort(Comparator.comparing(Todo::getCreatedAt));
            return copy;
        }
    }
}
