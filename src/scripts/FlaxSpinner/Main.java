package scripts.FlaxSpinner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Projection;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.Walking;
import org.tribot.api2007.util.ThreadSettings;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.Starting;

import scripts.FlaxSpinner.Positions.Areas;
import scripts.FlaxSpinner.TaskFramwork.Task;
import scripts.FlaxSpinner.TaskFramwork.TaskSet;
import scripts.FlaxSpinner.Tasks.Bank;
import scripts.FlaxSpinner.Tasks.Spin;
import scripts.FlaxSpinner.Tasks.ToggleRun;
import scripts.FlaxSpinner.Utilities.Timer;
import scripts.FlaxSpinner.Utilities.Vars;

@ScriptManifest(authors = { "C#2bot" }, category = "Crafting", name = "C#Flax Spinner")
public class Main extends Script implements Painting, Starting {

	public TaskSet taskSet = new TaskSet();

	@Override
	public void run() {
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

	public void drawTile(RSTile tile, Graphics2D g, boolean fill) {
		if (tile.isOnScreen()) {
			if (Projection.getTileBoundsPoly(tile, 0) != null) {
				if (fill) {
					g.fillPolygon(Projection.getTileBoundsPoly(tile, 0));
				} else {
					g.drawPolygon(Projection.getTileBoundsPoly(tile, 0));
				}
			}
		}
	}

	@Override
	public void onPaint(Graphics g) {
		if (Vars.start) {
			for (RSTile t : Areas.BANK.getArea().getAllTiles()) {
				this.drawTile(t, (Graphics2D) g, false);
			}
			String status = taskSet.getTask().status();
			Vars.stringMade = Vars.xpGained / 15;
			Vars.timeRan = System.currentTimeMillis() - Vars.beforeStart;
			Vars.xpGained = Skills.getXP(SKILLS.CRAFTING) - Vars.craftXp;
			Vars.xpPerHour = (Vars.xpGained * (3600000 / Vars.timeRan));
			Vars.stringPerHour = (long) (Vars.stringMade * (3600000D / Vars.timeRan));

			g.setColor(Color.yellow);
			g.fillRect(280, 360, 180, 20);
			g.setColor(Color.red);
			if (Vars.lumby) {
				g.drawString("C#2Bot Flax Spinner: Lumb:" , 280, 375);
			} else {
				g.drawString("C#2Bot Flax Spinner: Catherby", 280, 375);
			}
			g.setColor(Color.red);
			g.fillRect(280, 390, 180, 120);
			g.setColor(Color.yellow);
			g.drawString("Time Running: " + Timing.msToString(Vars.timeRan), 300, 410);
			g.drawString("String made: " + Vars.stringMade, 300, 440);
			g.drawString("String Per Hour: " + Vars.stringPerHour, 300, 455);
			g.drawString("Xp gained: " + Vars.xpGained, 300, 470);
			g.drawString("Xp Per Hour: " + Vars.xpPerHour, 300, 485);
			if (status != null) {
				g.drawString("Status: " + status, 300, 500);
			}
		}
	}

	@Override
	public void onStart() {
		int reply = JOptionPane.showConfirmDialog(null,
				"You want to spin in lumby click yes, if you want to spin in catherby click no.",
				"Choose where to spin", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			Vars.start = true;
			Vars.lumby = true;
			taskSet.addTasks(new Bank(Areas.LUMBYTHIRDFLOOR.getArea()), new Spin(Areas.LUMBYTHIRDFLOOR.getArea()),
					new ToggleRun());
		} else {
			Vars.start = true;
			Vars.lumby = false;
			taskSet.addTasks(new Bank(Areas.BANK.getArea()), new Spin(Areas.BANK.getArea()), new ToggleRun());
		}
		Walking.setWalkingTimeout(General.random(1200, 1300));
		Mouse.setSpeed(General.random(140, 160));
		ThreadSettings.get().setClickingAPIUseDynamic(true);
		Spin.ANIMATION_TIMER = new Timer(0L);
	}
}
