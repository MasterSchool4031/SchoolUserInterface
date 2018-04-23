package school.client.commons;

/**
 * Fabrique abstraite donnant accès aux Implémentations concrètes des
 * {@link ActionModelListener}
 * 
 * @author boogaerts
 * 
 */
public interface ActionFactory {

	/**
	 * Retourne le contrôleur gérant l’ajout d’un cours.
	 * 
	 * @return le {@link ActionModelListener} gérant l’ajout d’un cours
	 */
	ActionModelListener<ActionCommand> getAddLessonAction();

	/**
	 * Retourne le contrôleur gérant le changement d’éléments ( cours
	 * {@link Lesson}, formations {@link Training}, école {@link School}) actif.
	 * 
	 * @return le {@link ActionModelListener} gérant le changement d’éléments
	 *         actifs
	 */
	ActionModelListener<ActionCommand> getSchoolVisitor();

	/**
	 * Retourne le contrôleur gérant la suppression d’un cours d’une formation.
	 * 
	 * @return le {@link ActionModelListener} gérant la suppression d’un cours
	 */
	ActionModelListener<ActionCommand> getRemoveLessonAction();

	/**
	 * Retourne le contrôleur gérant l’ouverture et la fermeture de session par
	 * un utilisateur.
	 * 
	 * @return le {@link ActionModelListener} gérant l’ouverture et la fermeture
	 *         de session.
	 */
	ActionModelListener<ActionCommand> getLoginAction();

}
