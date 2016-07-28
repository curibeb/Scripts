package scripts.pestcontrol.tasks;

import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;

import scripts.pestcontrol.antiban.Antiban;
import scripts.pestcontrol.enums.Interface;
import scripts.pestcontrol.enums.Priorities;
import scripts.pestcontrol.taskframework.Task;
import scripts.pestcontrol.utilities.AreaCheck;
import scripts.pestcontrol.utilities.Attack;
import scripts.pestcontrol.utilities.Portals;
import scripts.pestcontrol.utilities.Vars;

public class DefendKnights extends Task {

	@Override
	public int priority() {
		return  Priorities.DEFENDKNIGHT.getPriority();
	}

	@Override
	public boolean validate() {
		return AreaCheck.areAreasDefined() && AreaCheck.isInGame();

	}

	@Override
	public void execute() {

		while (Player.getRSPlayer().getInteractingCharacter() != null) {
			Antiban.doIdleActions();
		}

		if (this.needToGetDamage())
			getDamage();
		else
			this.defendKnight();

	}

	public static void getDamage() {
		if (!Portals.portalDead(Interface.PINKPORTAL.getValue()))
			Portals.attackPortalNpc(Vars.pinkPortalArea, Vars.westGate);
		else if (!Portals.portalDead(Interface.PURPLEPORTAL.getValue()))
			Portals.attackPortalNpc(Vars.puplePortalArea, Vars.southGate);

		else if (!Portals.portalDead(Interface.BLUEPORTAL.getValue()))
			Portals.attackPortalNpc(Vars.bluePortalArea, Vars.eastGate);

		else if (!Portals.portalDead(Interface.YELLOPORTAL.getValue())) {
			Portals.attackPortalNpc(Vars.yellowPortalArea, Vars.southGate);
		}
	}

	public void defendKnight() {
		if (AreaCheck.isInsideGameVoidKnightArea()) {
			if (Player.getRSPlayer().getInteractingCharacter() == null) {
				if (Attack.target(Vars.gameVoidKnightProtectArea) != null) {
					Attack.attack(Vars.gameVoidKnightProtectArea);
				} else {
					Vars.status = "Idling.";
					Antiban.doIdleActions();
				}
			}
		} else {
			Vars.status = "Walking to void knight.";
			Walking.blindWalkTo(Vars.voidKnightTile);
		}
	}

	private boolean needToGetDamage() {
		return portalHpLow() && Interface.DAMAGECOLLECTED.getValue() < 50;
	}

	public boolean portalHpLow() {
		return (Interface.BLUEPORTAL.getValue() == 0 && Interface.PURPLEPORTAL.getValue() == 0)
				|| (Interface.PINKPORTAL.getValue() == 0 && Interface.YELLOPORTAL.getValue() == 0)
				|| (Interface.PURPLEPORTAL.getValue() == 0 && Interface.PINKPORTAL.getValue() == 0)
				|| (Interface.BLUEPORTAL.getValue() == 0 && Interface.PINKPORTAL.getValue() == 0)
				|| (Interface.BLUEPORTAL.getValue() == 0 && Interface.PURPLEPORTAL.getValue() == 0)
				|| (Interface.PURPLEPORTAL.getValue() == 0 && Interface.PINKPORTAL.getValue() == 0);
	}

}
