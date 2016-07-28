package scripts.flaxspinner;

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
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.util.ThreadSettings;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.Starting;

import scripts.flaxspinner.positions.Areas;
import scripts.flaxspinner.taskframework.Task;
import scripts.flaxspinner.taskframework.TaskSet;
import scripts.flaxspinner.tasks.Bank;
import scripts.flaxspinner.tasks.Spin;
import scripts.flaxspinner.tasks.ToggleRun;
import scripts.flaxspinner.utilities.Timer;
import scripts.flaxspinner.utilities.Vars;

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
			Vars.string_Made = Vars.xp_Gained / 15;
			Vars.time_Ran = System.currentTimeMillis() - Vars.before_Start;
			Vars.xp_Gained = Skills.getXP(SKILLS.CRAFTING) - Vars.craft_Xp;
			Vars.xp_Per_Hour = (Vars.xp_Gained * (3600000 / Vars.time_Ran));
			Vars.string_Per_Hour = (long) (Vars.string_Made * (3600000D / Vars.time_Ran));

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
			g.drawString("Time Running: " + Timing.msToString(Vars.time_Ran), 300, 410);
			g.drawString("String made: " + Vars.string_Made, 300, 440);
			g.drawString("String Per Hour: " + Vars.string_Per_Hour, 300, 455);
			g.drawString("Xp gained: " + Vars.xp_Gained, 300, 470);
			g.drawString("Xp Per Hour: " + Vars.xp_Per_Hour, 300, 485);
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
			taskSet.addTasks(new Bank(Areas.LUMBY_THIRD_FLOOR.getArea()), new Spin(Areas.LUMBY_THIRD_FLOOR.getArea()),
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
