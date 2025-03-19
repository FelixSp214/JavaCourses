package prv.felix.javacourses.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.utils.Converter;

public class H2DataBaseConnection {

    private static final Logger LOGGER = LogManager.getLogger(H2DataBaseConnection.class);

    private final String url;
    private final String user;
    private final String password;

    public H2DataBaseConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to H2 database successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("H2 Database Driver not found.");
            LOGGER.error("H2 Database Driver not found");
        } catch (SQLException e) {
            System.err.println("Failed to connect to H2 database.");
            LOGGER.error("Failed to connect to H2 database");
        }
        return connection;
    }

    public List<JavaCourse> executeGetSqlQuery(String sql) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            int columnCount = resultSet.getMetaData().getColumnCount();

            List<JavaCourse> javaCourses = new ArrayList<>();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    javaCourses.add(new JavaCourse(UUID.fromString(resultSet.getString(1)),
                            resultSet.getString(2),
                            resultSet.getString(3), resultSet.getInt(4),
                            resultSet.getInt(5),
                            resultSet.getDouble(6),
                            Converter.convertFromStringToCourseType(resultSet.getString(7)),
                            Converter.convertFromStringToDbState(resultSet.getString(8))));
                }
            }
            return javaCourses;

        } catch (SQLException e) {
            throw new SQLException("Can not execute SQL Statement and can not get any javacourses " + e.getMessage());
        }
    }

    public void executeSetSqlQuery(String sql) throws SQLException {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new SQLException("Can not execute SQL Statement");
        }
    }

}
