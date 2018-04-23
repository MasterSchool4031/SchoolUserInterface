package school.client.data;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import school.client.commons.School;
import school.client.commons.Training;
import school.client.commons.TrainingException;

/**
 * Ecole gérant l'accès à des dossiers de cours.
 * 
 * <pre>
 * Propriétés surchargées :
 * - Formations : DosFormation[*]
 * </pre>
 * 
 * @author Yannick Boogaerts pour STE-Formations <br>
 * @see School
 * @see Training
 */
public class SchoolImpl implements School {

  /**
   * Le nom de l'école
   */
  private String name;

  /**
   * ensemble des formations de l'école.
   */
  private Hashtable<String, Training> trainingMap;

  /**
   * Construit une école.
   * 
   * @param schoolName
   *          nom de l'école
   * @param trainingList
   *          la liste des formations de l'école
   */
  public SchoolImpl(String schoolName, List<Training> trainingList) {
    super();
    this.trainingMap = new Hashtable<String, Training>();
    if (trainingMap != null) {
      for (Training t : trainingList) {
        this.trainingMap.put(t.getName(), t);
      }
    }
    this.name = schoolName;
  }

  @Override
  public Training getTraining(String trainingName) throws TrainingException {
    return this.trainingMap.get(trainingName);
  }

  @Override
  public Enumeration<String> getTrainingNameEnum() {
    return this.trainingMap.keys();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    return this.getName();
  }

}
