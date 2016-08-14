package scripts.advancedcutter.api.gui;

import java.net.URL;

import javax.swing.SwingUtilities;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUI extends Application {

	private String guiTitle;
	private Stage stage;
	private Scene scene;
	private final URL fxml;
	private boolean isOpen = false;

	public GUI(URL fxml, String guiTitle) {
		this.guiTitle = guiTitle;
		this.fxml = fxml;
		SwingUtilities.invokeLater(() -> {
			new JFXPanel();
			Platform.runLater(() -> {
				try {
					start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		});
		waitForInit();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			this.stage = primaryStage;
			stage.setTitle(this.guiTitle);
			stage.setAlwaysOnTop(true);
			stage.setResizable(false);
			stage.getIcons().add(new Image("http://www.freeiconspng.com/uploads/tree-icon-0.png"));
			Platform.setImplicitExit(false);

			FXMLLoader loader = new FXMLLoader(fxml);

			loader.setClassLoader(this.getClass().getClassLoader());

			Parent root = loader.load();

			AbstractGUIController controller = loader.getController();

			controller.setGUI(this);

			scene = new Scene(root, 720, 395);
			stage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void show() {

		if (stage == null)
			return;

		isOpen = true;

		Platform.runLater(() -> stage.show());
	}

	public void close() {

		if (stage == null)
			return;

		isOpen = false;

		Platform.runLater(() -> stage.close());
	}

	public boolean isOpen() {
		return isOpen;
	}

	private void waitForInit() {
		Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(250);
				return stage != null;
			}
		}, 5000);
	}

}
