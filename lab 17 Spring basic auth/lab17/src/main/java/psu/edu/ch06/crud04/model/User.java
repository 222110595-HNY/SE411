package psu.edu.ch06.crud04.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

// "users" instead of "user" to avoid clashing with the SQL keyword
@Table("users")
public record User(
		@Id Integer id,
		String username,
		String password
) {

}
