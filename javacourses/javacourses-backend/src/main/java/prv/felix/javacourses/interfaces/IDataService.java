package prv.felix.javacourses.interfaces;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.Columns_JavaCourses;
import prv.felix.javacourses.enums.SearchType;
import prv.felix.javacourses.enums.SortType;

public interface IDataService {

	public List<JavaCourse> getAllJavaCourses();

	List<JavaCourse> getAllSortedJavaCourses(Columns_JavaCourses columns, SortType sortType);

	List<JavaCourse> getAllSearchedJavaCourses(SearchType searchType, String where);

	public void createJavaCourse();

	public void updateJavaCourse();

	public void deleteJavaCourse(JavaCourse javaCourse);

	public void exportCsv(List<JavaCourse> javaCourseList, Path path) throws IOException;

	public void exportPdf(List<JavaCourse> javaCourseList, Path path);

	public void exportXml(List<JavaCourse> javaCourseList, Path path);

	public List<JavaCourse> importCsv();

	public List<JavaCourse> importXml();
}
