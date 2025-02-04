package prv.felix.javacourses.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import prv.felix.javacourses.entities.JavaCourse;

public class H2DataBaseConnection {

    private static final String JDBC_URL = "jdbc:h2:~/JavaCourses";
    private static final String USER = "JavaCourses";
    private static final String PASSWORD = "Passwort";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("Connected to H2 database successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("H2 Database Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to H2 database.");
            e.printStackTrace();
        }
        return connection;
    }
    
    public List<JavaCourse> executeSqlStatment(String sql) { // TODO Exception
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            int columnCount = resultSet.getMetaData().getColumnCount();

            List<JavaCourse> javaCourses = new ArrayList<>();;
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    javaCourses.add(new JavaCourse(UUID.fromString(resultSet.getString(1)), resultSet.getString(2), 
                    		resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5), 
                    		resultSet.getDouble(5),
                    		CourseTypeConverter.convertFromStringToCourseType(resultSet.getString(6))));
                }
            }
            return javaCourses;

        } catch (SQLException e) {
            e.getLocalizedMessage();
        }
		return null;
    }
    
}
