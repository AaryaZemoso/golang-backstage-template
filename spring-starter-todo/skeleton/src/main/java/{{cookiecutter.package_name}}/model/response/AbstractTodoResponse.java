package {{cookiecutter.package_name}}.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import {{cookiecutter.package_name}}.util.Serializable;
import org.immutables.value.Value;

import java.util.UUID;

@Serializable
@Value.Immutable
public abstract class AbstractTodoResponse {

    @JsonProperty("id")
    public abstract UUID getId();

    @JsonProperty("content")
    public abstract String getContent();
}