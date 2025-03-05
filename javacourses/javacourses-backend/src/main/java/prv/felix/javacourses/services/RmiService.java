package prv.felix.javacourses.services;

import java.io.IOException;
import java.io.Serial;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.Columns_JavaCourses;
import prv.felix.javacourses.enums.SearchType;
import prv.felix.javacourses.enums.SortType;
import prv.felix.javacourses.interfaces.IDataService;
import prv.felix.javacourses.rmi.IRmiService;

public class RmiService implements IRmiService {

	private final IDataService dataService;

	public RmiService(IDataService dataService) {
		this.dataService = dataService;
	}

	@Override
	public List<JavaCourse> getAllJavaCourses() {
		try {
			return dataService.getAllJavaCourses();
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<JavaCourse> getAllSortedJavaCourses(Columns_JavaCourses columns, SortType sortType) {
		try {
			return dataService.getAllSortedJavaCourses(columns, sortType);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<JavaCourse> getAllSearchedJavaCourses(SearchType searchType, String where) {
		try {
			return dataService.getAllSearchedJavaCourses(searchType, where);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void createJavaCourse() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateJavaCourse() {
		System.out.println("Test");

	}

	@Override
	public void deleteJavaCourse(JavaCourse javaCourse) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exportCsv(List<JavaCourse> javaCourseList, Path path) throws IOException {

	}

	@Override
	public void exportPdf(List<JavaCourse> javaCourseList, Path path) {

	}

	@Override
	public void exportXml(List<JavaCourse> javaCourseList, Path path) {

	}

	@Override
	public List<JavaCourse> importCsv() {
		return List.of();
	}

	@Override
	public List<JavaCourse> importXml() {
		return List.of();
	}

}
