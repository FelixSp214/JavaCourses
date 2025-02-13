package prv.felix.javacourses.entities;

import java.util.Objects;
import java.util.UUID;

import prv.felix.javacourses.enums.CourseType;
import prv.felix.javacourses.utils.Guarding;

public class JavaCourse {
	
	private UUID uuid;
	private String courseName;
	private String description;
	private int durationInHours;
	private int maxParticipants;
	private double costInEuros;
	private CourseType courseTyp;
	
	public JavaCourse(UUID uuid, String courseName, String description, int durationInHours, int maxParticipants,
			double costInEuros, CourseType courseTyp) {
		this.uuid = Guarding.ensureNotNull(uuid);
		this.courseName = Guarding.ensureNotNull(courseName);
		this.description = Guarding.ensureNotNull(description);
		this.durationInHours = durationInHours;
		this.maxParticipants = maxParticipants;
		this.costInEuros = costInEuros;
		this.courseTyp = Guarding.ensureNotNull(courseTyp);
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
	
	@Override
	public String toString() {
		return "JavaCourse [uuid=" + uuid + ", courseName=" + courseName + ", description=" + description
				+ ", durationInHours=" + durationInHours + ", maxParticipants=" + maxParticipants + ", costInEuros="
				+ costInEuros + ", courseTyp=" + courseTyp + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(costInEuros, courseName, courseTyp, description, durationInHours, maxParticipants, uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JavaCourse other = (JavaCourse) obj;
		return costInEuros == other.costInEuros && Objects.equals(courseName, other.courseName)
				&& courseTyp == other.courseTyp && Objects.equals(description, other.description)
				&& durationInHours == other.durationInHours && maxParticipants == other.maxParticipants
				&& Objects.equals(uuid, other.uuid);
	}
	
}
