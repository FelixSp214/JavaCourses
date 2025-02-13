package prv.felix.javacourses.interfaces;

import prv.felix.javacourses.entities.JavaCourse;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface IExporting {

	public void exportCsv(List<JavaCourse> javaCourseList, Path path) throws IOException;

	public void exportPdf(List<JavaCourse> javaCourseList, Path path);

	public void exportXml(List<JavaCourse> javaCourseList, Path path);
}
