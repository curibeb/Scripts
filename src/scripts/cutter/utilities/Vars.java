package scripts.cutter.utilities;

import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.cutter.antiban.Antiban;

public class Vars {

	// ABC
	public static boolean should_Hover;
	public static boolean should_Check_Xp;
	public static boolean should_Check_Tabs;
	public static boolean should_Examine_Objects;
	public static boolean should_Lose_Mouse;
	//

	// RSAREA
	public static RSTile trees_Tile = null;
	public static RSArea bank = null;
	public static RSArea trees_Loc = null;
	//

	// Ids
	public static boolean start = false;
	public static Antiban antiban;
	public static int axe_Id = 0;
	public static String tree = "";
	public static boolean powerchop = false;
	//

	// Paint
	public static long before_Start = System.currentTimeMillis();
	public static long time_Ran;
	public static long woodcut_Xp = Skills.getXP(SKILLS.WOODCUTTING);
	public static int logs_Chopped = 0;
	public static double log_Xp = 0;
	public static long xp_Gained = 0;

	//

}
