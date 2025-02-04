package prv.felix.javacourses.utils;

public class Guarding {

	public static <T> T ensureNotNull(T object) {
		if (object == null) {
			throw new NullPointerException("Object is null");
		}
		return object;
	}

}
