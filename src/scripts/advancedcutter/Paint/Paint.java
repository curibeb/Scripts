package scripts.advancedcutter.Paint;

import java.awt.Graphics;

import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;

import scripts.advancedcutter.Main;
import scripts.advancedcutter.api.utilities.Vars;

public class Paint {

	public static void handle(String status, Graphics g) {
		Vars.runTime = (System.currentTimeMillis() - Vars.beforeStartTime);
		Vars.xpGained = Skills.getXP(SKILLS.WOODCUTTING) - Vars.startXp;
		g.drawString(Main.status, 300, 300);
	}
}
