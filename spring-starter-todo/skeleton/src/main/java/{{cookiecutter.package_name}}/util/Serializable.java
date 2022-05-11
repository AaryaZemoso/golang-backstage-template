package {{cookiecutter.package_name}}.util;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@JsonSerialize
@Value.Style(
        jdkOnly = true,
        headerComments = true,
        get = {"get*", "has*", "is*"},
        depluralize = true,
        typeAbstract = {"Abstract*"},
        typeImmutable = "*",
        stagedBuilder = true,
        visibility = Value.Style.ImplementationVisibility.PUBLIC,
        builderVisibility = Value.Style.BuilderVisibility.PUBLIC,
        validationMethod = Value.Style.ValidationMethod.SIMPLE
)
public @interface Serializable {
}