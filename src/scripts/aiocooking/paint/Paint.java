package scripts.aiocooking.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.tribot.api.Timing;
import org.tribot.api2007.Projection;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.aiocooking.Main;
import scripts.aiocooking.enums.Food;
import scripts.aiocooking.enums.Objectives;
import scripts.aiocooking.utils.Vars;

public class Paint {

	public static void paint(Graphics g) {
		int alpha = 127; // 50% transparent
		Color myColour = new Color(255, 0, 0, alpha);
		g.setColor(myColour);
		g.fillRect(288, 250, 230, 180);
		Vars.time_Ran = System.currentTimeMillis() - Vars.before_Start;
		Vars.xp_Per_Hour = (long) ((Vars.cooked * xp()) * (3600000D / Vars.time_Ran));
		Vars.cooked_Per_Hour = (long) (Vars.cooked * (3600000D / Vars.time_Ran));
		Paint.drawTile(Objectives.BANK.get_Area(), (Graphics2D) g, false, Color.red);
		Paint.drawTile(Objectives.COOK.get_Area(), (Graphics2D) g, false, Color.yellow);
		Paint.drawTile(Objectives.BANK.get_Walk_Tile(), (Graphics2D) g, false, Color.blue);
		Paint.drawTile(Objectives.COOK.get_Walk_Tile(), (Graphics2D) g, false, Color.magenta);
		Paint.drawTile(Vars.range_Tile, (Graphics2D) g, false, Color.magenta);
		g.setColor(Color.yellow);
		g.drawString("C#2Bot AIO COOKER", 340, 270);
		g.setColor(Color.white);
		g.drawString("Current mission: " + Main.mission, 300, 300);
		g.drawString("Status: " + Vars.status, 300, 315);
		g.drawString(Vars.food + " cooked: " + Vars.cooked, 300, 330);
		g.drawString(Vars.food + " burned: " + Vars.burned, 300, 345);
		g.drawString(Vars.food + " cooker/hour: " + Vars.cooked_Per_Hour, 300, 360);
		g.drawString("Cook xp Gained: " + Vars.cooked * xp(), 300, 375);
		g.drawString("Cook xp/hour: " + Vars.xp_Per_Hour , 300, 390);
		g.drawString("Time Running: " + Timing.msToString(Vars.time_Ran) , 300, 405);
	}

	public static int xp() {
		for (Food f : Food.values()) {
			if (f.getName().equals(Vars.food)) {
				return f.getXp();
			}
		}
		return 0;
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

	public static void drawTile(RSTile tile, Graphics2D g, boolean fill, Color color) {
		g.setColor(color);
		if (tile != null) {
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
