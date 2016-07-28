package scripts.pestcontrol.tasks;

import org.tribot.api.General;
import org.tribot.api2007.Player;

import scripts.pestcontrol.antiban.Antiban;
import scripts.pestcontrol.enums.Priorities;
import scripts.pestcontrol.taskframework.Task;
import scripts.pestcontrol.utilities.AreaCheck;

public class AttackPortals extends Task {

	@Override
	public int priority() {
		// TODO Auto-generated method stub
		return Priorities.ATTACK_PORTALS.getPriority();
	}

	@Override
	public boolean validate() {
		return AreaCheck.areAreasDefined() && AreaCheck.isInGame();
	}

	@Override
	public void execute() {
		while (Player.getRSPlayer().getInteractingCharacter() != null) {
			General.sleep(500,800);
			Antiban.doIdleActions();
		}
		if (Player.getRSPlayer().getInteractingCharacter() == null)
			DefendKnights.getDamage();
	}

}
