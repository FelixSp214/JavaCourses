package prv.felix.javacourses.rmi;

import java.util.List;

import prv.felix.javacourses.entities.JavaCourse;

public interface IRmiService {

	public List<JavaCourse> getAllJavaCourses();

	public List<JavaCourse> getAllSortedJavaCourses();

	public List<JavaCourse> getAllSearchedJavaCourses();

	public void createJavaCourse();

	public void updateJavaCourse();

	public void deleteJavaCourse();

}
