package prv.felix.javacourses.enums;

import java.util.HashMap;
import java.util.Map;

public enum ExportType {

	XML(".xml"), CSV(".csv"), PDF(".pdf");

	private static final Map<String, ExportType> BY_EXPORT_TYPE = new HashMap<>();

	static {
		for (ExportType e : values()) {
			BY_EXPORT_TYPE.put(e.exportType, e);
		}
	}

	public final String exportType;

	private ExportType(String exportType) {
		this.exportType = exportType;
	}

	public static ExportType getExportType(String label) {
		return BY_EXPORT_TYPE.get(label);
	}

}
