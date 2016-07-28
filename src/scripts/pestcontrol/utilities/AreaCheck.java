package scripts.pestcontrol.utilities;

import org.tribot.api2007.NPCs;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

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

	public static boolean areAreasDefined() {
		return Vars.game_Boat_Area != null && Vars.game_Around_Void_Knight_Area != null
				&& Vars.game_Void_Knight_Protect_Area != null && Vars.full_Game_Area != null;
	}

	public static boolean isInGame() {
		return Vars.full_Game_Area.contains(myTile());
	}

	public static boolean isInLobby() {
		return !Vars.lobby_Boat.contains(myTile()) && Vars.lobby.contains(myTile());
	}

	public static boolean isInsideLobbyBoat() {
		return Vars.lobby_Boat.contains(myTile()) && Vars.lobby.contains(myTile());
	}

	public static boolean isInsideGameBoat() {
		return Vars.game_Boat_Area.contains(myTile());
	}

	public static boolean isInsideGameVoidKnightArea() {
		return Vars.game_Void_Knight_Protect_Area.contains(myTile());
	}

	public static boolean isInsideGameAroundVoidKnightArea() {
		return Vars.game_Around_Void_Knight_Area.contains(myTile());
	}

}
