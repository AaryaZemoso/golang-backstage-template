package {{cookiecutter.package_name}}.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @Column(columnDefinition = "uuid default uuid_generate_v4()", unique = true, updatable = false, nullable = false)
    private UUID id;

    private String content;
}
