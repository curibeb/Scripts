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
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Player;
import org.tribot.api2007.Projection;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSTile;

import scripts.pestcontrol.Main;
import scripts.pestcontrol.enums.Interface;
import scripts.pestcontrol.utilities.AreaCheck;
import scripts.pestcontrol.utilities.Vars;

public class Paint {

	public static void paintStrings(Graphics g, String status) {
		Font font = new Font("Arial", Font.BOLD, 18);
		g.setFont(font);
		Vars.timeRan = System.currentTimeMillis() - Vars.timeBeforeStart;
		Vars.pointsPerHor = (long) (Vars.pointsGained * (3600000D / Vars.timeRan));
		Vars.gamerPerHour = (long) ((Vars.gamesLost + Vars.gamesWon) * (3600000D / Vars.timeRan));
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
		g.drawString("Void Knight Hp:" + Interface.VOIDKNIGHTHP.getValue(), 80, 455);
		g.drawString("" + Vars.gamerPerHour, 430, 480);
		g.drawString(Vars.pointsGained + "(" + Vars.pointsPerHor + ")", 250, 405);
		g.drawString(Vars.gamesWon + "(" + Vars.gamesLost + ")", 390, 405);
		g.drawString(Timing.msToString(Vars.timeRan), 70, 405);
		g.setColor(Color.magenta);
		g.drawString("" + Interface.PURPLEPORTAL.getValue(), 5, 400);
		g.setColor(Color.blue);
		g.drawString("" + Interface.BLUEPORTAL.getValue(), 5, 420);
		g.setColor(Color.yellow);
		g.drawString("" + Interface.YELLOPORTAL.getValue(), 5, 440);
		g.setColor(Color.pink);
		g.drawString("" + Interface.PINKPORTAL.getValue(), 5, 460);

	}

	public static Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}

	public static int getValue() {
		RSInterface portal = Interfaces.get(407, 15);
		if (portal != null && !portal.isHidden()) {
			String pt = portal.getText();
			pt = pt.replaceAll("[^\\d.]", "");
			if (pt != null) {
				if (pt.contains("")) {
					int pts = Integer.parseInt(pt);
					if (pts >= 0)
						return pts;
				}
			}
		}
		return 0;
	}

	public static void paintAreas(Graphics g) {
		if (Vars.fullGameArea != null) {
			for (RSTile t : Vars.fullGameArea.getAllTiles()) {
				drawTile(t, (Graphics2D) g, false, Color.black);
			}
		}
		if (Vars.gameBoatArea != null) {
			for (RSTile t : Vars.gameBoatArea.getAllTiles()) {
				drawTile(t, (Graphics2D) g, false, Color.white);
			}
		}
		if (Vars.lobbyBoat != null) {
			for (RSTile t : Vars.lobbyBoat.getAllTiles()) {
				drawTile(t, (Graphics2D) g, false, Color.white);
			}
		}

		if (Vars.gameAroundVoidKnightArea != null) {
			for (RSTile t : Vars.gameAroundVoidKnightArea.getAllTiles()) {
				drawTile(t, (Graphics2D) g, false, Color.LIGHT_GRAY);
			}
		}
		if (Vars.gameVoidKnightProtectArea != null) {
			for (RSTile t : Vars.gameVoidKnightProtectArea.getAllTiles()) {
				drawTile(t, (Graphics2D) g, false, Color.green);
			}
		}
		if (Vars.bluePortalArea != null) {
			for (RSTile t : Vars.bluePortalArea.getAllTiles()) {
				drawTile(t, (Graphics2D) g, false, Color.blue);
			}
		}
		if (Vars.yellowPortalArea != null) {
			for (RSTile t : Vars.yellowPortalArea.getAllTiles()) {
				drawTile(t, (Graphics2D) g, false, Color.yellow);
			}
		}
		if (Vars.pinkPortalArea != null) {
			for (RSTile t : Vars.pinkPortalArea.getAllTiles()) {
				drawTile(t, (Graphics2D) g, false, Color.pink);
			}
		}
		if (Vars.puplePortalArea != null) {
			for (RSTile t : Vars.puplePortalArea.getAllTiles()) {
				drawTile(t, (Graphics2D) g, false, Color.magenta);
			}
		}
		if (Vars.southGate != null) {
			for (RSTile t : Vars.southGate.getAllTiles()) {
				drawTile(t, (Graphics2D) g, false, Color.red);
			}
		}
		if (Vars.eastGate != null) {
			for (RSTile t : Vars.eastGate.getAllTiles()) {
				drawTile(t, (Graphics2D) g, false, Color.red);
			}
		}
		if (Vars.westGate != null) {
			for (RSTile t : Vars.westGate.getAllTiles()) {
				drawTile(t, (Graphics2D) g, false, Color.red);
			}
		}
		if (Vars.voidKnightTile != null) {
			drawTile(Vars.voidKnightTile, (Graphics2D) g, false, Color.red);
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
			Vars.isInLobbyArea = true;
		} else {
			Vars.isInLobbyArea = false;
		}
		if (AreaCheck.isInsideLobbyBoat()) {
			Vars.isInLobbyBoatArea = true;
		} else {
			Vars.isInLobbyBoatArea = false;
		}
		if (AreaCheck.areAreasDefined() && AreaCheck.isInsideGameBoat()) {
			Vars.isInGameBoatArea = true;
		} else {
			Vars.isInGameBoatArea = false;
		}
		if (AreaCheck.areAreasDefined() && AreaCheck.isInsideGameAroundVoidKnightArea()) {
			Vars.isInGameAroundVoidKnightArea = true;
		} else {
			Vars.isInGameAroundVoidKnightArea = false;
		}
		if (AreaCheck.areAreasDefined() && AreaCheck.isInsideGameVoidKnightArea()) {
			Vars.isInGameVoidKnightArea = true;
		} else {
			Vars.isInGameVoidKnightArea = false;

		}
	}
}
