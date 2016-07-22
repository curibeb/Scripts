package scripts.Cutter.Utilities;

import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSArea;

import scripts.Cutter.AntiBan.Antiban;

public class Vars {

	// ABC
	public static boolean shouldHover;
	public static boolean shouldCheckXp;
	public static boolean shouldCheckTabs;
	public static boolean shouldExamineObjects;
	public static boolean shouldLoseMouse;
	//

	// RSAREA
	public static RSArea bank = null;
	public static RSArea treesLoc = null;
	//

	// Ids
	public static boolean start = false;
	public static Antiban antiban;
	public static int axeId = 0;
	public static String tree = "";
	public static boolean powerchop = false;
	//

	// Paint
	public static long beforeStart = System.currentTimeMillis();
	public static long timeRan;
	public static long woodcutXp = Skills.getXP(SKILLS.WOODCUTTING);
	public static int logsChopped = 0;
	public static double logXp = 0;
	public static long xpGained = 0;

	//

}
