package prv.felix.javacourses.enums;

import java.util.HashMap;
import java.util.Map;

public enum Columns_JavaCourses {

    ID("id", 80),
    NAME("Name", 200),
    DESCRIPTION("Beschreibung", 300),
    DURATION("Länge (in h)", 140),
    MAX_PARTICIPANTS("Max. Teilnehmer", 140),
    COST_IN_EUROS("Kosten (in €)", 140),
    COURSE_TYPE("Schwierigkeit", 140),
    DB_STATE("DB State", 0);

    private static final Map<String, Columns_JavaCourses> BY_COLUMNS_JAVA_COURSES = new HashMap<>();

    static {
        for (Columns_JavaCourses e : values()) {
            BY_COLUMNS_JAVA_COURSES.put(e.columnName, e);
        }
    }
    public final String columnName;
    public final int columnWidth;

    Columns_JavaCourses(String columnName, int columnWidth) {
        this.columnName = columnName;
        this.columnWidth = columnWidth;
    }

    public static Columns_JavaCourses getColumns(String label) {
        return BY_COLUMNS_JAVA_COURSES.get(label);
    }

}
