package school.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import school.client.commons.ActionCommand;
import school.client.commons.ActionModelListener;
import school.client.commons.Factory;
import school.client.commons.School;

public class SchoolPresentationModel implements TreeSelectionListener,
		ActionListener {
	private boolean login = false;
	private String userName = "Anonyme";
	private SchoolTreeModel schoolTreeModel = new SchoolTreeModel(Factory
			.getInstance().getSchool("null"));
	private SchoolObject selectedObject;
	private PropertyChangeSupport support = new PropertyChangeSupport(this);
	private ActionModelSupport<ActionCommand> actionSupport = new ActionModelSupport<ActionCommand>(
			this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
		listener.propertyChange(new PropertyChangeEvent(this, "login",
				this.login, this.login));
		listener.propertyChange(new PropertyChangeEvent(this, "userName",
				this.userName, this.userName));
		listener.propertyChange(new PropertyChangeEvent(this,
				"schoolTreeModel", this.schoolTreeModel, this.schoolTreeModel));
		listener.propertyChange(new PropertyChangeEvent(this, "selectedObject",
				this.selectedObject, this.selectedObject));
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		support.addPropertyChangeListener(propertyName, listener);
		if ("login".equalsIgnoreCase(propertyName))
			listener.propertyChange(new PropertyChangeEvent(this, "login",
					this.login, this.login));
		else if ("userName".equalsIgnoreCase(propertyName))
			listener.propertyChange(new PropertyChangeEvent(this, "userName",
					this.userName, this.userName));
		else if ("schoolTreeModel".equalsIgnoreCase(propertyName))
			listener.propertyChange(new PropertyChangeEvent(this,
					"schoolTreeModel", this.schoolTreeModel,
					this.schoolTreeModel));
		else if ("selectedObject".equalsIgnoreCase(propertyName))
			listener.propertyChange(new PropertyChangeEvent(this, "user",
					this.selectedObject, this.selectedObject));
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		support.removePropertyChangeListener(propertyName, listener);
	}

	public void addActionModelListener(ActionModelListener<ActionCommand> listener) {
		actionSupport.addActionModelListener(listener);
	}

	public void addActionModelListener(ActionCommand action,
			ActionModelListener<ActionCommand> listener) {
		actionSupport.addActionModelListener(action, listener);
	}

	public void removeActionModelListener(ActionModelListener<ActionCommand> listener) {
		actionSupport.removeActionModelListener(listener);
	}

	public void removeActionModelListener(ActionCommand action,
			ActionModelListener<ActionCommand> listener) {
		actionSupport.removeActionModelListener(action, listener);
	}

	public boolean isLog() {
		return login;
	}

	public void setlogin(boolean log, String userName) {
		boolean oldlogin = this.login;
		String oldUserName = this.userName;
		this.login = log;
		this.userName = userName;
		this.support.firePropertyChange("login", oldlogin, this.login);
		this.support.firePropertyChange("userName", oldUserName, this.userName);
	}

	public School getSchool() {
		return schoolTreeModel.getSchool();
	}

	public void setSchool(School school) {
		SchoolTreeModel old = this.schoolTreeModel;
		this.schoolTreeModel = new SchoolTreeModel(school);
		this.support.firePropertyChange("schoolTreeModel", old, this.schoolTreeModel);
	}

	public SchoolObject getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(SchoolObject selectedObject) {
		SchoolObject old = this.selectedObject;
		this.selectedObject = selectedObject;
		this.support.firePropertyChange("selectedObject", old,
				this.selectedObject);
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		Object[] path = e.getPath().getPath();
		String param[] = new String[path.length];
		for (int i = 0; i < path.length; i++) {
			param[i] = path[i].toString();
		}
		this.actionSupport.fireActionModel(ActionCommand.SELECTED_OBJECT_CHANGE,
				param);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e != null) {
			if ("add".equals(e.getActionCommand())) {
				this.actionSupport.fireActionModel(ActionCommand.ADD_LESSON, null);
			} else if ("remove".equals(e.getActionCommand())) {
				this.actionSupport.fireActionModel(ActionCommand.REMOVE_LESSON, null);
			} else if ("login".equals(e.getActionCommand())) {
				this.actionSupport.fireActionModel(ActionCommand.LOGIN, null);
			} else if ("logout".equals(e.getActionCommand())) {
				this.actionSupport.fireActionModel(ActionCommand.LOGOUT, null);
			}
		}
	}

}
