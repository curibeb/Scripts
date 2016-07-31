package scripts.pestcontrol.tasks;

import org.tribot.api2007.Game;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;

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
		return Game.getSetting(1021) == 32 && AreaCheck.shouldGenerateArea() && !AreaCheck.isInGame();

	}

	@Override
	public void execute() {
		this.resetPositions();
	}

	public void resetPositions() {
		Vars.status = "Grabbing void knight tile.";
		Vars.void_Knight_Tile = generateRSTile(Offsets.VOID_KNIGHT_TILE.getXOffset(),
				Offsets.VOID_KNIGHT_TILE.getYOffset());
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
