package school.client.commons;

import java.util.Map;

import school.curriculum.Curriculum;

/**
 * Fabrique abstraite donnant accès aux implémentations de {@link School},
 * {@link Training} et {@link Lesson}.
 * 
 * @author boogaerts
 * 
 */
public interface DataFactory {

	/**
	 * Retourne une nouvelle école initialisée avec les formations et les cours
	 * correspondants aux curriculumMap et le nom est à la valeur du paramètre
	 * schoolName.
	 * 
	 * @param schoolName
	 *            le nom de l'école.
	 * @param curriculumMap
	 *            la définitiondes curriculum par formations.
	 * @return la nouvelle école.
	 */
	School getSchool(String schoolName, Map<String, Curriculum> curriculumMap);

}
