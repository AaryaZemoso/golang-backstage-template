package {{cookiecutter.package_name}}.controller;

import com.google.common.base.Preconditions;
import {{cookiecutter.package_name}}.model.request.CreateTodoRequest;
import {{cookiecutter.package_name}}.model.request.UpdateTodoRequest;
import {{cookiecutter.package_name}}.model.response.TodoResponse;
import {{cookiecutter.package_name}}.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@RequestBody final CreateTodoRequest request) {
        return ResponseEntity.ok(todoService.createTodo(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable("id") final UUID id,
                                                   @RequestBody UpdateTodoRequest request) {
        return ResponseEntity.ok(todoService.updateTodo(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodo(@PathVariable("id") final UUID id) {
        return ResponseEntity.ok(todoService.getTodo(id));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }
}
