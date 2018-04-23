package school.client.gui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class SecureRenderer extends DefaultTreeCellRenderer {
	ImageIcon secureIcon = createImageIcon("cadenas.jpeg");

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded,
				leaf, row, hasFocus);

		if (leaf) {
			if ((value instanceof String)) {
				setIcon(secureIcon);
			}
		}

		return this;
	}

	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = this.getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
