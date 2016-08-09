package scripts.pestcontrol.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;

import scripts.pestcontrol.api.antiban.Antiban;
import scripts.pestcontrol.api.conditions.Conditions;
import scripts.pestcontrol.api.entities.InteractObject;
import scripts.pestcontrol.enums.Areas;
import scripts.pestcontrol.enums.Interface;
import scripts.pestcontrol.enums.Priorities;
import scripts.pestcontrol.taskframework.Task;
import scripts.pestcontrol.utilities.AreaCheck;
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

	@Override
	public void execute() {
		Vars.getRandomPortal = -1;
		Antiban.getReactionTime();
		if (Interface.WON_GAME_MSG.text().contains("Congratulations")) {
			Vars.won_Msg = true;
		}
		if (Interface.LOST_GAME_MSG.text().contains("The knights noticed your lack")
				|| Interface.LOST_GAME_MSG.text().contains("The void knight was killed")) {
			Vars.lost_Msg = true;
		}
		Vars.status = "Crossing plank to boat";
		if (this.interactObject()) {
			Timing.waitCondition(Conditions.get().isInArea(Areas.LOBBY_BOAT.getArea()), General.random(4000, 7000));
			Antiban.sleepReactionTime();
		}
	}

	private boolean interactObject() {
		return new InteractObject(true, Vars.gang_Plank_Tile, null, 0, null, "Cross").click();
	}

}
