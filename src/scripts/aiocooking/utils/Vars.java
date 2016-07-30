package scripts.aiocooking.utils;

import org.tribot.api2007.types.RSTile;

import scripts.aiocooking.taskframework.TaskSet;

public class Vars {

	public static long time_Ran = 0;
	public static long before_Start = System.currentTimeMillis();
	public static long xp_Per_Hour = 0;
	public static long cooked_Per_Hour = 0;
	public static int cooked = 0;
	public static int burned = 0;
	public static String status = "";
	public static String food = "";
	public static boolean start = false;
	public static TaskSet task_Set = new TaskSet();
	public static RSTile range_Tile = null;
	public static RSTile bank_Walk_Tile = null;
	public static RSTile cook_Walk_Tile = null;
	public static RSTile first_Bank_Area_tile = null;
	public static RSTile second_Bank_Area_tile = null;
	public static RSTile first_Cook_Area_Tile = null;
	public static RSTile second_Cook_Area_Tile = null;

}
