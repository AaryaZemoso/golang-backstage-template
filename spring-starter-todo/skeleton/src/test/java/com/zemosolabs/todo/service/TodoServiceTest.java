package com.zemosolabs.{{cookiecutter.package_name}}.service;

import com.zemosolabs.{{cookiecutter.package_name}}.model.Todo;
import com.zemosolabs.{{cookiecutter.package_name}}.model.request.CreateTodoRequest;
import com.zemosolabs.{{cookiecutter.package_name}}.model.request.UpdateTodoRequest;
import com.zemosolabs.{{cookiecutter.package_name}}.repository.TodoRepository;
import com.zemosolabs.{{cookiecutter.package_name}}.service.impl.TodoServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    private TodoService todoService;

    @Before
    public void setup() {
        todoService = new TodoServiceImpl(todoRepository);
    }

    @Test
    public void testSaveTodo() {
        var mockTodo = new Todo(UUID.randomUUID(), "todo");
        when(todoRepository.save(any(Todo.class)))
                .thenReturn(mockTodo);

        var createTodoRequest = CreateTodoRequest.builder()
                .content("todo")
                .build();

        var todoResponse = todoService.createTodo(createTodoRequest);

        assertEquals(mockTodo.getContent(), todoResponse.getContent());

    }

    @Test
    public void testGetTodoById() {
        var mockTodo = new Todo(UUID.randomUUID(), "todo");
        when(todoRepository.getOne(mockTodo.getId()))
                .thenReturn(mockTodo);

        var todoResponse = todoService.getTodo(mockTodo.getId());

        assertEquals(todoResponse.getContent(), mockTodo.getContent());
    }

    @Test
    public void testGetAllTodos() {
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo(UUID.randomUUID(), "todo 1"));
        todos.add(new Todo(UUID.randomUUID(), "todo 2"));

        when(todoRepository.findAll()).thenReturn(todos);

        var getAllResponse= todoService.getAllTodos();

        assertEquals(getAllResponse.size(), todos.size());
    }

    @Test
    public void testUpdateTodo() {
        var mockTodo = new Todo(UUID.randomUUID(), "todo");
        when(todoRepository.getOne(mockTodo.getId()))
                .thenReturn(mockTodo);
        when(todoRepository.save(any(Todo.class)))
                .thenReturn(mockTodo);

        var todoResponse = todoService.updateTodo(mockTodo.getId(),
                UpdateTodoRequest.builder().content("todo").build());
        assertEquals(todoResponse.getContent(), mockTodo.getContent());
    }
}
