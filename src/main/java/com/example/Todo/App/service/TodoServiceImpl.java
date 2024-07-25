package com.example.Todo.App.service;

import com.example.Todo.App.repository.TodoRepository;
import com.example.Todo.App.domain.Todo;
import com.example.Todo.App.dto.TodoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getTodoAllItems() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> getTodoById(Long id) throws Exception {
        return todoRepository.findById(id);
    }

    @Override
    public void deleteTodoById(Long id) throws Exception {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isEmpty()){
            throw new Exception("Corresponding todo id " + id + " is not present");
        }
        todoRepository.deleteById(id);
    }

    @Override
    public void updateTodo(Long id, TodoDto todoDto) throws Exception {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isEmpty()){
            throw new Exception("Corresponding todo id " + id + " is not present");
        }
        todo.get().setTitle(todoDto.getTitle());
        todo.get().setDescription(todoDto.getDescription());
        todoRepository.save(todo.get());

    }

    @Override
    public void createTodo(TodoDto todoDto) {
        Todo todo = new Todo(todoDto.getTitle(), todoDto.getDescription());
        todoRepository.save(todo);
    }
}
