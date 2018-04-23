package school.client.action;

import javax.security.auth.callback.NameCallback;

import school.client.commons.ActionCommand;
import school.client.commons.ActionModelEvent;
import school.client.commons.ActionModelListener;
import school.client.gui.LoginDialog;
import school.client.gui.SchoolPresentationModel;

public class LoginAction implements ActionModelListener<ActionCommand> {

  @Override
  public void actionModelPerformed(ActionModelEvent<ActionCommand> event) {
    SchoolPresentationModel source;
    if (event != null && event.getSource() instanceof SchoolPresentationModel) {
      source = (SchoolPresentationModel) event.getSource();
      if (event.getCommand() == ActionCommand.LOGIN) {
        NameCallback nc = new NameCallback("Nom d'utilisateur");
        // PasswordCallback pc = new PasswordCallback("mot de passe",
        // false);
        if (LoginDialog.showLogin(null, nc, null)) {
          // pc.clearPassword();
          source.setlogin(true, nc.getName());
          source.setSchool(source.getSchool());
        }
      } else if (event.getCommand() == ActionCommand.LOGOUT) {
        source.setlogin(false, "Anonyme");
        source.setSchool(source.getSchool());
      }
    }
  }

}
