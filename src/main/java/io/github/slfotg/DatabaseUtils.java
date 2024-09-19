package io.github.slfotg;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

public class DatabaseUtils {

    public static void insertRecord(DataSource dataSource, String name, Timestamp createdAt, Timestamp other) {
        String insertSQL = "INSERT INTO example_table (name, created_at, my_tz) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setTimestamp(2, createdAt);
            preparedStatement.setTimestamp(3, other, Calendar.getInstance(TimeZone.getTimeZone("GMT")));

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}