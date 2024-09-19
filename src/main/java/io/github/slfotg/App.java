package io.github.slfotg;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        var dataSource = DataSourceConfig.getDataSource();
        // Run Liquibase migrations
        LiquibaseRunner.runLiquibase(dataSource);
        DatabaseUtils.insertRecord(dataSource, "Sam Foster", Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now()));

    }
}
