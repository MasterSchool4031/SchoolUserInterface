package school.client.commons;

import java.net.URL;

/**
 * un Cours est définit par un nom et un contenu.
 * 
 * <pre>
 *  Propriétés
 *  - nom :String // le nom du cours
 *  - contenu  :URL // le contenu du cours
 *  
 *  Contraintes sur les Propriétés
 *  Le contenu et l'intitulé ne peuvent pas être null.
 * </pre>
 * 
 * @author Yannick Boogaerts pour STE-Formations
 */
public interface Lesson {
    /**
     * Renvoit le nom du cours.
     * 
     * @return le nom du cours.
     */
    public String getName();

    /**
     * Renvoit le dossier de cours.
     * 
     * @return le dossier de cours.
     */
    public URL getContent();
}
