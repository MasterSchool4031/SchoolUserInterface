package school.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import school.client.commons.Training;

/**
 * Panel d'affichage de l'arborescence Ecole-Formation_cours et du countenu des
 * cours. Le panel contient deux boutons pour géré l'ajout et la suppression de
 * cours.
 * 
 * <pre>
 * Structure du component :
 * PanelDossier
 *   +-- JSplitPane
 *     +-- JPanel (gauche)
 *     :  +-- JScrollPane (Haut)
 *     :  :  +-- JTree
 *     :  +-- JButton &quot;Ajouter&quot;  (bas gauche)
 *     :  +-- JButton &quot;Supprimer (bas droite)
 *     +-- JScrollPane (droite)
 *        +-- jEditorPane
 * </pre>
 * 
 * @author Yannick Boogaerts pour STE-Formations<br>
 */
public class MainWindow extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = -4778723881440423905L;
	private JSplitPane jSplitPane = null;
	private JScrollPane jScrollPaneCours = null;
	private JEditorPane jEditorPane = null;
	private JPanel jPanelListe = null;
	private JScrollPane jScrollPane = null;
	private JTree jTree = null;
	private JButton jButtonadd = null;
	private JButton jButtonSupp = null;
	private JPanel jPanelLogin = null;
	private JButton jButtonLogin = null;
	private JLabel jLabelUser = null;

	/**
	 * This is the default constructor
	 */
	public MainWindow() {
		super();
		initialize();
	}

	public void addJButtonAddListener(ActionListener listener) {
		this.getJButtonadd().addActionListener(listener);
	}

	public void removeJButtonAddListener(ActionListener listener) {
		this.getJButtonadd().removeActionListener(listener);
	}

	public void addJButtonRemoveListener(ActionListener listener) {
		this.getJButtonSupp().addActionListener(listener);
	}

	public void removeJButtonRemoveListener(ActionListener listener) {
		this.getJButtonSupp().removeActionListener(listener);
	}

	public void addJButtonLoginListener(ActionListener listener) {
		this.getJButtonLogin().addActionListener(listener);
	}

	public void removeJButtonLoginListener(ActionListener listener) {
		this.getJButtonLogin().removeActionListener(listener);
	}

	public void addTreeSelectionListener(TreeSelectionListener listener) {
		this.getJTree().addTreeSelectionListener(listener);
	}

	public void removeTreeSelectionListener(TreeSelectionListener listener) {
		this.getJTree().removeTreeSelectionListener(listener);
	}

	public void setTreeModel(TreeModel model) {
		this.getJTree().setModel(model);
	}

	public void updateText(String text, String contentType) {
		this.getJEditorPane().setContentType(contentType);
		this.getJEditorPane().setText(text);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLayout(new BorderLayout());
		this.setSize(461, 334);
		this.setContentPane(getJSplitPane());
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes jSplitPane
	 * 
	 * @return javax.swing.JSplitPane
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setRightComponent(getJScrollPaneCours());
			jSplitPane.setLeftComponent(getJPanelListe());
			// jSplitPane.setOpaque(true);
		}
		return jSplitPane;
	}

	/**
	 * This method initializes jScrollPaneCours
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneCours() {
		if (jScrollPaneCours == null) {
			jScrollPaneCours = new JScrollPane();
			jScrollPaneCours.setViewportView(getJEditorPane());
		}
		return jScrollPaneCours;
	}

	/**
	 * This method initializes jEditorPane
	 * 
	 * @return javax.swing.JEditorPane
	 */
	private JEditorPane getJEditorPane() {
		if (jEditorPane == null) {
			jEditorPane = new JEditorPane();
		}
		return jEditorPane;
	}

	/**
	 * This method initializes jPanelListe
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelListe() {
		if (jPanelListe == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridwidth = 2;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.gridy = 3;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints11.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.gridwidth = 2;
			gridBagConstraints.weighty = 1.0;
			jPanelListe = new JPanel();
			jPanelListe.setLayout(new GridBagLayout());
			jPanelListe.add(getJScrollPane(), gridBagConstraints);
			jPanelListe.add(getJButtonadd(), gridBagConstraints1);
			jPanelListe.add(getJButtonSupp(), gridBagConstraints11);
			jPanelListe.add(getJPanelLogin(), gridBagConstraints4);
		}
		return jPanelListe;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane(getJTree());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTree
	 * 
	 * @return javax.swing.JTree
	 */
	private JTree getJTree() {
		if (jTree == null) {
			jTree = new JTree();
			jTree.setCellRenderer(new SecureRenderer());
		}
		return jTree;
	}

	/**
	 * This method initializes jButtonadd
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonadd() {
		if (jButtonadd == null) {
			jButtonadd = new JButton();
			jButtonadd.setText("Ajouter");
			jButtonadd.setActionCommand("add");
		}
		return jButtonadd;
	}

	/**
	 * This method initializes jButtonSupp
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonSupp() {
		if (jButtonSupp == null) {
			jButtonSupp = new JButton();
			jButtonSupp.setText("Supprimer");
			jButtonSupp.setActionCommand("remove");
		}
		return jButtonSupp;
	}

	public DefaultMutableTreeNode getNodeActiveTraining() {
		DefaultMutableTreeNode noeud;

		TreePath parentPath = jTree.getSelectionPath();

		if (parentPath == null) {
			noeud = null;
		} else {
			noeud = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
			while (noeud != null
					&& !(noeud.getUserObject() instanceof Training)) {
				noeud = (DefaultMutableTreeNode) noeud.getParent();
			}
		}

		return noeud;
	}

	public DefaultTreeModel getTreeModel() {
		return (DefaultTreeModel) this.getJTree().getModel();

	}

	public void setSchoolTreeModel(SchoolTreeModel schoolTreeModel) {
		String title = schoolTreeModel.getSchool().getName();
		title += System.getSecurityManager() != null ? " sécurisée" : "";
		this.setTitle(title);
		this.getJTree().setModel(schoolTreeModel);
		this.getJTree().setSelectionRow(0);
		this.getJTree().setSelectionModel(
				schoolTreeModel.getTreeSelectionModel());
	}

	public void updateText(URL support, String contentType) throws IOException {
		this.getJEditorPane().setContentType(contentType);
		this.getJEditorPane().setPage(support);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt != null && evt.getSource() instanceof SchoolPresentationModel) {

			if ("login".equals(evt.getPropertyName())) {
				this.updateLog((Boolean) evt.getNewValue());
			} else if ("userName".equals(evt.getPropertyName())) {
				this.getJLabelUser().setText((String) evt.getNewValue());
			} else if ("schoolTreeModel".equals(evt.getPropertyName())) {
				this.setSchoolTreeModel((SchoolTreeModel) evt.getNewValue());
			} else if ("selectedObject".equals(evt.getPropertyName())) {
				this.updateSelectedObject((SchoolObject) evt.getNewValue());
			}
		}
	}

	private void updateSelectedObject(SchoolObject newValue) {
		if (newValue == null) {
			this.updateText("", "text/txt");
		} else if (newValue.getType() == school.client.gui.SchoolObject.Type.LESSON) {
			try {
				this.updateText(newValue.getLesson().getContent(), "text/html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.updateText(newValue.getLastName(), "text/txt");
		}
	}

	private void updateLog(Boolean login) {
		if (login) {
			this.getJButtonLogin().setActionCommand("logout");
			this.getJButtonLogin().setText("logout");
		} else {
			this.getJButtonLogin().setActionCommand("login");
			this.getJButtonLogin().setText("login");
		}

	}

	/**
	 * This method initializes jPanelLogin
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelLogin() {
		if (jPanelLogin == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.anchor = GridBagConstraints.EAST;
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.ipadx = 10;
			gridBagConstraints3.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints3.gridy = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = -1;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.fill = GridBagConstraints.NONE;
			gridBagConstraints2.gridy = -1;
			jPanelLogin = new JPanel();
			jPanelLogin.setLayout(new GridBagLayout());
			jPanelLogin.setBorder(BorderFactory.createTitledBorder(null,
					"Utilisateur", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelLogin.add(getJButtonLogin(), gridBagConstraints2);
			jPanelLogin.add(getJLabelUser(), gridBagConstraints3);
		}
		return jPanelLogin;
	}

	private JLabel getJLabelUser() {
		if (this.jLabelUser == null) {
			jLabelUser = new JLabel();
			jLabelUser.setText("Anonyme");

		}
		return jLabelUser;
	}

	/**
	 * This method initializes jButtonLogin
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonLogin() {
		if (jButtonLogin == null) {
			jButtonLogin = new JButton();
			jButtonLogin.setActionCommand("login");
			jButtonLogin.setText("Login");
		}
		return jButtonLogin;
	}
}
