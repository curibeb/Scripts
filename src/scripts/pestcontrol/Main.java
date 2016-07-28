package scripts.pestcontrol;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Walking;
import org.tribot.api2007.util.ThreadSettings;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.EventBlockingOverride;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.Starting;

import scripts.pestcontrol.gui.Gui;
import scripts.pestcontrol.paint.Paint;
import scripts.pestcontrol.taskframework.Task;
import scripts.pestcontrol.taskframework.TaskSet;
import scripts.pestcontrol.utilities.Vars;;

@ScriptManifest(authors = { "C#2bot" }, category = "Beta", name = "C#2Bot Pest Control")
public class Main extends Script implements Painting, Starting, EventBlockingOverride {

	public TaskSet taskSet = new TaskSet();
	public  JFrame gui = new Gui(this, taskSet);
	
	public static final Image image = Paint.getImage("http://i.imgur.com/RIyYzER.png");
	private final Rectangle showTiles = new Rectangle(300, 439, 105, 20);
	private final Rectangle showPaint = new Rectangle(409, 439, 105, 20);

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
				Vars.status = "Gui.";

				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


		while (Vars.start) {
			sleep(loop());
		}
	}

	public int loop() {
		Task task = taskSet.getTask();
		if (task != null) {
			task.execute();
		}
		return 1;
	}

	@Override
	public void onStart() {
		General.useAntiBanCompliance(true);
		Walking.setWalkingTimeout(General.random(3000, 5000));
		Mouse.setSpeed(General.random(140, 160));
		ThreadSettings.get().setClickingAPIUseDynamic(true);
	}

	@Override
	public void onPaint(Graphics g) {
		if (Vars.paint_Areas) {
			Paint.paintAreas(g);
		}
		Paint.checkPositionForPaint();

		if (Vars.show_Paint) {
			Paint.paintStrings(g, Vars.status);
		}
		g.setColor(Color.red);
	}

	@Override
	public OVERRIDE_RETURN overrideKeyEvent(KeyEvent e) {
		return OVERRIDE_RETURN.SEND;
	}

	@Override
	public OVERRIDE_RETURN overrideMouseEvent(MouseEvent e) {
		try {
			if (showTiles.contains(e.getPoint())) {
				if (e.getID() == MouseEvent.MOUSE_CLICKED) {
					e.consume();
					if (!Vars.paint_Areas)
						Vars.paint_Areas = true;
					else
						Vars.paint_Areas = false;
					return OVERRIDE_RETURN.DISMISS;
				} else if (e.getID() == MouseEvent.MOUSE_PRESSED)
					return OVERRIDE_RETURN.DISMISS;
			}
			if (showPaint.contains(e.getPoint())) {
				if (e.getID() == MouseEvent.MOUSE_CLICKED) {
					e.consume();
					if (!Vars.show_Paint)
						Vars.show_Paint = true;
					else
						Vars.show_Paint = false;
					return OVERRIDE_RETURN.DISMISS;
				} else if (e.getID() == MouseEvent.MOUSE_PRESSED)
					return OVERRIDE_RETURN.DISMISS;
			}
			return OVERRIDE_RETURN.PROCESS;
		} catch (Exception e2) {
			return OVERRIDE_RETURN.DISMISS;
		}
	}

}
