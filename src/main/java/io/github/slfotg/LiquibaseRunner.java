package io.github.slfotg;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import javax.sql.DataSource;
import java.sql.Connection;

public class LiquibaseRunner {

    public static void runLiquibase(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));
            try (Liquibase liquibase = new Liquibase("db.changelog-master.xml", new ClassLoaderResourceAccessor(),
                    database)) {
                liquibase.update(new Contexts());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}