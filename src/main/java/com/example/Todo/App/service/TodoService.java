package com.example.Todo.App.service;

import com.example.Todo.App.domain.Todo;
import com.example.Todo.App.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getTodoAllItems();

    Optional<Todo> getTodoById(Long id) throws Exception;

    void deleteTodoById(Long id) throws Exception;

    void updateTodo(Long id, TodoDto todoDto) throws Exception;

    void createTodo(TodoDto todoDto);
}
