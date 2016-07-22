package scripts.FlaxSpinner.Utilities;

import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;

public class Vars {


	// bools
	public static boolean lumby = false;
	public static boolean start = false;
	//

	// ints
	public static long xpPerHour = 0;
	public static long timeRan = 0;
	public static long xpGained = 0;
	public static long stringMade = 0;
	public static long stringPerHour = 0;
	public static final int bankPriority = 0;
	public static final int spinPriority = 1;
	public static final int runPriority = 2;
	public static final int craftXp = Skills.getXP(SKILLS.CRAFTING);
	public static final long beforeStart = System.currentTimeMillis();
	//
}
