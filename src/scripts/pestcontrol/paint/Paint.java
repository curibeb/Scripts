package scripts.pestcontrol.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Game;
import org.tribot.api2007.Player;
import org.tribot.api2007.Projection;
import org.tribot.api2007.types.RSTile;

import scripts.pestcontrol.Main;
import scripts.pestcontrol.enums.Areas;
import scripts.pestcontrol.enums.Interface;
import scripts.pestcontrol.utilities.AreaCheck;
import scripts.pestcontrol.utilities.Vars;

public class Paint {

	public static void paintStrings(Graphics g, String status) {
		Font font = new Font("Arial", Font.BOLD, 18);
		g.setFont(font);
		Vars.time_Ran = System.currentTimeMillis() - Vars.time_Before_Start;
		Vars.point_Per_Hor = (long) (Vars.points_Gained * (3600000D / Vars.time_Ran));
		Vars.games_Per_Hour = (long) ((Vars.games_Lost + Vars.games_Won) * (3600000D / Vars.time_Ran));
		Graphics2D gg = (Graphics2D) g;
		gg.drawImage(Main.image, 0, 300, null);
		g.setColor(Color.red);
		g.drawString(status, 80, 480);
		// g.drawString("At Lobby: " + Vars.isInLobbyArea, 560, 245);
		// g.drawString("At Lobby Boat: " + Vars.isInLobbyBoatArea, 560, 260);
		// g.drawString("At Game Boat: " + Vars.isInGameBoatArea, 560, 275);
		// g.drawString("At Void Knight: " + Vars.isInGameAroundVoidKnightArea,
		// 560, 290);
		// g.drawString("At Area Around Knight: " + Vars.isInGameVoidKnightArea,
		// 560, 305);
		g.drawString("Void Knight Hp:" + Interface.VOID_KNIGHT_HP.getValue(), 80, 455);
		g.drawString("" + Vars.games_Per_Hour, 430, 480);
		g.drawString(Vars.points_Gained + "(" + Vars.point_Per_Hor + ")", 250, 405);
		g.drawString(Vars.games_Won + "(" + Vars.games_Lost + ")", 390, 405);
		g.drawString(Timing.msToString(Vars.time_Ran), 70, 405);
		g.setColor(Color.magenta);
		g.drawString("" + Interface.PURPLE_PORTAL.getValue(), 5, 400);
		g.setColor(Color.blue);
		g.drawString("" + Interface.BLUE_PORTAL.getValue(), 5, 420);
		g.setColor(Color.yellow);
		g.drawString("" + Interface.YELLO_PORTAL.getValue(), 5, 440);
		g.setColor(Color.pink);	
	}

	public static Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}

	public static void paintAreas(Graphics g) {
		for (Areas a : Areas.values()) {
			if (a != null) {
				for (RSTile t : a.getArea().getAllTiles()) {
					drawTile(t, (Graphics2D) g, false, Color.red);
				}
			}

		}
		if (Vars.void_Knight_Tile != null) {
			drawTile(Vars.void_Knight_Tile, (Graphics2D) g, false, Color.red);
		}
	}

	public static void drawTile(RSTile tile, Graphics2D g, boolean fill, Color color) {
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

	public static RSTile getHoveredTile() {
		if (!Projection.isInViewport(Mouse.getPos()))
			return null;
		RSTile pos = Player.getPosition();
		for (int x = pos.getX() - 10; x < pos.getX() + 10; x++) {
			for (int y = pos.getY() - 10; y < pos.getY() + 10; y++) {
				final RSTile tile = new RSTile(x, y, Game.getPlane());
				final Polygon tileBounds = Projection.getTileBoundsPoly(tile, 0);
				if (tileBounds != null && tileBounds.contains(Mouse.getPos()))
					return new RSTile(x, y, Game.getPlane());
			}
		}
		return null;
	}

	public static void checkPositionForPaint() {
		if (AreaCheck.isInLobby()) {
			Vars.is_In_Lobby_Area = true;
		} else {
			Vars.is_In_Lobby_Area = false;
		}
		if (AreaCheck.isInsideLobbyBoat()) {
			Vars.is_In_Lobby_Boat_Area = true;
		} else {
			Vars.is_In_Lobby_Boat_Area = false;
		}
		if (AreaCheck.areAreasDefined() && AreaCheck.isInsideGameBoat()) {
			Vars.is_In_Game_Boat_Area = true;
		} else {
			Vars.is_In_Game_Boat_Area = false;
		}
		if (AreaCheck.areAreasDefined() && AreaCheck.isInsideGameAroundVoidKnightArea()) {
			Vars.is_In_Game_Around_Void_Knight_Area = true;
		} else {
			Vars.is_In_Game_Around_Void_Knight_Area = false;
		}
		if (AreaCheck.areAreasDefined() && AreaCheck.isInsideGameVoidKnightArea()) {
			Vars.is_In_Game_Void_Knight_Area = true;
		} else {
			Vars.is_In_Game_Void_Knight_Area = false;

		}
	}
}
