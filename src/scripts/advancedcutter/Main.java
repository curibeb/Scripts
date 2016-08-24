package scripts.advancedcutter;

import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Walking;
import org.tribot.script.Script;
import org.tribot.script.interfaces.Ending;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.Starting;

import com.allatori.annotations.DoNotRename;

import scripts.advancedcutter.Paint.Paint;
import scripts.advancedcutter.api.dynamicsig.SigData;
import scripts.advancedcutter.api.gui.GUI;
import scripts.advancedcutter.api.taskframework.Task;
import scripts.advancedcutter.api.taskframework.TaskSet;
import scripts.advancedcutter.api.utilities.Vars;
import scripts.advancedcutter.tasks.proglevel.ProgLevel;
import scripts.advancedcutter.tasks.woodcut.WoodCut;

@DoNotRename
public class Main extends Script implements Starting, Ending, Painting {
	public static String status = "";
	public static GUI gui = null;
	private TaskSet taskset = new TaskSet();
	private String url = "https://raw.githubusercontent.com/CSharp2Bot/Scripts/master/src/scripts/advancedcutter/gui/Main.fxml";
	
	public GUI getGUI() {
		try {
			return new GUI(
					new URL(url),
					"C#2Bot Cutter");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void addTasks() {
		if (Vars.progressiveLevel) {
			taskset.addTasks(new ProgLevel());
		}
		if (Vars.customChop){
			taskset.addTasks(new WoodCut());
		}
	}

	public int loop() {
		Task task = taskset.getTask();
		if (task != null) {
			task.execute();
		}
		return 1;
	}

	@Override
	public void run() {
		gui = getGUI();
		if (gui != null) {
			gui.show();
			while (gui.isOpen())
				sleep(250);
		}
		this.addTasks();
		while (Vars.start) {
			sleep(loop());
		}
	}

	@Override
	public void onEnd() {
		if (gui.isOpen()) {
			gui.close();
		}
		System.out.print("Thank you for using C#2Bot Cutter");
		int[] vars = new int[] { Vars.xpGained, 0, 0, 0, 0, 0, 0, 0 };
		if (SigData.send(Vars.runTime, vars)) {
			System.out.print("Dynamic signature data has been sent :)");
		}
	}

	@Override
	public void onPaint(Graphics g) {
		Paint.handle(status, g);
	}

	@Override
	public void onStart() {
		Mouse.setSpeed(General.random(100, 120));
		Walking.setWalkingTimeout(General.random(1500, 2000));
		General.useAntiBanCompliance(true);
		System.out.print("Starting c#2Bot Cutter");
	}

}
