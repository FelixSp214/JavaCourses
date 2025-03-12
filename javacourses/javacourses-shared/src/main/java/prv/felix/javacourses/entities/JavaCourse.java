package prv.felix.javacourses.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import prv.felix.javacourses.enums.CourseType;
import prv.felix.javacourses.enums.DBState;
import prv.felix.javacourses.utils.Guarding;

public class JavaCourse implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private String courseName;
	private String description;
	private int durationInHours;
	private int maxParticipants;
	private double costInEuros;
	private CourseType courseTyp;
	private DBState dbState;

	public JavaCourse(UUID uuid, String courseName, String description, int durationInHours, int maxParticipants,
					  double costInEuros, CourseType courseTyp, DBState dbState) {
		this.uuid = Guarding.ensureNotNull(uuid);
		this.courseName = Guarding.ensureNotNull(courseName);
		this.description = Guarding.ensureNotNull(description);
		this.durationInHours = durationInHours;
		this.maxParticipants = maxParticipants;
		this.costInEuros = costInEuros;
		this.courseTyp = Guarding.ensureNotNull(courseTyp);
		this.dbState = Guarding.ensureNotNull(dbState);
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDurationInHours() {
		return durationInHours;
	}

	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}

	public int getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public double getCostInEuros() {
		return costInEuros;
	}

	public void setCostInEuros(double costInEuros) {
		this.costInEuros = costInEuros;
	}

	public CourseType getCourseTyp() {
		return courseTyp;
	}

	public void setCourseTyp(CourseType courseTyp) {
		this.courseTyp = courseTyp;
	}

	public DBState getDbState() {
		return dbState;
	}

	public void setDbState(DBState dbState) {
		this.dbState = dbState;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		JavaCourse that = (JavaCourse) o;
		return durationInHours == that.durationInHours && maxParticipants == that.maxParticipants && Double.compare(costInEuros, that.costInEuros) == 0 && Objects.equals(uuid, that.uuid) && Objects.equals(courseName, that.courseName) && Objects.equals(description, that.description) && courseTyp == that.courseTyp && dbState == that.dbState;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid, courseName, description, durationInHours, maxParticipants, costInEuros, courseTyp, dbState);
	}

	@Override
	public String toString() {
		return "JavaCourse{" +
				"uuid=" + uuid +
				", courseName='" + courseName + '\'' +
				", description='" + description + '\'' +
				", durationInHours=" + durationInHours +
				", maxParticipants=" + maxParticipants +
				", costInEuros=" + costInEuros +
				", courseTyp=" + courseTyp +
				", dbState=" + dbState +
				'}';
	}

}
