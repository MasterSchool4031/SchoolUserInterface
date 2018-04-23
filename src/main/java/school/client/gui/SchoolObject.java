package school.client.gui;

import school.client.commons.Factory;
import school.client.commons.Lesson;
import school.client.commons.School;
import school.client.commons.Training;

public class SchoolObject {

	private School school;
	private Training training;
	private Lesson lesson;
	private String lastName;
	private Type type;

	public enum Type {
		SCHOOL, SCHOOL_NAME, TRAINING, TRAINING_NAME, LESSON, LESSON_NAME,
	}

	public SchoolObject(String[] path) {
		super();
		if (path == null || path.length == 0) {
			throw new IllegalArgumentException(
					"school == null && lastName == null");
		} else {
			try {
				this.type = Type.SCHOOL_NAME;
				this.lastName = path[0];
				this.school = Factory.getInstance().getSchool(path[0]);
				this.type = Type.SCHOOL;
				if (path.length > 1) {
					this.type = Type.TRAINING_NAME;
					this.lastName = path[1];
					this.training = this.school.getTraining(path[1]);
					this.type = Type.TRAINING;
					if (path.length > 2) {
						this.type = Type.LESSON_NAME;
						this.lastName = path[2];
						this.lesson = this.training.getLesson(path[2]);
						this.training = this.school.getTraining(path[1]);
						this.type = Type.LESSON;
					}
				}
			} catch (Exception e) {
			}
		}
	}

	public School getSchool() {
		return school;
	}

	public Training getTraining() {
		return training;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public String getLastName() {
		return lastName;
	}

	public Type getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lesson == null) ? 0 : lesson.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		result = prime * result
				+ ((training == null) ? 0 : training.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchoolObject other = (SchoolObject) obj;
		if (lesson == null) {
			if (other.lesson != null)
				return false;
		} else if (!lesson.equals(other.lesson))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		if (training == null) {
			if (other.training != null)
				return false;
		} else if (!training.equals(other.training))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
