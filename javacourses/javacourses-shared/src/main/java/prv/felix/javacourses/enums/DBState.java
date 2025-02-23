package prv.felix.javacourses.enums;

import java.util.HashMap;
import java.util.Map;

public enum DBState {
    ACTIVE("ACTIVE"),
    DELETED("DELETED");

    private static final Map<String, DBState> BY_DB_STATE = new HashMap<>();

    static {
        for (DBState e : values()) {
            BY_DB_STATE.put(e.dbstate, e);
        }
    }
    public final String dbstate;

    private DBState(String dbstate) {
        this.dbstate = dbstate;
    }

    public static DBState getDBState(String label) {
        return BY_DB_STATE.get(label);
    }

}
