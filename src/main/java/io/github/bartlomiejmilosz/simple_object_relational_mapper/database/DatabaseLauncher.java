package io.github.bartlomiejmilosz.simple_object_relational_mapper.database;

import org.h2.tools.Server;

import java.sql.SQLException;

public class DatabaseLauncher {
    public static void main(String[] args) throws SQLException {
        Server.main();
        System.out.println("DB Launched");
    }
}
