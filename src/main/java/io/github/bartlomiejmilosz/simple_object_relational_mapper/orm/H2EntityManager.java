package io.github.bartlomiejmilosz.simple_object_relational_mapper.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2EntityManager<T> extends AbstractEntityManager<T> {

	public Connection buildConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:h2:/home/bart/DevProjects/PluralSight/java/reflection-playbook/database-files/database",
				"sa", "");
	}
}
