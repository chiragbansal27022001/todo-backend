package com.example.Todo.App.controller;

import com.example.Todo.App.domain.Todo;
import com.example.Todo.App.dto.TodoDto;
import com.example.Todo.App.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todoApp")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/getTodoList")
    public List<Todo> getTodoList(){
        return todoService.getTodoAllItems();
    }

    @GetMapping("/getTodo/{id}")
    public ResponseEntity<Todo> getToDoById(@PathVariable Long id) throws Exception {
        Optional<Todo> todoOptional = todoService.getTodoById(id);
        return todoOptional.map(todo -> new ResponseEntity<>(todo, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/createTodo")
    public ResponseEntity<String> createTodo(@RequestBody TodoDto todoDto){
        todoService.createTodo(todoDto);
        return new ResponseEntity<>("Todo is successfully created", HttpStatus.OK);
    }

    @PostMapping("/updateTodo/{id}")
    public ResponseEntity<String> updateToDo(@PathVariable Long id, @RequestBody TodoDto todoDto) throws Exception {
        todoService.updateTodo(id, todoDto);
        return new ResponseEntity<>("Todo is successfully updated", HttpStatus.OK);
    }

    @DeleteMapping("/deleteTodo/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) throws Exception {
        todoService.deleteTodoById(id);
        return new ResponseEntity<>("Todo is successfully deleted", HttpStatus.OK);
    }

}
