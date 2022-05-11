package {{cookiecutter.package_name}}.exception;

import java.util.UUID;

public class TodoNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE =
            "Todo with id (%s) not found.";

    public TodoNotFoundException(UUID id) {
        super(String.format(ERROR_MESSAGE, id.toString()));
    }
}