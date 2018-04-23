package school.client.commons;

import java.beans.PropertyChangeListener;
import java.util.Enumeration;

/**
 * Formation définie par un nom et un ensemble de cours.
 * 
 * <pre>
 *   Propriétés
 *   - nom : String //le nom de la formation.
 *   - curriculum : Cours[*] // ensemble des cours de la formation.
 *       
 *   Contraintes sur les Propriétés
 *   - nom != null
 *   - nom != &quot;&quot;
 *   - curriculum[i].nom = curriculum[j].nom &lt;=&gt; i = j, avec 0 &lt;= i,j &lt; curriculum.lenght
 * </pre>
 * 
 * @author Yannick Boogaerts pour STE-Formations <br>
 * @see Lesson.commun.Cours
 */
public interface Training {
	/**
	 * renvoit le nom de la formation.
	 * 
	 * @return le nom de la formation.
	 */
	public String getName();

	/**
	 * Renvoit une Enumeration des noms de cours prèsents dans le curriculum de
	 * la formations.
	 * 
	 * @return une Enumeration de noms de cours. Enumeration[i]:String.
	 */
	public Enumeration<String> getLessonNameEnum();

	/**
	 * Renvoit la référence du cours dont la propriété nom est égals au
	 * paramètre. Soulève une FormationException s'il n'y a pas de cours
	 * correspondant au paramètre.
	 * 
	 * @param lessonName
	 *            le nom du cours demandé.
	 * @return la référence du cours dont
	 *         curriculum[i].intiutlé.equals(intitule)
	 * @throws TrainingException
	 *             s'il n'existe pas de curriculum[i].intiutlé.equals(intitule)
	 */
	public Lesson getLesson(String lessonName) throws TrainingException;

	/**
	 * Crée et ajoute un cours à la formation. Le cours est créé à partir du nom
	 * retourné par la méthode getName() du Callback passé en paramètre. Cette
	 * technique d’inversion de contrôle permet de ne demander le nom du cours à
	 * l’utilisateur que si la formation accepte l’ajout d’un cours. La méthode
	 * soulève une TrainingException si le nom du cours n’est pas valide ou
	 * qu’il est déjà présent dans cette formation.
	 * 
	 * @param callback
	 *            le Callback permettant de récupérer le nom du cours à ajouter.
	 * @return le cours ajouté dans le curriculum.
	 * @throws TrainingException
	 *             si le nom n'est pas valide ou que le nom du cours est déjà
	 *             présent dans la formation
	 */
	public Lesson addLesson(Training.Callback callback)
			throws TrainingException;

	/**
	 * Retire le cours dont le nom est égal au paramètre du curriculum de la
	 * formation. Pour que le cours soit retirer il faut qu'il exsite un cours
	 * dont le nom est égal au paramètre.
	 * 
	 * @param name
	 *            le nom du cours à retirer
	 * @return le cours retirer ou null si le cours ne fait pas partie du
	 *         curriculum.
	 * @throws TrainingException
	 */
	public Lesson removeLesson(String name) throws TrainingException;

	public void addPropertyChangeListener(PropertyChangeListener listener);

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

	public void removePropertyChangeListener(PropertyChangeListener listener);

	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

	/**
	 * interface des classes permettant à la méthode addLesson() d’une formation
	 * de récupérer le nom du cours à ajouter.
	 * 
	 * @author boogaerts
	 * 
	 */
	public interface Callback {
		/**
		 * Retourne le nom du Callback
		 * 
		 * @return un nom.
		 */
		public String getName();
	}

}
