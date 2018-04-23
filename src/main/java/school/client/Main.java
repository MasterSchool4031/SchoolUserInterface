package school.client;

import school.client.commons.Factory;
import school.client.gui.MainWindow;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final MainWindow window;
//		String schoolName = "STE-Formations";

		Factory factory = Factory.getInstance();
		window = factory.getMainWindow();
//		window.setTitle(System.getSecurityManager() == null ? schoolName
//				: schoolName + " Sécurisée");

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI(window);
			}
		});
	}

	static void createAndShowGUI(MainWindow window) {
		window.setVisible(true);

	}
}
