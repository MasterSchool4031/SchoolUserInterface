package school.client.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import school.client.commons.School;
import school.client.commons.Training;

public class SchoolTreeModel extends DefaultTreeModel {

	private School school;
	private TreeSelectionModel treeSelectionModel;

	public SchoolTreeModel(School school) {
		super(new DefaultMutableTreeNode(school.getName()));
		this.school = school;
		this.initRoot(school);
	}

	public void setSchool(School school) {
		this.school = school;
		this.initRoot(school);
	}

	private void initRoot(School school) {
		Enumeration<String> trainingEnum, lessonEnum;
		String trainingName, lessonName;
		DefaultMutableTreeNode root, noeudF, noeudC;

		root = new DefaultMutableTreeNode(school.getName());
		trainingEnum = school.getTrainingNameEnum();
		while (trainingEnum.hasMoreElements()) {
			trainingName = (String) trainingEnum.nextElement();
			try {
				noeudF = new TrainingTreeNode(school.getTraining(trainingName));
				lessonEnum = school.getTraining(trainingName).getLessonNameEnum();
				while (lessonEnum.hasMoreElements()) {
					lessonName = (String) lessonEnum.nextElement();
					try {
						noeudC = new DefaultMutableTreeNode(school.getTraining(
								trainingName).getLesson(lessonName));
					} catch (Exception e) {
						noeudC = new DefaultMutableTreeNode(lessonName);
						e.printStackTrace();
					}
					noeudF.add(noeudC);
				}
			} catch (Exception e) {
				noeudF = new DefaultMutableTreeNode(trainingName);
				e.printStackTrace();
			}
			root.add(noeudF);
			this.setRoot(root);
		}
	}

	private class TrainingTreeNode extends DefaultMutableTreeNode implements
			PropertyChangeListener {

		private static final long serialVersionUID = 1L;

		public TrainingTreeNode(Training training) {
			super(training);
			training.addPropertyChangeListener(this);
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			DefaultMutableTreeNode fils = null;
			TreePath newTreePath;
			Enumeration<?> enumeration;
			if (evt != null && this.getUserObject().equals(evt.getSource())) {
				if ("addLesson".equals(evt.getPropertyName())) {
					fils = new DefaultMutableTreeNode(evt.getNewValue());
					SchoolTreeModel.this.insertNodeInto(fils, this, this
							.getChildCount());
					newTreePath = new TreePath(SchoolTreeModel.this
							.getPathToRoot(fils));
					SchoolTreeModel.this.getTreeSelectionModel()
							.setSelectionPath(newTreePath);
				} else if ("removeLesson".equals(evt.getPropertyName())) {
					enumeration = this.children();
					while (enumeration.hasMoreElements() && fils == null) {
						fils = (DefaultMutableTreeNode) enumeration
								.nextElement();
						if (!evt.getOldValue().equals(fils.getUserObject())) {
							fils = null;
						}
					}
					if (fils != null) {
						newTreePath = new TreePath(SchoolTreeModel.this
								.getPathToRoot(fils)).getParentPath();
						SchoolTreeModel.this.removeNodeFromParent(fils);
						SchoolTreeModel.this.getTreeSelectionModel()
								.setSelectionPath(newTreePath);
					}
				}
			}
		}
	}

	public School getSchool() {
		return this.school;
	}

	public TreeSelectionModel getTreeSelectionModel() {
		if (this.treeSelectionModel == null) {
			this.treeSelectionModel = new DefaultTreeSelectionModel();
			//this.treeSelectionModel.setSelectionPath(new TreePath(new Object[]{this.root}));
		}
		return treeSelectionModel;
	}
}
