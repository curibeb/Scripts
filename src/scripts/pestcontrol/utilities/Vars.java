package scripts.pestcontrol.utilities;

import org.tribot.api2007.types.RSTile;

public class Vars {

	//
	public static String status = "";
	//

	//
	public static int getRandomPortal = -1;
	public static long time_Ran;
	public static long time_Before_Start = System.currentTimeMillis();
	public static int points_Gained = 0;
	public static long point_Per_Hor = 0;
	public static int games_Won = 0;
	public static int games_Lost = 0;
	public static long games_Per_Hour;
	public static final int paint_Toggle_X = 550;
	public static final int paint_Toggle_Y = 225;
	public static final int paint_Toggle_Width = 250;
	public static final int paint_Toggle_Height = 175;
	//

	// booleans
	public static boolean start = false;
	public static boolean novice = false;
	public static boolean intermediate = false;
	public static boolean veteran = false;
	public static boolean lost_Msg = false;
	public static boolean won_Msg = false;
	public static boolean show_Paint = true;
	public static boolean paint_Areas = false;
	public static boolean is_In_Game_Boat_Area = false;
	public static boolean is_In_Game_Void_Knight_Area = false;
	public static boolean is_In_Game_Around_Void_Knight_Area = false;
	public static boolean is_In_Lobby_Boat_Area = false;
	public static boolean is_In_Lobby_Area = false;
	//

	public static RSTile gang_Plank_Tile;
	public static RSTile void_Knight_Tile = new RSTile(0,0,0);



}
