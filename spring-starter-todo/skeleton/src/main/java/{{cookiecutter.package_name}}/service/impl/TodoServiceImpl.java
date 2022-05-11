package {{cookiecutter.package_name}}.service.impl;

import com.google.common.base.Preconditions;
import {{cookiecutter.package_name}}.model.Todo;
import {{cookiecutter.package_name}}.model.request.CreateTodoRequest;
import {{cookiecutter.package_name}}.model.request.UpdateTodoRequest;
import {{cookiecutter.package_name}}.model.response.TodoResponse;
import {{cookiecutter.package_name}}.repository.TodoRepository;
import {{cookiecutter.package_name}}.service.TodoService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TodoServiceImpl implements TodoService {

    private final TodoRepository fTodoRepository;

    public TodoServiceImpl(final TodoRepository todoRepository) {
        fTodoRepository = todoRepository;
    }

    @Override
    public TodoResponse createTodo(CreateTodoRequest request) {
        Preconditions.checkNotNull(request, "CreateTodoRequest cannot be null");
        var todo = new Todo(UUID.randomUUID(), request.getContent());
        todo = fTodoRepository.save(todo);
        return convertTodoToTodoResponse(todo);
    }

    @Override
    public TodoResponse updateTodo(UUID id, UpdateTodoRequest request) {
        Preconditions.checkNotNull(id, "id cannot be null");
        Preconditions.checkNotNull(request, "UpdateTodoRequest cannot be null");

        var todo = getTodoById(id);

        todo.setContent(request.getContent());

        fTodoRepository.save(todo);

        return convertTodoToTodoResponse(todo);
    }

    @Override
    public TodoResponse getTodo(UUID id) {
        Preconditions.checkNotNull(id, "id cannot be null");
        var todo = getTodoById(id);
        return convertTodoToTodoResponse(todo);
    }

    @Override
    public List<TodoResponse> getAllTodos() {
        var todos = fTodoRepository.findAll();
        return todos.stream()
                .map(this::convertTodoToTodoResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    private Todo getTodoById(UUID id) {
        return fTodoRepository.getOne(id);
    }

    private TodoResponse convertTodoToTodoResponse(final Todo todo) {
        Preconditions.checkNotNull(todo, "todo cannot be null");
        return TodoResponse.builder()
                .id(todo.getId())
                .content(todo.getContent())
                .build();
    }
}