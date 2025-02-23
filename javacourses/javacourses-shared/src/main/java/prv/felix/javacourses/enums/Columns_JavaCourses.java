package prv.felix.javacourses.enums;

import java.util.HashMap;
import java.util.Map;

public enum Columns_JavaCourses {

    ID("id"),
    NAME("Name"),
    DESCRIPTION("Description"),
    DURATION("Duration"),
    MAX_PARTICIPANTS("Max Participants"),
    COST_IN_EUROS("Cost in euros"),
    COURSE_TYPE("Course type"),
    DB_STATE("DB State");

    private static final Map<String, Columns_JavaCourses> BY_COLUMNS_JAVA_COURSES = new HashMap<>();

    static {
        for (Columns_JavaCourses e : values()) {
            BY_COLUMNS_JAVA_COURSES.put(e.cloumns_javacourses, e);
        }
    }
    public final String cloumns_javacourses;

    private Columns_JavaCourses(String exportType) {
        this.cloumns_javacourses = exportType;
    }

    public static Columns_JavaCourses getColumns(String label) {
        return BY_COLUMNS_JAVA_COURSES.get(label);
    }

}
