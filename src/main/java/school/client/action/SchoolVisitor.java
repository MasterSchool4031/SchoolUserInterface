package school.client.action;

import school.client.commons.ActionCommand;
import school.client.commons.ActionModelEvent;
import school.client.commons.ActionModelListener;
import school.client.gui.SchoolObject;
import school.client.gui.SchoolPresentationModel;

public class SchoolVisitor implements ActionModelListener<ActionCommand> {
	public SchoolVisitor() {
		super();
	}

	@Override
	public void actionModelPerformed(ActionModelEvent<ActionCommand> event) {
		SchoolPresentationModel source;
		if (event != null
				&& event.getSource() instanceof SchoolPresentationModel
				&& event.getCommand() == ActionCommand.SELECTED_OBJECT_CHANGE) {
			source = (SchoolPresentationModel) event.getSource();
			source.setSelectedObject(new SchoolObject((String[]) event
					.getParam()));
		}

	}
}
