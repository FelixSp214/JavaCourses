package prv.felix.javacourses.interfaces;

import java.util.List;

import prv.felix.javacourses.entities.JavaCourse;

public interface IImporting {

	public List<JavaCourse> importCsv();

	public List<JavaCourse> importXml();

}
