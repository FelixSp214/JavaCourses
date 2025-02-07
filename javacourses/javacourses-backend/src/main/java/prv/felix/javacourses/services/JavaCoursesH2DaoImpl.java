package prv.felix.javacourses.services;

import java.util.List;
import java.util.UUID;

import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.SearchType;
import prv.felix.javacourses.enums.SortType;
import prv.felix.javacourses.interfaces.IExporting;
import prv.felix.javacourses.interfaces.IImporting;
import prv.felix.javacourses.interfaces.IJavaCourseDao;
import prv.felix.javacourses.utils.Guarding;
import prv.felix.javacourses.utils.H2DataBaseConnection;

public class JavaCoursesH2DaoImpl implements IJavaCourseDao, IImporting, IExporting {

	H2DataBaseConnection connection = new H2DataBaseConnection();

	@Override
	public List<JavaCourse> getAllJavaCourses() {
		String sql = "SELECT * FROM JAVACOURSES";
		return connection.executeSqlStatment(sql);
	}

	@Override
	public List<JavaCourse> getAllSortedJavaCourses(SortType sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JavaCourse> getAllSearchedJavaCourses(SearchType search, String where) {
		Guarding.ensureNotNull(search);
		Guarding.ensureNotNull(where);

		String sql = "SELECT * FROM JAVACOURSES WHERE " + search + " = " + where;
		return connection.executeSqlStatment(sql);
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
	public void deleteJavaCourse(UUID uuid) {
		Guarding.ensureNotNull(uuid);

		String sql = "DELETE FROM JAVACOURSES WHERE id = " + uuid;
		connection.executeSqlStatment(sql);
	}

	@Override
	public void exportCsv() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exportPdf() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exportXml() {
		// TODO Auto-generated method stub

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
