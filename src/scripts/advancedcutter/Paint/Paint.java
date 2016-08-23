package scripts.advancedcutter.Paint;

import java.awt.Graphics;

import org.tribot.api.Timing;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;

import scripts.advancedcutter.Main;
import scripts.advancedcutter.api.utilities.Vars;

public class Paint {

	public static void handle(String status, Graphics g) {
		Vars.runTime = (System.currentTimeMillis() - Vars.beforeStartTime);
		Vars.xpGained = Skills.getXP(SKILLS.WOODCUTTING) - Vars.startXp;
		g.drawString("Time running: " + Timing.msToString(Vars.runTime), 300, 300);
		g.drawString("Xp Gained: " + Vars.xpGained, 300, 315);
		g.drawString("Status: " + Main.status, 300, 330);
	}
}
