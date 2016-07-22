package scripts.pestcontrol.tasks;

import org.tribot.api2007.Game;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSArea;
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
		return Priorities.GENERATEAREA.getPriority();
	}

	RSTile bankTile = Vars.lobbyBoat.getRandomTile();

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
		if (Vars.voidKnightTile != null) {
			Vars.status = "Grabbing required areas.";

			Vars.westGate = new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.WESTGATEXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.WESTGATEXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.WESTGATEYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.WESTGATEYTILE.getYOffset()));

			Vars.eastGate = new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.EASTGATEXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.EASTGATEXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.EASTGATEYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.EASTGATEYTILE.getYOffset()));

			Vars.southGate = new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.SOUTHGATEXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.SOUTHGATEXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.SOUTHGATEYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.SOUTHGATEYTILE.getYOffset()));

			Vars.gameAroundVoidKnightArea = new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.AROUNDVOIDKNIGHTXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.AROUNDVOIDKNIGHTXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.AROUNDVOIDKNIGHTyTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.AROUNDVOIDKNIGHTyTILE.getYOffset()));

			Vars.gameVoidKnightProtectArea = new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.VOIDKNIGHTXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.VOIDKNIGHTXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.VOIDKNIGHTYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.VOIDKNIGHTYTILE.getYOffset()));

			Vars.gameBoatArea = new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.GAMEBOATXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.GAMEBOATXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.GAMEBOATYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.GAMEBOATYTILE.getYOffset()));

			Vars.bluePortalArea = new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.BLUEPORTALXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.BLUEPORTALXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.BLUEPORTALYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.BLUEPORTALYTILE.getYOffset()));

			Vars.yellowPortalArea = new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.YELLOWPORTALXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.YELLOWPORTALXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.YELLOWPORTALYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.YELLOWPORTALYTILE.getYOffset()));

			Vars.pinkPortalArea = new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.PINKPORTALXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.PINKPORTALXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.PINKPORTALYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.PINKPORTALYTILE.getYOffset()));

			Vars.puplePortalArea = new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.PURPLEPORTALXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.PURPLEPORTALXTILE.getYOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.PURPLEPORTALYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.PURPLEPORTALYTILE.getYOffset()));

			Vars.fullGameArea = new RSArea(
					new RSTile(Vars.voidKnightTile.getX() + Offsets.FULLGAMEXTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.FULLGAMEYTILE.getXOffset()),
					new RSTile(Vars.voidKnightTile.getX() + Offsets.FULLGAMEYTILE.getXOffset(),
							Vars.voidKnightTile.getY() + Offsets.FULLGAMEYTILE.getYOffset()));
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
