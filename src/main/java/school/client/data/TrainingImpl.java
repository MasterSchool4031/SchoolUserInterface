package school.client.data;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Enumeration;
import java.util.Hashtable;

import school.client.commons.Lesson;
import school.client.commons.Training;
import school.client.commons.TrainingException;
import school.curriculum.Curriculum;

/**
 * TODO revoir la javadoc TrainingImpl est une Formation dont les cours sont des
 * {@link Lesson}.
 * 
 * <pre>
 * Les propriétés sont surchargées par :
 * - Reference : {@link Lesson}[*] // Ensemble des dossiers de cours de référence.
 * </pre>
 * 
 * @author Yannick Boogaerts pour STE-Formations<br>
 */
public class TrainingImpl implements Training {

	/**
	 * Ensemble des noms de dossier de cours de la formation.
	 */
	private Hashtable<String, Lesson> curriculum = new Hashtable<String, Lesson>();

	/**
	 * Nom de la formation.
	 */
	private String name;

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	private String schoolName;

	/**
	 * Instancie une nouvelle {@link Training} en l'initialisant ses propriétés
	 * passées en paramètres.
	 * 
	 * @param name
	 *            nom de la foramtion
	 * @param curriculum
	 *            ensemble des noms de cours du curriculum
	 * @throws TrainingException
	 *             si le nom de la formation ou un élément du paramètre
	 *             curriculum est null ou est composé uniquement de caractères
	 *             d'espacement.
	 */
	public TrainingImpl(String schoolName, String name, Curriculum curriculum)
			throws TrainingException {
		this.valideName(name);
		this.schoolName = schoolName;
		this.name = name;

		for (String lessonName : curriculum) {
			this.addLesson(lessonName);
		}
	}

	/**
	 * Renvoit le nom de la formation.
	 * 
	 * @return le nom de la formation.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Renvoit une énumération des noms de cours du curriculum
	 * 
	 * @return une énumération des noms de cours du curriculum
	 */
	@Override
	public Enumeration<String> getLessonNameEnum() {
		return curriculum.keys();
	}

	/**
	 * Renvoit le dossier de cours correspondant au nom du cours. Si aucun
	 * dossier de cours au nom du cours n'est présent dans la référence un
	 * nouveau cours avec un dossier erreur est renvoyé.
	 * 
	 * @param lessonName
	 *            nom du cours à renvoyer
	 * @return dossier de cours correspondant au nom
	 * @throws TrainingException
	 *             si le nom du cours n'est pas présent dans le curriculum.
	 * @throws ClassCastException
	 *             si la valeur de reference correspondant au npm n'est pas un
	 *             DosCours
	 * @see Training#getLesson(String)
	 */
	public Lesson getLesson(String lessonName) throws TrainingException {
		String key = lessonName.toUpperCase().trim();
		Lesson cours = this.curriculum.get(key);
		if (cours == null)
			throw new TrainingException("cours absent du curriculum");
		return cours;
	}

	public Lesson addLesson(Training.Callback callback)
			throws TrainingException {
		return this.addLesson(callback.getName());
	}

	private Lesson addLesson(String name) throws TrainingException {
		String key;
		Lesson cours;

		this.valideName(name);
		key = name.toUpperCase().trim();

		if (this.curriculum.containsKey(key)) {
			throw new TrainingException(name
					+ " fait déjà partie de la formation");
		} else {
			cours = DataFactoryImpl.getInstance().getNewLesson(name);
			this.curriculum.put(key, cours);

			this.propertyChangeSupport.firePropertyChange("addLesson", null,
					cours);

			return cours;
		}
	}

	public Lesson removeLesson(String name) {
		String key = name.toUpperCase().trim();
		Lesson cours = this.curriculum.remove(key);

		this.propertyChangeSupport.firePropertyChange("removeLesson", cours,
				null);

		return cours;
	}

	private void valideName(String name) throws TrainingException {
		if (name == null || name.matches("\\s*"))
			throw new TrainingException("nom incorrect");
	}

	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((curriculum == null) ? 0 : curriculum.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TrainingImpl other = (TrainingImpl) obj;
		if (curriculum == null) {
			if (other.curriculum != null)
				return false;
		} else if (!curriculum.equals(other.curriculum))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(propertyName,
				listener);
	}

}
