package scripts.pestcontrol.antiban;

import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api.util.abc.ABCProperties;
import org.tribot.api.util.abc.preferences.TabSwitchPreference;
import org.tribot.api.util.abc.preferences.WalkingPreference;

public class Antiban extends org.tribot.api.util.abc.ABCUtil {

	private static Antiban _instance = null;
	private int runPercentage = this.generateRunActivation();
	private int eatPercentage = this.generateEatAtHP();
	private long waitingSince;

	private Antiban() {
	}

	public static Antiban get() {
		return _instance = _instance != null ? _instance : new Antiban();
	}
	
	public static Positionable determineNextTarget(Positionable[] tile){
		return get().selectNextTarget(tile);
	}

	public static int getEatPercentage() {
		return get().eatPercentage;
	}

	public static void generateEatPercentage() {
		get().eatPercentage = get().generateRunActivation();
	}

	public static int getRunPercentage() {
		return get().runPercentage;
	}

	public static void generateRunPercentage() {
		get().runPercentage = get().generateEatAtHP();
	}

	public static boolean shouldMoveAnticipated() {
		return get().shouldMoveToAnticipated();
	}

	public static boolean shouldHoverNext() {
		return Mouse.isInBounds() && get().shouldHover();
	}

	public static boolean shouldOpenMenuNext() {
		return Mouse.isInBounds() && get().shouldOpenMenu();
	}

	public static TabSwitchPreference getTabSwitchPreference() {
		return get().generateTabSwitchPreference();
	}

	public static WalkingPreference getWalkingPreference(final int distance) {
		return get().generateWalkingPreference(distance);
	}

	public static void setWaitingSince() {
		get().waitingSince = Timing.currentTimeMillis();
	}

	public void performReactionTimeWait() {
		final ABCProperties props = get().getProperties();
		props.setWaitingTime(getWaitingTime());
		props.setWaitingFixed(false);
		final int reactionTime = get().generateReactionTime();
		try {
			get().sleep(reactionTime);
		} catch (InterruptedException e) {

		}
	}

	public static int getWaitingTime() {
		int result = (int) (Timing.currentTimeMillis() - get().waitingSince);
		return result;
	}

	public static void doIdleActions() {

		if (get().shouldCheckTabs())
			get().checkTabs();

		if (get().shouldCheckXP())
			get().checkXP();

		if (get().shouldExamineEntity())
			get().examineEntity();

		if (get().shouldMoveMouse())
			get().moveMouse();

		if (get().shouldPickupMouse())
			get().pickupMouse();

		if (get().shouldRightClick())
			get().rightClick();

		if (get().shouldRotateCamera())
			get().rotateCamera();

		if (get().shouldLeaveGame())
			get().leaveGame();
	}


}
