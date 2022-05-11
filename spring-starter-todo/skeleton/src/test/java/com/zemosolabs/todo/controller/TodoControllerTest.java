package com.zemosolabs.{{cookiecutter.package_name}}.controller;

import com.google.gson.Gson;
import com.zemosolabs.{{cookiecutter.package_name}}.TodoApplication;
import com.zemosolabs.{{cookiecutter.package_name}}.TodoTestApplication;
import com.zemosolabs.{{cookiecutter.package_name}}.model.Todo;
import com.zemosolabs.{{cookiecutter.package_name}}.model.request.CreateTodoRequest;
import com.zemosolabs.{{cookiecutter.package_name}}.model.request.UpdateTodoRequest;
import com.zemosolabs.{{cookiecutter.package_name}}.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        properties = "spring.main.allow-bean-definition-overriding=true",
        classes = {TodoApplication.class, TodoTestApplication.class}
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    private List<Todo> todos = new ArrayList<>();

    private Gson gson = new Gson();

    @BeforeEach
    public void setup() {
        todos.add(new Todo(UUID.randomUUID(), "todo 1"));
        todos.add(new Todo(UUID.randomUUID(), "todo 2"));
        todoRepository.saveAll(todos);
    }

    @AfterEach
    public void teardown() {
        todoRepository.deleteAll();
    }

    @Test
    public void testCreateTodo() throws Exception {
        var mockTodoRequest = CreateTodoRequest.builder()
                .content("create todo")
                .build();
        mockMvc.perform(post("/api/v1/todos")
                .contentType("application/json")
                .content(gson.toJson(mockTodoRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("create todo")));
    }

    @Test
    public void testUpdateTodo() throws Exception {
        var mockTodoRequest = UpdateTodoRequest.builder()
                .content("update")
                .build();
        mockMvc.perform(patch("/api/v1/todos/" + todos.get(0).getId())
                .contentType("application/json")
                .content(gson.toJson(mockTodoRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("update")));
    }

    @Test
    public void testGetAllTodos() throws Exception {
        mockMvc.perform(get("/api/v1/todos"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("todo 1")))
                .andExpect(content().string(containsString("todo 2")));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/api/v1/todos/" + todos.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("todo 1")));

    }
}
