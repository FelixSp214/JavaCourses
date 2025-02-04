package prv.felix.javacourses.enums;

import java.util.HashMap;
import java.util.Map;

public enum SortType {

	BY_UUID(""), BY_NAME(""), BY_DURATION(""), BY_MAXPARTICIPANTS(""), BY_COST(""), BY_COURSETYPE("");

	private static final Map<String, SortType> SORT = new HashMap<>();

	static {
		for (SortType e : values()) {
			SORT.put(e.search, e);
		}
	}

	public final String search;
	private static final String ASCENDING = "ASC"; // TODO extra enum
	private static final String DESCENDING = "DESC";

	private SortType(String exportType) {
		this.search = exportType;
	}

	public static SortType getSearchCriteria(String label) {
		return SORT.get(label);
	}

}
