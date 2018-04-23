package school.client.action;

import javax.swing.JOptionPane;

import school.client.commons.ActionCommand;
import school.client.commons.ActionModelEvent;
import school.client.commons.ActionModelListener;
import school.client.commons.Training.Callback;
import school.client.commons.TrainingException;
import school.client.gui.SchoolObject;
import school.client.gui.SchoolPresentationModel;

public class AddLessonAction implements ActionModelListener<ActionCommand> {

	@Override
	public void actionModelPerformed(ActionModelEvent<ActionCommand> event) {
		SchoolPresentationModel source;
		SchoolObject path;
		if (event != null
				&& event.getSource() instanceof SchoolPresentationModel
				&& event.getCommand() == ActionCommand.ADD_LESSON) {
			source = (SchoolPresentationModel) event.getSource();
			path = source.getSelectedObject();
			if(path != null && path.getTraining() != null ){
				try {
					path.getTraining().addLesson(new Callback() {

						@Override
						public String getName() {
							return JOptionPane.showInputDialog(null,
									"Nom du cours");
						}
					});
				} catch (TrainingException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
	}

}
