package school.client.commons;

import java.util.Enumeration;

/**
 * une Ecole est définie par un nom et un ensemble de formations.
 * 
 * <pre>
 * Propriétés
 * - nom :String le nom de l'école
 * - formations : Formation[*] l'ensemble des formations dispensés par l'école
 *   
 * Contraintes sur les Propriétés
 * - nom ne peut pas être modifié après sa déclaration
 * - nom != null
 * - nom != &quot;&quot;
 * - formations[i].nom = formations[k].nom &lt;=&gt; i = k, avec 0 &lt;= i,k &lt; formations.lenght
 * </pre>
 * 
 * @author Yannick Boogaerts pour STE-Formations
 * @see Training.commun.Formation
 */
public interface School {
	/**
	 * Renvoit le nom de l'école.
	 * 
	 * @return la valeur de la propriété nom
	 */
	public String getName();

	/**
	 * Renvoit la formation dont le nom est égal au paramètre.
	 * 
	 * @param nom
	 *            le nom de la formation à récupérer.
	 * 
	 * @return une formation avec formation.nom = nom.
	 * @throws TrainingException
	 *             si nom ne correspond à aucune formation.
	 */
	public Training getTraining(String trainingName) throws TrainingException;

	/**
	 * Renvoit une Enumeration des noms des formations de l'école.
	 * 
	 * @return une Enumeration des noms des formations de l'école
	 */
	public Enumeration<String> getTrainingNameEnum();
}
