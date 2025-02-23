package prv.felix.javacourses.services;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.Columns_JavaCourses;
import prv.felix.javacourses.enums.SearchType;
import prv.felix.javacourses.enums.SortType;
import prv.felix.javacourses.interfaces.IDataService;
import prv.felix.javacourses.rmi.IRmiService;

public class RmiService extends UnicastRemoteObject implements IRmiService {

	@Serial
	private static final long serialVersionUID = 1L;
	private IDataService dataService;

	public RmiService() throws RemoteException {
		super();
	}

	@Override
	public List<JavaCourse> getAllJavaCourses() {
		return dataService.getAllJavaCourses();
	}

	@Override
	public List<JavaCourse> getAllSortedJavaCourses(Columns_JavaCourses columns, SortType sortType) {
		return dataService.getAllSortedJavaCourses(columns, sortType);
	}

	@Override
	public List<JavaCourse> getAllSearchedJavaCourses(SearchType searchType, String where) {
		return dataService.getAllSearchedJavaCourses(searchType, where);
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
	public void deleteJavaCourse() {
		// TODO Auto-generated method stub

	}

}
