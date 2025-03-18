package prv.felix.javacourses.services;

import java.io.IOException;
import java.io.Serial;
import java.nio.file.Path;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.Columns_JavaCourses;
import prv.felix.javacourses.enums.SearchType;
import prv.felix.javacourses.enums.SortType;
import prv.felix.javacourses.interfaces.IDataService;
import prv.felix.javacourses.rmi.IRmiService;

public class RmiService implements IRmiService, Remote {

	private final IDataService dataService;

	public RmiService(IDataService dataService) {
		this.dataService = dataService;
	}

	@Override
	public List<JavaCourse> getAllJavaCourses() {
		try {
			return dataService.getAllJavaCourses();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<JavaCourse> getAllSortedJavaCourses(Columns_JavaCourses columns, SortType sortType) {
		try {
			return dataService.getAllSortedJavaCourses(columns, sortType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<JavaCourse> getAllSearchedJavaCourses(SearchType searchType, String where) {
		try {
			return dataService.getAllSearchedJavaCourses(searchType, where);
		} catch (RuntimeException | SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void createJavaCourse(JavaCourse javaCourse) {
		try {
			dataService.createJavaCourse(javaCourse);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void updateJavaCourse(JavaCourse javaCourse) {
		try {
			dataService.updateJavaCourse(javaCourse);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void deleteJavaCourse(JavaCourse javaCourse) {
		try {
			dataService.deleteJavaCourse(javaCourse);
		} catch (RuntimeException | SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void exportCsv(List<JavaCourse> javaCourseList, Path path) throws IOException {
		try {
			dataService.exportCsv(javaCourseList, path);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void exportPdf(List<JavaCourse> javaCourseList, Path path) {
		try {
			 dataService.exportPdf(javaCourseList, path);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void exportXml(List<JavaCourse> javaCourseList, Path path) {
		try {
            dataService.exportCsv(javaCourseList, path);
        } catch (RuntimeException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<JavaCourse> importCsv() {
		try {
			return dataService.importCsv();
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<JavaCourse> importXml() {
		try {
			return dataService.importXml();
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}

}
