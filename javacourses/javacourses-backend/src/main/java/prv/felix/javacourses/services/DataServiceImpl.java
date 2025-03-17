package prv.felix.javacourses.services;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.Columns_JavaCourses;
import prv.felix.javacourses.enums.SearchType;
import prv.felix.javacourses.enums.SortType;
import prv.felix.javacourses.interfaces.IDataService;
import prv.felix.javacourses.interfaces.IJavaCourseDao;

public class DataServiceImpl implements IDataService {

	private final IJavaCourseDao javaCourseDao;

	public DataServiceImpl(IJavaCourseDao javaCourseDao) {
		this.javaCourseDao = javaCourseDao;
	}

	@Override
	public List<JavaCourse> getAllJavaCourses() {
		return javaCourseDao.getAllJavaCourses();
	}

	@Override
	public List<JavaCourse> getAllSortedJavaCourses(Columns_JavaCourses columns, SortType sort) throws SQLException {
		return javaCourseDao.getAllSortedJavaCourses(columns, sort);
	}

	@Override
	public List<JavaCourse> getAllSearchedJavaCourses(SearchType search, String where) throws SQLException {
		return javaCourseDao.getAllSearchedJavaCourses(search, where);
	}

	@Override
	public void createJavaCourse(JavaCourse javaCourse) {
		javaCourseDao.createJavaCourse(javaCourse);
	}

	@Override
	public void updateJavaCourse(JavaCourse javaCourse) {
		javaCourseDao.updateJavaCourse(javaCourse);
	}

	@Override
	public void deleteJavaCourse(JavaCourse javaCourse) throws SQLException {
		javaCourseDao.deleteJavaCourse(javaCourse);
	}

	@Override
	public void exportCsv(List<JavaCourse> javaCourseList, Path path) throws IOException {

	}

	@Override
	public void exportPdf(List<JavaCourse> javaCourseList, Path path) {

	}

	@Override
	public void exportXml(List<JavaCourse> javaCourseList, Path path) {

	}

	@Override
	public List<JavaCourse> importCsv() {
		return List.of();
	}

	@Override
	public List<JavaCourse> importXml() {
		return List.of();
	}

}
