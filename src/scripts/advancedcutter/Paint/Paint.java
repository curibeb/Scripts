package scripts.advancedcutter.Paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.tribot.api.Timing;
import org.tribot.api2007.Projection;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.advancedcutter.Main;
import scripts.advancedcutter.api.utilities.Vars;

public class Paint {


	public static void handle(String status, Graphics g) {
		RSArea[] areas = new RSArea[] { Vars.chopArea, Vars.bankArea };
		if (areas.length > 0){
			for (RSArea area : areas){
				if (area !=null)
					drawTile(area,(Graphics2D)g,false,Color.magenta);
			}
		}
		Vars.runTime = (System.currentTimeMillis() - Vars.beforeStartTime);
		Vars.xpGained = Skills.getXP(SKILLS.WOODCUTTING) - Vars.startXp;
		g.drawString("Time running: " + Timing.msToString(Vars.runTime), 300, 300);
		g.drawString("Xp Gained: " + Vars.xpGained, 300, 315);
		g.drawString("Status: " + Main.status, 300, 330);
	}

	public static void drawTile(RSArea area, Graphics2D g, boolean fill, Color color) {
		if (area != null) {
			for (RSTile tile : area.getAllTiles()) {
				g.setColor(color);
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
		}
	}
}
