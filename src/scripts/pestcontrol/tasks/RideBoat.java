package scripts.pestcontrol.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSObject;

import scripts.pestcontrol.enums.Interface;
import scripts.pestcontrol.enums.Priorities;
import scripts.pestcontrol.taskframework.Task;
import scripts.pestcontrol.utilities.AreaCheck;
import scripts.pestcontrol.utilities.Conditions;
import scripts.pestcontrol.utilities.Vars;

public class RideBoat extends Task {

	@Override
	public int priority() {
		return Priorities.RIDEBOAT.getPriority();
	}

	@Override
	public boolean validate() {
		return AreaCheck.isInLobby();
	}

	public void resetPositions() {
		Vars.gameBoatArea = null;
		Vars.gameAroundVoidKnightArea = null;
		Vars.gameVoidKnightProtectArea = null;
		Vars.voidKnightTile = null;
		Vars.fullGameArea = null;
	}

	@Override
	public void execute() {
		if (Interface.WONGGAMEMSG.text().contains("Congratulations")) {
			Vars.wonMsg = true;
		}
		if (Interface.LOSTGAMEMSG.text().contains("The knights noticed your lack")
				|| Interface.LOSTGAMEMSG.text().contains("The void knight was killed")) {
			Vars.lostMsg = true;
		}
		if (AreaCheck.areAreasDefined()) {
			Vars.status = "Reseting areas.";
			this.resetPositions();
		}
		Vars.status = "Crossing plank to boat";
		RSObject[] plank = Objects.getAt(Vars.gangPlankTile);
		if (plank.length > 0) {
			if (plank[0].isOnScreen()) {
				if (plank[0].click("Cross")) {
					Timing.waitCondition(Conditions.insideLobbyBoat(), General.random(4000, 7000));
				}
			} else {
				Walking.blindWalkTo(plank[0]);
			}
		}
	}

}
