package prv.felix.javacourses.rmi;

import java.io.IOException;
import java.nio.file.Path;
import java.rmi.Remote;
import java.util.List;

import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.Columns_JavaCourses;
import prv.felix.javacourses.enums.SearchType;
import prv.felix.javacourses.enums.SortType;

public interface IRmiService extends Remote {

	public List<JavaCourse> getAllJavaCourses() throws Exception;

    List<JavaCourse> getAllSortedJavaCourses(Columns_JavaCourses columns, SortType sortType) throws Exception;

	List<JavaCourse> getAllSearchedJavaCourses(SearchType searchType, String where) throws Exception;

	public void createJavaCourse(JavaCourse javaCourse) throws Exception;

	public void updateJavaCourse(JavaCourse javaCourse) throws Exception;

	public void deleteJavaCourse(JavaCourse javaCourse) throws Exception;

	public void exportCsv(List<JavaCourse> javaCourseList, Path path) throws Exception;

	public void exportPdf(List<JavaCourse> javaCourseList, Path path) throws Exception;

	public void exportXml(List<JavaCourse> javaCourseList, Path path) throws Exception;

	public List<JavaCourse> importCsv() throws Exception;

	public List<JavaCourse> importXml() throws Exception;

}
