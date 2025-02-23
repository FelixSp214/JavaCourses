package prv.felix.javacourses.enums;

import java.util.HashMap;
import java.util.Map;

public enum SortType {
	ASCENDING("ASC"),
	DESCENDING("DESC");

	private static final Map<String, SortType> BY_COURSE_TYPE = new HashMap<>();

	static {
		for (SortType e : values()) {
			BY_COURSE_TYPE.put(e.coursesType, e);
		}
	}
	public final String coursesType;

	private SortType(String coursesType) {
		this.coursesType = coursesType;
	}

	public static SortType getSortType(String label) {
		return BY_COURSE_TYPE.get(label);
	}
}
