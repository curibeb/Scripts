package scripts.pestcontrol.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Combat;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.pestcontrol.api.antiban.Antiban;
import scripts.pestcontrol.api.conditions.Conditions;
import scripts.pestcontrol.api.entities.InteractNpc;
import scripts.pestcontrol.api.entities.InteractObject;
import scripts.pestcontrol.enums.Areas;
import scripts.pestcontrol.enums.Interface;
import scripts.pestcontrol.enums.Priorities;
import scripts.pestcontrol.taskframework.Task;
import scripts.pestcontrol.utilities.AreaCheck;
import scripts.pestcontrol.utilities.Vars;

public class DefendKnights extends Task {

	private static RSArea[] portalAreas = new RSArea[] { Areas.BLUE_PORTAL_AREA.getArea(),
			Areas.PINK_PORTAL_AREA.getArea(), Areas.PURPLE_PORTAL_AREA.getArea(), Areas.YELLOW_PORTAL_AREA.getArea() };

	private static RSArea portalArea = portalAreas[Vars.getRandomPortal];

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
			Vars.status = "Idling.";
			General.sleep(500, 800);
			Antiban.timedActions();
		}
		if (needToGetDamage())
			getDamage();
		else
			defendKnight();
	}

	public static void getDamage() {
		if (Vars.getRandomPortal == 0) {
			Vars.getRandomPortal = General.random(0, 3);
		} else {
			if (portalArea != null) {
				if (portalArea.contains(Player.getPosition())) {
					attackPortalNpc();
				} else {
					getToPortal();
				}
			}
		}
	}

	private static void attackPortalNpc() {
		if (!Combat.isUnderAttack()) {
			if (spinnerAvailable()) {
				Antiban.getReactionTime();
				if (attackSpinners()) {
					Timing.waitCondition(Conditions.get().playerInteracting(), General.random(4000, 7000));
					Antiban.sleepReactionTime();
				}
			} else {
				Antiban.getReactionTime();
				if (attackAllNpcs(portalArea)) {
					Timing.waitCondition(Conditions.get().playerInteracting(), General.random(4000, 7000));
					Antiban.sleepReactionTime();
				}
			}
		}
	}

	private static boolean attackSpinners() {
		return new InteractNpc("Spinner", "Attack", portalArea, false).click();
	}

	private static boolean spinnerAvailable() {
		return new InteractNpc("Spinner", "", portalArea, false).npcAvailable();
	}

	private static void getToPortal() {
		RSTile temp = portalArea.getRandomTile();
		if (PathFinding.canReach(temp, false)) {
			Walking.blindWalkTo(temp);
		} else {
			Antiban.getReactionTime();
			if (openGate()) {
				Timing.waitCondition(Conditions.get().canReachTile(temp), General.random(4000, 7000));
				Antiban.sleepReactionTime();
			}
		}
	}

	private static boolean openGate() {
		return new InteractObject(false, null, Areas.FULL_GAME_AREA.getArea(), 30, "Gate", "Open").click();
	}

	private void defendKnight() {
		if (AreaCheck.isInsideGameVoidKnightArea()) {
			if (!Combat.isUnderAttack()) {
				if (attackAllNpcs(Areas.GAME_VOID_KNIGHT_PROTECT_AREA.getArea())) {
					Timing.waitCondition(Conditions.get().playerInteracting(), General.random(4000, 7000));
					Antiban.sleepReactionTime();
				}
			}
		} else {
			Vars.status = "Walking to void knight.";
			Walking.blindWalkTo(Vars.void_Knight_Tile);
		}
	}

	private static boolean attackAllNpcs(RSArea area) {
		return new InteractNpc(null, "Attack", area, true).click();
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
