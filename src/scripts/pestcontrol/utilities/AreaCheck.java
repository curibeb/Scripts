package scripts.pestcontrol.utilities;

import org.tribot.api2007.NPCs;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.pestcontrol.enums.Areas;

public class AreaCheck {

	private static RSTile myTile() {
		return Player.getPosition();
	}

	public static boolean shouldGenerateArea() {
		RSNPC[] squire = NPCs.findNearest("Squire");
		RSObject[] rock = Objects.findNearest(25, "Rock");
		if (squire.length > 0 && rock.length > 0) {
			return squire[0].getPosition().distanceTo(rock[0].getPosition()) < 10;
		}
		return false;
	}


	public static boolean isInGame() {
		return Areas.FULL_GAME_AREA.getArea().contains(myTile());
	}

	public static boolean isInLobby() {
		return !Areas.LOBBY_BOAT.getArea().contains(myTile()) && Areas.LOBBY.getArea().contains(myTile());
	}

	public static boolean isInsideLobbyBoat() {
		return Areas.LOBBY_BOAT.getArea().contains(myTile()) && Areas.LOBBY.getArea().contains(myTile());
	}

	public static boolean isInsideGameBoat() {
		return Areas.GAME_BOAT_AREA.getArea().contains(myTile());
	}

	public static boolean isInsideGameVoidKnightArea() {
		return Areas.GAME_VOID_KNIGHT_PROTECT_AREA.getArea().contains(myTile());
	}

	public static boolean isInsideGameAroundVoidKnightArea() {
		return Areas.GAME_AROUND_VOID_KNIGHT_AREA.getArea().contains(myTile());
	}

}
