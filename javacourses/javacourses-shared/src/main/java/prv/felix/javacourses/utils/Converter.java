package prv.felix.javacourses.utils;

import org.apache.logging.log4j.LogManager;
import prv.felix.javacourses.enums.CourseType;
import prv.felix.javacourses.enums.DBState;

public class Converter {

	public static CourseType convertFromStringToCourseType(String courseTyp) {
        return switch (courseTyp) {
            case "TASTER_COURSE" -> CourseType.TASTER_COURSE;
            case "BEGINNER" -> CourseType.BEGINNER;
            case "ADVANCED" -> CourseType.ADVANCED;
            case "EXPERT" -> CourseType.EXPERT;
            default -> null; // TODO default not null
        };
	}

	public static DBState convertFromStringToDbState(String dbState) {
		return switch (dbState) {
			case "ACTIVE" -> DBState.ACTIVE;
			case "DELETED" -> DBState.DELETED;
            default -> throw new IllegalStateException("Unexpected value: " + dbState);
        };
	}

}
