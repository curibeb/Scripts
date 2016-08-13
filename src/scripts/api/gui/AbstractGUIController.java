package scripts.api.gui;

import javafx.fxml.Initializable;

public abstract class AbstractGUIController implements Initializable {

	private GUI gui = null;

	public void setGUI(GUI gui) {
		this.gui = gui;
	}

	public GUI getGUI() {
		return this.gui;
	}

}