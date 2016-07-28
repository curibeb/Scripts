package scripts.flaxspinner.utilities;

import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;

public class Vars {


	// bools
	public static boolean lumby = false;
	public static boolean start = false;
	//

	// ints
	public static long xp_Per_Hour = 0;
	public static long time_Ran = 0;
	public static long xp_Gained = 0;
	public static long string_Made = 0;
	public static long string_Per_Hour = 0;
	public static final int bank_Priority = 0;
	public static final int spin_Priority = 1;
	public static final int run_Priority = 2;
	public static final int craft_Xp = Skills.getXP(SKILLS.CRAFTING);
	public static final long before_Start = System.currentTimeMillis();
	//
}
