package scripts.aiocooking;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Walking;
import org.tribot.api2007.util.ThreadSettings;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.MessageListening07;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.Starting;

import scripts.aiocooking.gui.Gui;
import scripts.aiocooking.paint.Paint;
import scripts.aiocooking.taskframework.Task;
import scripts.aiocooking.tasks.Bank;
import scripts.aiocooking.tasks.Cook;
import scripts.aiocooking.tasks.ToggleRun;
import scripts.aiocooking.utils.Vars;

@ScriptManifest(authors = { "C#2Bot" }, category = "Cooking", name = "C#2Bot AIO Cooker")
public class Main extends Script implements Painting, Starting, MessageListening07 {

	public static String mission = "";
	public JFrame gui = new Gui(this, Vars.task_Set);

	@Override
	public void run() {
		EventQueue.invokeLater(() -> {
			try {
				gui.setVisible(true);
				gui.setLocationRelativeTo(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		while (gui.isVisible()) {
			sleep(50, 80);
		}
		while (Vars.start) {
			sleep(loop());
		}
	}

	public int loop() {
		Task task = Vars.task_Set.getTask();
		if (task != null) {
			mission = task.mission();
			task.execute();
		}
		return 1;
	}

	@Override
	public void onPaint(Graphics g) {
		if (Vars.start) {
			Paint.paint(g);
		}
	}

	@Override
	public void onStart() {
		Mouse.setSpeed(General.random(100, 120));
		Walking.setWalkingTimeout(1500);
		ThreadSettings.get().setClickingAPIUseDynamic(true);
		Vars.task_Set.addTasks(new Bank(), new Cook(), new ToggleRun());
	}

	@Override
	public void clanMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void duelRequestReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void personalMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void serverMessageReceived(String a) {
		if (a.contains("You successfully cook") || a.contains("You manage to cook")) {
			Vars.cooked++;
		}
		if (a.contains("You accidentally burn")) {
			Vars.burned++;
		}
	}

	@Override
	public void tradeRequestReceived(String arg0) {
		// TODO Auto-generated method stub

	}

}
