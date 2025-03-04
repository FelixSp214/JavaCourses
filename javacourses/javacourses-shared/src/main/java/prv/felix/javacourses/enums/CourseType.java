package prv.felix.javacourses.enums;

import java.util.HashMap;
import java.util.Map;

public enum CourseType {

    TASTER_COURSE("Taster Course"),
    BEGINNER("Beginner"),
    ADVANCED("Advanced"),
    EXPERT("Expert");

    private static final Map<String, CourseType> BY_COURSE_TYPE = new HashMap<>();

    static {
        for (CourseType e : values()) {
            BY_COURSE_TYPE.put(e.coursesType, e);
        }
    }
    public final String coursesType;

    private CourseType(String coursesType) {
        this.coursesType = coursesType;
    }

    public static CourseType getCourseType(String label) {
        return BY_COURSE_TYPE.get(label);
    }

}
