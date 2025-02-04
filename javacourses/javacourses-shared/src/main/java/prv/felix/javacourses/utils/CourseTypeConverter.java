package prv.felix.javacourses.utils;

import prv.felix.javacourses.enums.CourseType;

public class CourseTypeConverter {
	
	public static CourseType convertFromStringToCourseType(String courseTyp) {
		switch(courseTyp) {
		case "TASTERCOURSE":
			return CourseType.TASTERCOURSE;
		case "BEGINNER":
			return CourseType.BEGINNER;
		case "ADVANCED":
			return CourseType.ADVANCED;
		case "EXPERT":
			return CourseType.EXPERT;
		default:
			return null;
		}
	}

}
