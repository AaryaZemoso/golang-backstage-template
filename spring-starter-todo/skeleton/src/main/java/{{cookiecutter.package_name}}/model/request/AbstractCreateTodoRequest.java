package {{cookiecutter.package_name}}.model.request;

import {{cookiecutter.package_name}}.util.Serializable;
import org.immutables.value.Value;

@Serializable
@Value.Immutable
public abstract class AbstractCreateTodoRequest extends AbstractTodoRequestBase {

}
