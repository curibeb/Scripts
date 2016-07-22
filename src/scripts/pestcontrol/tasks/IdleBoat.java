package scripts.pestcontrol.tasks;

import scripts.pestcontrol.antiban.Antiban;
import scripts.pestcontrol.enums.Priorities;
import scripts.pestcontrol.taskframework.Task;
import scripts.pestcontrol.utilities.AreaCheck;
import scripts.pestcontrol.utilities.Vars;

public class IdleBoat extends Task {

	@Override
	public int priority() {
		return Priorities.RIDEBOAT.getPriority();
	}

	@Override
	public boolean validate() {
		return AreaCheck.isInsideLobbyBoat();
	}
	
	@Override
	public void execute() {
		Vars.status = "Idling";
		if (Vars.wonMsg) {
			Vars.pointsGained += 2;
			Vars.gamesWon +=1;
			Vars.wonMsg = false;
		}
		if (Vars.lostMsg) {
			Vars.gamesLost +=1;
			Vars.lostMsg = false;
		}
		Antiban.doIdleActions();
	}



}
