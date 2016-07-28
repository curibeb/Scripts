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
		return Priorities.GENERATEAREA.getPriority();
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

		if (Vars.voidKnightTile != null) {
			Vars.westGate = Areas.WESTGATE.getArea();
			Vars.eastGate = Areas.EASTGATE.getArea();
			Vars.southGate = Areas.SOUTHGATE.getArea();
			Vars.gameAroundVoidKnightArea = Areas.GAMEAROUNDVOIDKNIGHTAREA.getArea();
			Vars.gameVoidKnightProtectArea = Areas.GAMEVOIDKNIGHTPROTECTAREA.getArea();
			Vars.gameBoatArea = Areas.GAMEBOATAREA.getArea();
			Vars.bluePortalArea = Areas.BLUEPORTALAREA.getArea();
			Vars.yellowPortalArea = Areas.YELLOWPORTALAREA.getArea();
			Vars.pinkPortalArea = Areas.PINKPORTALAREA.getArea();
			Vars.puplePortalArea = Areas.PURPLEPORTALAREA.getArea();
			Vars.fullGameArea = Areas.FULLGAMEAREA.getArea();
		} else {
			Vars.status = "Grabbing void knight tile.";
			Vars.voidKnightTile = generateRSTile(Offsets.VOIDKNIGHTTILE.getXOffset(),
					Offsets.VOIDKNIGHTTILE.getYOffset());
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
