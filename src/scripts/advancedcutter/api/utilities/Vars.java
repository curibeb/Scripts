package scripts.advancedcutter.api.utilities;

import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;

public class Vars {

	public static int logsPerHour;
	public static int logsChoped;
	public static int xpGained;
	public static int xpPerHour;
	public static int startXp = Skills.getXP(SKILLS.WOODCUTTING);
	public static long runTime;
	public static long beforeStartTime = System.currentTimeMillis();

	public static boolean progressiveLevel = false;
	public static boolean customChop = false;
	public static boolean standardChop = false;
	public static boolean start = false;
}
