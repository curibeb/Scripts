package scripts.pestcontrol.tasks;

import org.tribot.api.General;
import org.tribot.api2007.Combat;
import org.tribot.api2007.Player;

import scripts.pestcontrol.antiban.Antiban;
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
		return Vars.attack_Portals && AreaCheck.areAreasDefined() && AreaCheck.isInGame();
	}

	@Override
	public void execute() {
		while (Combat.isUnderAttack()) {
			General.sleep(500, 800);
			Antiban.doIdleActions();
		}
		if (Player.getRSPlayer().getInteractingCharacter() == null)
			DefendKnights.getDamage();
	}

}
