package {{cookiecutter.package_name}}.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import org.immutables.value.Value;

public abstract class AbstractTodoRequestBase {

    @JsonProperty("content")
    public abstract String getContent();

    @Value.Check
    public void validate() {
        Preconditions.checkArgument(getContent() != null && !getContent().isBlank(),
                "content cannot be null or empty");
    }
}