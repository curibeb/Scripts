package scripts.cutter;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Projection;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Ending;
import org.tribot.script.interfaces.Painting;

import scripts.cutter.gui.GUI;
import scripts.cutter.taskframework.Task;
import scripts.cutter.taskframework.TaskSet;
import scripts.cutter.utilities.Trees;
import scripts.cutter.utilities.Vars;

@ScriptManifest(authors = { "C#2Bot" }, category = "Woodcutting", name = "C#2Bot WoodCutter")
public class Main extends Script implements Painting, Ending {

	public static TaskSet taskSet = new TaskSet();

	public JFrame gui = new GUI(this, taskSet);

	@Override
	public void run() {
		Walking.setWalkingTimeout(General.random(1000, 1500));
		General.useAntiBanCompliance(true);
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Metal".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
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

	public void setXp() {
		if (Vars.tree.equalsIgnoreCase("willow")) {
			Vars.logXp = Trees.WILLOW.getXp();
		}
		if (Vars.tree.equalsIgnoreCase("yew")) {
			Vars.logXp = Trees.YEW.getXp();
		}
		if (Vars.tree.equalsIgnoreCase("tree")) {
			Vars.logXp = Trees.NORMAL.getXp();
		}
		if (Vars.tree.equalsIgnoreCase("oak")) {
			Vars.logXp = Trees.OAK.getXp();
		}
		if (Vars.tree.equalsIgnoreCase("magic")) {
			Vars.logXp = Trees.MAGIC.getXp();
		}
	}

	@Override
	public void onPaint(Graphics g) {
		setXp();
		Graphics2D b = (Graphics2D) g;
		if (Vars.start) {
			Vars.timeRan = System.currentTimeMillis() - Vars.beforeStart;
			Vars.xpGained = Skills.getXP(SKILLS.WOODCUTTING) - Vars.woodcutXp;
			Vars.logsChopped = (int) Math.round((Vars.xpGained / Vars.logXp));
			for (RSTile tile : Vars.treesLoc.getAllTiles()) {
				drawTile(tile, b, false);
			}
			g.setColor(Color.yellow);
			g.fillRect(280, 360, 180, 20);
			g.setColor(Color.red);
			g.drawString("C#2Bot Cutter", 330, 375);
			g.setColor(Color.red);
			g.fillRect(280, 390, 180, 80);
			g.setColor(Color.yellow);
			g.drawString("Time Running: " + Timing.msToString(Vars.timeRan), 300, 410);
			g.drawString("Woodcut Xp Gained: " + Vars.xpGained, 300, 425);
			g.drawString(Vars.tree + " Logs Chopped: " + Vars.logsChopped, 300, 440);
			g.drawString("Status: " + taskSet.getTask().status(), 300, 455);

		}
		g.drawString("Powerchop: " + Vars.powerchop, 300, 470);
	}

	@Override
	public void onEnd() {
		General.println("----------------------C#Cutter----------------------");
		General.println("Time Ran: " + Timing.msToString(Vars.timeRan));
		General.println("Xp Gained: " + Vars.xpGained);
		General.println(Vars.tree + " Logs Chopped: " + Vars.logsChopped);
		General.println("----------------------C#Cutter----------------------");
	}

}
