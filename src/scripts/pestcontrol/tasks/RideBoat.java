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
		return Priorities.RIDE_BOAT.getPriority();
	}

	@Override
	public boolean validate() {
		return AreaCheck.isInLobby();
	}

	public void resetPositions() {
		Vars.game_Boat_Area = null;
		Vars.game_Around_Void_Knight_Area = null;
		Vars.game_Void_Knight_Protect_Area = null;
		Vars.void_Knight_Tile = null;
		Vars.full_Game_Area = null;
	}

	@Override
	public void execute() {
		if (Interface.WON_GAME_MSG.text().contains("Congratulations")) {
			Vars.won_Msg = true;
		}
		if (Interface.LOST_GAME_MSG.text().contains("The knights noticed your lack")
				|| Interface.LOST_GAME_MSG.text().contains("The void knight was killed")) {
			Vars.lost_Msg = true;
		}
		if (AreaCheck.areAreasDefined()) {
			Vars.status = "Reseting areas.";
			this.resetPositions();
		}
		Vars.status = "Crossing plank to boat";
		RSObject[] plank = Objects.getAt(Vars.gang_Plank_Tile);
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
