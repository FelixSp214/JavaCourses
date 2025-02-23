package prv.felix.javacourses.interfaces;

import java.util.List;
import java.util.UUID;

import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.Columns_JavaCourses;
import prv.felix.javacourses.enums.SearchType;
import prv.felix.javacourses.enums.SortType;

public interface IJavaCourseDao {

	public List<JavaCourse> getAllJavaCourses();

	public List<JavaCourse> getAllSortedJavaCourses(Columns_JavaCourses columns, SortType sort);

	public List<JavaCourse> getAllSearchedJavaCourses(SearchType search, String where);

	public void createJavaCourse();

	public void updateJavaCourse();

	public void deleteJavaCourse(JavaCourse javaCourse);
}
