package scripts.advancedcutter;

import java.net.MalformedURLException;
import java.net.URL;

import org.tribot.script.Script;
import org.tribot.script.interfaces.Ending;

import scripts.advancedcutter.api.gui.GUI;
import scripts.advancedcutter.api.utilities.Vars;

public class Main extends Script implements Ending{
	public static GUI gui = null;

	public GUI getGUI() {
		try {
			return new GUI(new URL("https://raw.githubusercontent.com/CSharp2Bot/Scripts/master/src/scripts/advancedcutter/gui/Main.fxml"), "C#2Bot Cutter");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void run() {
		gui = getGUI();
		if (gui != null) {
			gui.show();
			while (gui.isOpen())
				sleep(250);
		}
		while (Vars.start) {
			sleep(500, 800);
		}
	}

	@Override
	public void onEnd() {
		if (gui.isOpen()){
			gui.close();
		}
		System.out.print("Thank you for using C#2Bot Cutter");
	}

}
