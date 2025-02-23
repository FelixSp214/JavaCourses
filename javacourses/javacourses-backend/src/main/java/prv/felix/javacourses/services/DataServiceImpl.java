package prv.felix.javacourses.services;

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
	public List<JavaCourse> getAllSortedJavaCourses(Columns_JavaCourses columns, SortType sort) {
		return javaCourseDao.getAllSortedJavaCourses(columns, sort);
	}

	@Override
	public List<JavaCourse> getAllSearchedJavaCourses(SearchType search, String where) {
		return javaCourseDao.getAllSearchedJavaCourses(search, where);
	}

	@Override
	public void createJavaCourse() {
		javaCourseDao.createJavaCourse();
	}

	@Override
	public void updateJavaCourse() {
		javaCourseDao.updateJavaCourse();
	}

	@Override
	public void deleteJavaCourse(JavaCourse javaCourse) {
		javaCourseDao.deleteJavaCourse(javaCourse);
	}

}
