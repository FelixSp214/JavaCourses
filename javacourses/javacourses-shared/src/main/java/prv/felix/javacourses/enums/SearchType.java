package prv.felix.javacourses.enums;

import java.util.HashMap;
import java.util.Map;

public enum SearchType {

	BY_NAME("NAME"), BY_COURSETYPE("COURSETYPE");

	private static final Map<String, SearchType> SORT = new HashMap<>();

	static {
		for (SearchType e : values()) {
			SORT.put(e.search, e);
		}
	}

	public final String search;

	private SearchType(String exportType) {
		this.search = exportType;
	}

	public static SearchType getSearchCriteria(String label) {
		return SORT.get(label);
	}

}
