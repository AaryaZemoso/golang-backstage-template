package {{cookiecutter.package_name}}.service;

import {{cookiecutter.package_name}}.model.request.CreateTodoRequest;
import {{cookiecutter.package_name}}.model.request.UpdateTodoRequest;
import {{cookiecutter.package_name}}.model.response.AbstractTodoResponse;
import {{cookiecutter.package_name}}.model.response.TodoResponse;

import java.util.List;
import java.util.UUID;

public interface TodoService {

    TodoResponse createTodo(CreateTodoRequest request);

    TodoResponse updateTodo(UUID id, UpdateTodoRequest request);

    TodoResponse getTodo(UUID id);

    List<TodoResponse> getAllTodos();
}
