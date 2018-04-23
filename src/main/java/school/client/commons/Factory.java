package school.client.commons;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import school.client.action.ActionFactoryImpl;
import school.client.data.DataFactoryImpl;
import school.client.gui.MainWindow;
import school.client.gui.SchoolPresentationModel;
import school.curriculum.Curriculum;
import school.curriculum.CurriculumFactory;
import school.curriculum.CurriculumFactoryImpl;
import school.net.NetFactory;
import school.net.NetFactoryImpl;

/**
 * Fabrique générale gérant la création et l’initialisation des instances de
 * l’application.
 * 
 * @author boogaerts
 * 
 */
public class Factory {

	private static Factory instance;
	/**
	 * Définit le protocole utilisé pour accéder au dossier de cours. Dans la
	 * version prototype de l’application elle est initialisé avec une instance
	 * de {@link NetFactoryImpl}.
	 */
	private final NetFactory supportFactory;
	/**
	 * Définit l’implémentation des modèles métiers utilisés par l’application.
	 * Dans la version prototype elle est initialisée avec une instance de
	 * {@link DataFactoryImpl}.
	 */
	private final DataFactory dataFactory;
	/**
	 * Définit les contrôleurs des actions de l’utilisateur. Dans la version
	 * prototype elle est initialisé avec une {@link ActionFactoryImpl}.
	 */
	private final ActionFactory userFactory;
	/**
	 * Définit l’ensemble des partenaires fournisseurs de curriculums présents
	 * dans l’application. Dans la version prototype une seule CurriculumFactory
	 * est présente dans la collection : {@link CurriculumFactoryImpl}.
	 */
	private Map<String, CurriculumFactory> contentsfactoryMap;
	private MainWindow mainWindow;
	private SchoolPresentationModel schoolPresentationModel;

	private Factory() {
		super();
		this.dataFactory = DataFactoryImpl.getInstance();
		this.userFactory = new ActionFactoryImpl();
		this.supportFactory = new NetFactoryImpl();
		this.contentsfactoryMap = getCurriculumFactoryMap();
	}

	private Map<String, CurriculumFactory> getCurriculumFactoryMap() {
		if (this.contentsfactoryMap == null) {
			this.contentsfactoryMap = new HashMap<String, CurriculumFactory>();
			CurriculumFactory curriculumFactory = new CurriculumFactoryImpl();
			this.contentsfactoryMap.put(curriculumFactory.getSchoolName(),
					curriculumFactory);
		}
		return this.contentsfactoryMap;
	}

	public static Factory getInstance() {
		if (Factory.instance == null) {
			Factory.instance = new Factory();
		}
		return Factory.instance;
	}

	/**
	 * Retourne l’instance de l’école initialisée avec les formations et les
	 * cours correspondants aux curriculums fournis par la CurriculumFactory
	 * dont la propriété SchoolName correspond à la valeur du paramètre.
	 * 
	 * @param schoolName
	 *            le nom de l'école.
	 * @return l'école.
	 */
	public School getSchool(String schoolName) {
		School school;
		CurriculumFactory curriculumFactory = this.getCurriculumFactoryMap()
				.get(schoolName);
		if (curriculumFactory != null) {
			school = this.dataFactory.getSchool(schoolName, curriculumFactory
					.getCurriculumMap());
		} else {
			school = this.dataFactory.getSchool(schoolName,
					new HashMap<String, Curriculum>());

		}
		return school;
	}

	/**
	 * Retourne la référence de la vue principale de l’application. Cette vue
	 * est initialisée avec comme écouteur des différents événements
	 * utilisateurs l’instance du {@link SchoolPresentationModel} retourné par
	 * la méthode {@link #getSchoolPresentationModel()}.
	 * 
	 * @return la vue principale de l’application.
	 */
	public MainWindow getMainWindow() {
		if (mainWindow == null) {
			mainWindow = new MainWindow();
			this.mainWindow
					.addTreeSelectionListener(getSchoolPresentationModel());
			this.mainWindow.addJButtonAddListener(getSchoolPresentationModel());
			this.mainWindow
					.addJButtonRemoveListener(getSchoolPresentationModel());
			this.mainWindow
					.addJButtonLoginListener(getSchoolPresentationModel());
		}
		return mainWindow;
	}

	/**
	 * Retourne le persentationModel des vues de l’application. Il a comme rôles
	 * de :
	 * <ul>
	 * <li>Représenter l’état du dialogue avec l’utilisateur. Toute modification
	 * de cet état est automatiquement répercuté sur les éléments visuelles de
	 * l’application.</li>
	 * <li>Abstraire les événements liés aux composants visuels pour en faire
	 * des événements métiers. Les événements envoyés par les composants visuels
	 * sont reçu par le « PresentationModel » et transformé en événement modèle
	 * ActionModelEvent et ré envoyé aux écouteurs de celui-ci.</li>
	 * </ul>
	 * L’instance retourné par la méthode est initialisée avec comme écouteur de
	 * ses propriétés l’instance renvoyée par getSchoolMainWindow() et comme
	 * écouteurs des événements métiers les différents contrôleurs définit par
	 * la ActionFactory.
	 * 
	 * @return le SchoolPresentationModel.
	 */
	public SchoolPresentationModel getSchoolPresentationModel() {
		if (this.schoolPresentationModel == null) {
			this.schoolPresentationModel = new SchoolPresentationModel();
			this.schoolPresentationModel.addPropertyChangeListener(this
					.getMainWindow());
			this.schoolPresentationModel.setSchool(this
					.getSchool("STE-Formations"));
			// TODO refactoring mise en place du curriculum
			this.schoolPresentationModel.addActionModelListener(
					ActionCommand.SELECTED_OBJECT_CHANGE, this
							.getSchoolVisitor());
			this.schoolPresentationModel.addActionModelListener(
					ActionCommand.ADD_LESSON, this.getAddLessonAction());
			this.schoolPresentationModel.addActionModelListener(
					ActionCommand.REMOVE_LESSON, this.getRemoveLessonAction());
			this.schoolPresentationModel.addActionModelListener(
					ActionCommand.LOGIN, this.getLoginAction());
			this.schoolPresentationModel.addActionModelListener(
					ActionCommand.LOGOUT, this.getLoginAction());
		}
		return this.schoolPresentationModel;
	}

	private ActionModelListener<ActionCommand> getSchoolVisitor() {
		return this.userFactory.getSchoolVisitor();
	}

	private ActionModelListener<ActionCommand> getAddLessonAction() {
		return this.userFactory.getAddLessonAction();
	}

	private ActionModelListener<ActionCommand> getRemoveLessonAction() {
		return this.userFactory.getRemoveLessonAction();
	}

	private ActionModelListener<ActionCommand> getLoginAction() {
		return this.userFactory.getLoginAction();
	}

	/**
	 * Retourne une {@link URL} vers un dossier de cours en fonction du nom de
	 * cours passé en paramètre. Dans la version prototype la fonctionnalité est
	 * simplement déléguée au NetFactory.
	 * 
	 * @param lessonName
	 *            le nom du cours
	 * @return l'URL du dossier
	 * @throws MalformedURLException
	 */
	public URL getURL(String lessonName) throws MalformedURLException {
		return this.supportFactory.getURL(lessonName);
	}
}
