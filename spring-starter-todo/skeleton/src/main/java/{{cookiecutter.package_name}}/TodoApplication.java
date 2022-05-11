package {{cookiecutter.package_name}};

import {{cookiecutter.package_name}}.repository.TodoRepository;
import {{cookiecutter.package_name}}.service.TodoService;
import {{cookiecutter.package_name}}.service.impl.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = "{{cookiecutter.package_name}}.model")
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Bean
	public TodoService configureTodoService(final TodoRepository todoRepository) {
		return new TodoServiceImpl(todoRepository);
	}

	@Bean
	public DataSource configureDataSource(
			@Value("${spring.datasource.url}") final String url,
			@Value("${spring.datasource.username}") final String username,
			@Value("${spring.datasource.password}") final String password,
			@Value("${spring.datasource.driver-class-name}") final String driverClassName) {
		var dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(driverClassName);
		dataSourceBuilder.url(url);
		dataSourceBuilder.username(username);
		dataSourceBuilder.password(password);
		return dataSourceBuilder.build();
	}
}