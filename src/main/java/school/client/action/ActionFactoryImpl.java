package school.client.action;

import school.client.commons.ActionCommand;
import school.client.commons.ActionFactory;
import school.client.commons.ActionModelListener;

public class ActionFactoryImpl implements ActionFactory {

	@Override
	public ActionModelListener<ActionCommand> getAddLessonAction() {
		return new AddLessonAction();
	}

	@Override
	public ActionModelListener<ActionCommand> getSchoolVisitor() {
		return new SchoolVisitor();
	}

	@Override
	public ActionModelListener<ActionCommand> getRemoveLessonAction() {
		return new RemoveLessonAction();
	}

	@Override
	public ActionModelListener<ActionCommand> getLoginAction() {
		return new LoginAction();
	}

}
