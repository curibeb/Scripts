package scripts.pestcontrol.tasks;

import org.tribot.api.General;
import org.tribot.api2007.Combat;

import scripts.pestcontrol.api.antiban.Antiban;
import scripts.pestcontrol.enums.Priorities;
import scripts.pestcontrol.taskframework.Task;
import scripts.pestcontrol.utilities.AreaCheck;
import scripts.pestcontrol.utilities.Vars;

public class AttackPortals extends Task {

	@Override
	public int priority() {
		return Priorities.ATTACK_PORTALS.getPriority();
	}

	@Override
	public boolean validate() {
		return AreaCheck.isInGame();
	}

	@Override
	public void execute() {
		Antiban.getReactionTime();
		long t = System.currentTimeMillis();
		while (Combat.isUnderAttack() && (System.currentTimeMillis() - t) < 5000) {
			Vars.status = "Idling.";
			General.sleep(500, 800);
			Antiban.timedActions();
		}
		if (!Combat.isUnderAttack())
			DefendKnights.getDamage();
	}

}
