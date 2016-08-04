package scripts.pestcontrol.tasks;

import org.tribot.api.General;

import scripts.pestcontrol.api.antiban.Antiban;
import scripts.pestcontrol.enums.Priorities;
import scripts.pestcontrol.taskframework.Task;
import scripts.pestcontrol.utilities.AreaCheck;
import scripts.pestcontrol.utilities.Vars;

public class IdleBoat extends Task {

	@Override
	public int priority() {
		return Priorities.RIDE_BOAT.getPriority();
	}

	@Override
	public boolean validate() {
		return AreaCheck.isInsideLobbyBoat();
	}

	@Override
	public void execute() {
		Antiban.getReactionTime();
		Vars.status = "Idling";
		if (Vars.won_Msg) {
			Vars.points_Gained += 2;
			Vars.games_Won += 1;
			Vars.won_Msg = false;
		}
		if (Vars.lost_Msg) {
			Vars.games_Lost += 1;
			Vars.lost_Msg = false;
		}
		while (this.validate()){
			General.sleep(500,800);
			Antiban.timedActions();
		}
	}

}
