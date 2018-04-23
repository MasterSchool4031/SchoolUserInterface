package school.client.data;

import java.net.MalformedURLException;
import java.net.URL;

import school.client.commons.Factory;
import school.client.commons.Lesson;
import school.client.commons.TrainingException;

/**
 * TODO javadoc du dossier de cours, pas d'une lesson.
 * 
 * Un DosCours est dossier de cours dont le contenu est représenté par l'URL
 * vers un serveur <code>DossierServeur</code> distribuant des dossiers de
 * cours.
 * 
 * @author Yannick Boogaerts pour STE-Formations <br>
 */
public class LessonImpl implements Lesson {

  /**
   * Intitulé du cours
   */
  private String name;

  /**
   * Contenu du cours
   */
  private URL content;

  /**
   * Construit un cours en lui donnant un intitulé. Le contenu est représenté
   * par une URL dont :
   * <ul>
   * <li>le protocole est "book"</li>
   * <li>l'hôte est "localhost"</li>
   * <li>le port est 1443</li>
   * <li>le gestionnaire de flux est un <code>DossierURLStreamHandler</code></li>
   * </ul>
   * 
   * @param name
   *          le nom du cours
   * @throws TrainingException
   *           si le nom est null ou s'il est composé uniquement de caractères
   *           d'espacement.
   */
  public LessonImpl(String name) throws TrainingException {
    if (name == null || name.matches("\\s*"))
      throw new TrainingException("nom de cours incorrect");
    this.name = name;
    try {
      this.content = Factory.getInstance().getURL(name);

    } catch (MalformedURLException e) {
      throw new TrainingException(e);
    }
  }

  /**
   * Renvoit l'intitulé du cours
   * 
   * @see Lesson#getName()
   */
  public String getName() {
    return name;
  }

  /**
   * Renvoit le contenu du cours.
   * 
   * @see Lesson#getContent()
   */
  public URL getContent() {
    return content;
  }

  /**
   * Deux cours sont égaux si leur intitulé et leur contenu sont égaux.
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object arg0) {
    return arg0 != null && arg0 instanceof LessonImpl
        && ((LessonImpl) arg0).getName().equals(this.name)
        && ((LessonImpl) arg0).getContent().equals(this.content);
  }

  /**
   * Renvoit le code de hashage de l'objet.
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return this.name.hashCode();
  }

  /**
   * Renvois le DosCours sous forme de String
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return this.name;
  }
}
