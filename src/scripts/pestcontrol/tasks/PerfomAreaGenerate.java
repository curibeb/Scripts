package scripts.pestcontrol.tasks;

import org.tribot.api2007.Game;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;

import scripts.pestcontrol.enums.Areas;
import scripts.pestcontrol.enums.Offsets;
import scripts.pestcontrol.enums.Priorities;
import scripts.pestcontrol.taskframework.Task;
import scripts.pestcontrol.utilities.AreaCheck;
import scripts.pestcontrol.utilities.Vars;

public class PerfomAreaGenerate extends Task {

	@Override
	public int priority() {
		return Priorities.GENERATE_AREA.getPriority();
	}

	@Override
	public boolean validate() {
		if (Game.getSetting(1021) == 32 && AreaCheck.shouldGenerateArea()) {
			if (!AreaCheck.areAreasDefined()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void execute() {
		this.resetPositions();
	}

	public void resetPositions() {
		Vars.status = "Grabbing required areas.";

		if (Vars.void_Knight_Tile != null) {
			Vars.west_Gate = Areas.WEST_GATE.getArea();
			Vars.east_Gate = Areas.EAST_GATE.getArea();
			Vars.south_Gate = Areas.SOUTH_GATE.getArea();
			Vars.game_Around_Void_Knight_Area = Areas.GAME_AROUND_VOID_KNIGHT_AREA.getArea();
			Vars.game_Void_Knight_Protect_Area = Areas.GAME_VOID_KNIGHT_PROTECT_AREA.getArea();
			Vars.game_Boat_Area = Areas.GAME_BOAT_AREA.getArea();
			Vars.blue_Portal_Area = Areas.BLUE_PORTAL_AREA.getArea();
			Vars.yellow_Portal_Area = Areas.YELLOW_PORTAL_AREA.getArea();
			Vars.pink_Portal_Area = Areas.PINK_PORTAL_AREA.getArea();
			Vars.puple_Portal_Area = Areas.PURPLE_PORTAL_AREA.getArea();
			Vars.full_Game_Area = Areas.FULL_GAME_AREA.getArea();
		} else {
			Vars.status = "Grabbing void knight tile.";
			Vars.void_Knight_Tile = generateRSTile(Offsets.VOID_KNIGHT_TILE.getXOffset(),
					Offsets.VOID_KNIGHT_TILE.getYOffset());
		}
	}

	public static RSTile generateRSTile(int xOffset, int yOffset) {
		RSNPC[] squire = NPCs.findNearest("Squire");
		if (squire.length > 0) {
			RSTile tile = squire[0].getPosition();
			RSTile xTile = new RSTile(tile.getX() + xOffset, tile.getY() + yOffset);
			return xTile;
		}
		return null;
	}

}
