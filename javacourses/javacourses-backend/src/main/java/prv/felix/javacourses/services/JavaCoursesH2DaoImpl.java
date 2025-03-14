package prv.felix.javacourses.services;

import java.io.IOException;
import java.rmi.Remote;
import java.util.List;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import prv.felix.javacourses.app.App;
import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.*;
import prv.felix.javacourses.exporting.ExportCsv;
import prv.felix.javacourses.interfaces.IJavaCourseDao;
import prv.felix.javacourses.utils.Guarding;
import prv.felix.javacourses.utils.H2DataBaseConnection;

public class JavaCoursesH2DaoImpl implements IJavaCourseDao, Remote {

	private final H2DataBaseConnection connection = new H2DataBaseConnection();
	private static final String DB_TABLE = "JAVACOURSES";
	private static final Logger LOGGER = LogManager.getLogger(JavaCoursesH2DaoImpl.class);

	private String dbUrl;
	private String userName;
	private String password;

	public JavaCoursesH2DaoImpl(String dbUrl, String userName, String password) {
		this.dbUrl = dbUrl;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public List<JavaCourse> getAllJavaCourses() {
		String sql = "SELECT * FROM " + DB_TABLE;
		return connection.executeSqlStatement(sql);
	}

	@Override
	public List<JavaCourse> getAllSortedJavaCourses(Columns_JavaCourses columns, SortType sort) {
		Guarding.ensureNotNull(sort);
		Guarding.ensureNotNull(columns);

		String sql = "SELECT * FROM " + DB_TABLE + " ORDER BY " + columns + " " + sort;
		return connection.executeSqlStatement(sql);
	}

	@Override
	public List<JavaCourse> getAllSearchedJavaCourses(SearchType search, String where) {
		Guarding.ensureNotNull(search);
		Guarding.ensureNotNull(where);

		String sql = "SELECT * FROM " + DB_TABLE + " WHERE " + search + " = " + where;
		return connection.executeSqlStatement(sql);
	}

	@Override
	public void createJavaCourse() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateJavaCourse() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteJavaCourse(JavaCourse javaCourse) {
		Guarding.ensureNotNull(javaCourse);
		javaCourse.setDbState(DBState.DELETED);

		String sql = "DELETE FROM JAVACOURSES WHERE id = " + javaCourse.getUuid();
		String sqlTwo = ""; // TODO insert into deleted DB
		connection.executeSqlStatement(sql);
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
