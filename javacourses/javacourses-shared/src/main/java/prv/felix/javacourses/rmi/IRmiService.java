package prv.felix.javacourses.rmi;

import java.util.List;

import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.Columns_JavaCourses;
import prv.felix.javacourses.enums.SearchType;
import prv.felix.javacourses.enums.SortType;

public interface IRmiService {

	public List<JavaCourse> getAllJavaCourses();

    List<JavaCourse> getAllSortedJavaCourses(Columns_JavaCourses columns, SortType sortType);

	List<JavaCourse> getAllSearchedJavaCourses(SearchType searchType, String where);

	public void createJavaCourse();

	public void updateJavaCourse();

	public void deleteJavaCourse();

}
