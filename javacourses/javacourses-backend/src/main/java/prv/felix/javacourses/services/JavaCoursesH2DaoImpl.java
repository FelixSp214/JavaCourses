package prv.felix.javacourses.services;

import java.io.IOException;
import java.rmi.Remote;
import java.sql.SQLException;
import java.util.List;
import java.nio.file.Path;

import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.*;
import prv.felix.javacourses.exporting.ExportCsv;
import prv.felix.javacourses.interfaces.IJavaCourseDao;
import prv.felix.javacourses.utils.Guarding;

public class JavaCoursesH2DaoImpl implements IJavaCourseDao, Remote {

    private static final String DB_TABLE = "JAVACOURSES";

    private final String jdbc_url;
    private final String username;
    private final String password;

    public JavaCoursesH2DaoImpl(String jdbc_url, String username, String password) {
        this.jdbc_url = jdbc_url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<JavaCourse> getAllJavaCourses() throws SQLException {
        H2DataBaseConnection connection =
                new H2DataBaseConnection(jdbc_url, username, password);
        String sql = "SELECT jc.uuid, jc.name, jc.description, jc.duration_in_hours, " +
                "jc.max_participants, jc.cost_in_euros, ct.type_name AS course_type, " +
                "ds.state_name AS db_state, ds.color AS db_state_color " +
                "FROM JavaCourse jc " +
                "JOIN CourseType ct ON jc.course_type = ct.type_id" +
                "JOIN DBState ds ON jc.dbState = ds.state_id;";
        return connection.executeGetSqlQuery(sql);
    }

    @Override
    public List<JavaCourse> getAllSortedJavaCourses(Columns_JavaCourses columns, SortType sort) throws SQLException {
        Guarding.ensureNotNull(sort);
        Guarding.ensureNotNull(columns);

        String sql = "SELECT * FROM JavaCourse " +
                "JOIN CourseType ct ON JavaCourse.course_type = ct.type_id " +
                "JOIN DBState ds ON JavaCourse.dbState = ds.state_id " +
                "ORDER BY " + columns.name() + " " + sort.name();
        H2DataBaseConnection connection =
                new H2DataBaseConnection(jdbc_url, username, password);
        return connection.executeGetSqlQuery(sql);
    }

    @Override // TODO search Type = column ?
    public List<JavaCourse> getAllSearchedJavaCourses(SearchType search, String where) throws SQLException {
        Guarding.ensureNotNull(search);
        Guarding.ensureNotNull(where);

        String sql = "SELECT * FROM " + DB_TABLE + " WHERE " + search + " = " + where;
        String test = "SELECT jc.*, \n" +
                "       ct.type_name AS course_type, \n" +
                "       ds.state_name AS db_state, \n" +
                "       ds.color AS db_state_color\n" +
                "FROM JavaCourse jc\n" +
                "JOIN CourseType ct ON jc.course_type = ct.type_id\n" +
                "JOIN DBState ds ON jc.dbState = ds.state_id\n" +
                "WHERE {column_name} = ?;";
        H2DataBaseConnection connection =
                new H2DataBaseConnection(jdbc_url, username, password);
        return connection.executeGetSqlQuery(sql);
    }

    @Override
    public void createJavaCourse(JavaCourse javaCourse) {
        Guarding.ensureNotNull(javaCourse);

        String uuid = String.valueOf(javaCourse.getUuid());
        String name = javaCourse.getCourseName();
        String desc = javaCourse.getDescription();
        String duration = String.valueOf(javaCourse.getDurationInHours());
        String maxParticipants = String.valueOf(javaCourse.getMaxParticipants());
        String cost = String.valueOf(javaCourse.getCostInEuros());
        String courseType = String.valueOf(javaCourse.getCourseTyp());
        String dbState = String.valueOf(javaCourse.getDbState());

        String sql = "INSERT INTO JavaCourse (\n" +
                "uuid, name, description, duration_in_hours, max_participants, cost_in_euros, \n" +
                "course_type, dbState\n" +
                ") VALUES ( ' " + uuid + "' , ' " + name + "' , ' " + desc + "' , " +
                "' " + duration + "' , ' " + maxParticipants + "' , ' " + cost + "' , " +
                "' " + courseType + "' , ' " + dbState + " );";
    }

    @Override
    public void updateJavaCourse(JavaCourse javaCourse) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteJavaCourse(JavaCourse javaCourse) throws SQLException {
        Guarding.ensureNotNull(javaCourse);
        javaCourse.setDbState(DBState.DELETED);

        String sql = "DELETE FROM JAVACOURSES WHERE uuid = " + javaCourse.getUuid();

        H2DataBaseConnection connection =
                new H2DataBaseConnection(jdbc_url, username, password);
        connection.executeSetSqlQuery(sql);
    }

    @Override
    public void exportCsv(List<JavaCourse> javaCourseList, Path path) throws IOException {
        ExportCsv csv = new ExportCsv();
        csv.exportJavaCoursesToCsv(javaCourseList, path);
    }

    @Override
    public void exportPdf(List<JavaCourse> javaCourseList, Path path) {

    }

    @Override
    public void exportXml(List<JavaCourse> javaCourseList, Path path) {

    }

    @Override
    public List<JavaCourse> importCsv() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<JavaCourse> importXml() {
        // TODO Auto-generated method stub
        return null;
    }

}
