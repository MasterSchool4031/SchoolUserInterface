package school.client.action;

import javax.swing.JOptionPane;

import school.client.commons.ActionCommand;
import school.client.commons.ActionModelEvent;
import school.client.commons.ActionModelListener;
import school.client.commons.TrainingException;
import school.client.gui.SchoolObject;
import school.client.gui.SchoolObject.Type;
import school.client.gui.SchoolPresentationModel;

public class RemoveLessonAction implements ActionModelListener<ActionCommand> {

  @Override
  public void actionModelPerformed(ActionModelEvent<ActionCommand> event) {
    SchoolPresentationModel source;
    SchoolObject path;
    if (event != null && event.getSource() instanceof SchoolPresentationModel
        && event.getCommand() == ActionCommand.REMOVE_LESSON) {
      source = (SchoolPresentationModel) event.getSource();
      path = source.getSelectedObject();
      if (path != null
          && (path.getType() == Type.LESSON || path.getType() == Type.LESSON_NAME)) {
        try {
          path.getTraining().removeLesson(path.getLastName());
        } catch (TrainingException e) {
          JOptionPane.showMessageDialog(null, e.getMessage());
        }
      }
    }
  }
}
