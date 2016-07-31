package scripts.pestcontrol.tasks;

import org.tribot.api.General;
import org.tribot.api2007.Combat;
import org.tribot.api2007.Walking;

import scripts.pestcontrol.antiban.Antiban;
import scripts.pestcontrol.enums.Areas;
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
		return Priorities.DEFEND_KNIGHT.getPriority();
	}

	@Override
	public boolean validate() {
		return AreaCheck.isInGame();

	}

	@Override
	public void execute() {
		while (Combat.isUnderAttack()) {
			General.sleep(500, 800);
			Antiban.doIdleActions();
		}
		if (needToGetDamage())
			getDamage();
		else
			defendKnight();
	}

	public static void getDamage() {
		if (!Portals.portalDead(Interface.PINK_PORTAL.getValue()))
			Portals.attackPortalNpc(Areas.PINK_PORTAL_AREA.getArea(), Areas.WEST_GATE.getArea());
		else if (!Portals.portalDead(Interface.PURPLE_PORTAL.getValue()))
			Portals.attackPortalNpc(Areas.PURPLE_PORTAL_AREA.getArea(), Areas.EAST_GATE.getArea());

		else if (!Portals.portalDead(Interface.BLUE_PORTAL.getValue()))
			Portals.attackPortalNpc(Areas.BLUE_PORTAL_AREA.getArea(), Areas.EAST_GATE.getArea());

		else if (!Portals.portalDead(Interface.YELLO_PORTAL.getValue())) {
			Portals.attackPortalNpc(Areas.YELLOW_PORTAL_AREA.getArea(), Areas.SOUTH_GATE.getArea());
		}
	}

	public void defendKnight() {
		if (AreaCheck.isInsideGameVoidKnightArea()) {
			if (!Combat.isUnderAttack()) {
				if (Attack.target(Areas.GAME_VOID_KNIGHT_PROTECT_AREA.getArea()) != null) {
					Attack.attack(Areas.GAME_VOID_KNIGHT_PROTECT_AREA.getArea());
				} else {
					Vars.status = "Idling.";
					Antiban.doIdleActions();
				}
			}
		} else {
			Vars.status = "Walking to void knight.";
			Walking.blindWalkTo(Vars.void_Knight_Tile);
		}
	}

	private boolean needToGetDamage() {
		return portalHpLow() && Interface.DAMAGE_COLLECTED.getValue() < 50;
	}

	public boolean portalHpLow() {
		return (Interface.BLUE_PORTAL.getValue() == 0 && Interface.PURPLE_PORTAL.getValue() == 0)
				|| (Interface.PINK_PORTAL.getValue() == 0 && Interface.YELLO_PORTAL.getValue() == 0)
				|| (Interface.PURPLE_PORTAL.getValue() == 0 && Interface.PINK_PORTAL.getValue() == 0)
				|| (Interface.BLUE_PORTAL.getValue() == 0 && Interface.PINK_PORTAL.getValue() == 0)
				|| (Interface.BLUE_PORTAL.getValue() == 0 && Interface.PURPLE_PORTAL.getValue() == 0)
				|| (Interface.PURPLE_PORTAL.getValue() == 0 && Interface.PINK_PORTAL.getValue() == 0);
	}

}
