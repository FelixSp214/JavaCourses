package prv.felix.javacourses.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.rmi.IRmiService;

public class RmiService extends UnicastRemoteObject implements IRmiService {

	private static final long serialVersionUID = 1L;

	public RmiService() throws RemoteException {
		super();
	}

	@Override
	public List<JavaCourse> getAllJavaCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JavaCourse> getAllSortedJavaCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JavaCourse> getAllSearchedJavaCourses() {
		// TODO Auto-generated method stub
		return null;
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
