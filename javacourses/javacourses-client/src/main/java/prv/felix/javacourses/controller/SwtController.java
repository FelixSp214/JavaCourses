package prv.felix.javacourses.controller;

import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.Columns_JavaCourses;
import prv.felix.javacourses.enums.SearchType;
import prv.felix.javacourses.enums.SortType;
import prv.felix.javacourses.rmi.IRmiService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SwtController implements IRmiService {

    private final IRmiService iRmiService;

    public SwtController(IRmiService iRmiService) {
        this.iRmiService = iRmiService;
    }

    @Override
    public List<JavaCourse> getAllJavaCourses() {
        return iRmiService.getAllJavaCourses();
    }

    @Override
    public List<JavaCourse> getAllSortedJavaCourses(Columns_JavaCourses columns, SortType sortType) {
        return iRmiService.getAllSortedJavaCourses(columns, sortType);
    }

    @Override
    public List<JavaCourse> getAllSearchedJavaCourses(SearchType searchType, String where) {
        return iRmiService.getAllSearchedJavaCourses(searchType, where);
    }

    @Override
    public void createJavaCourse() {
        iRmiService.createJavaCourse();
    }

    @Override
    public void updateJavaCourse() {
        iRmiService.updateJavaCourse();
    }

    @Override
    public void deleteJavaCourse(JavaCourse javaCourse) {
        iRmiService.deleteJavaCourse(javaCourse);
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
