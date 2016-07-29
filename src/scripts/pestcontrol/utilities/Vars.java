package scripts.pestcontrol.utilities;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Vars {

	//
	public static String status = "";
	//

	//
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
	public static boolean defend_Knight = false;
	public static boolean attack_Portals = false;
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
	public static RSTile void_Knight_Tile;

	public static RSArea full_Game_Area;
	public static RSArea game_Boat_Area;
	public static RSArea game_Void_Knight_Protect_Area;
	public static RSArea game_Around_Void_Knight_Area;
	public static RSArea blue_Portal_Area;
	public static RSArea yellow_Portal_Area;
	public static RSArea pink_Portal_Area;
	public static RSArea puple_Portal_Area;
	public static RSArea south_Gate;
	public static RSArea east_Gate;
	public static RSArea west_Gate;
	public static RSArea lobby_Boat;
	public static RSArea lobby;

}
