package scripts.pestcontrol.utilities;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Vars {

	//
	public static String status = "";
	//

	//
	public static long timeRan;
	public static long timeBeforeStart = System.currentTimeMillis();
	public static int pointsGained = 0;
	public static long pointsPerHor = 0;
	public static int gamesWon = 0;
	public static int gamesLost = 0;
	public static long gamerPerHour;
	public static final int paintToggleX = 550;
	public static final int paintToggleY = 225;
	public static final int paintToggleWidth = 250;
	public static final int paintToggleHeight = 175;
	//

	// booleans
	public static boolean start = false;
	public static boolean novice = false;
	public static boolean intermediate = false;
	public static boolean veteran = false;
	public static boolean lostMsg = false;
	public static boolean wonMsg = false;
	public static boolean showPaint = true;
	public static boolean paintAreas = false;
	public static boolean isInGameBoatArea = false;
	public static boolean isInGameVoidKnightArea = false;
	public static boolean isInGameAroundVoidKnightArea = false;
	public static boolean isInLobbyBoatArea = false;
	public static boolean isInLobbyArea = false;
	//

	public static RSTile gangPlankTile;
	public static RSTile voidKnightTile;

	public static RSArea fullGameArea;
	public static RSArea gameBoatArea;
	public static RSArea gameVoidKnightProtectArea;
	public static RSArea gameAroundVoidKnightArea;
	public static RSArea bluePortalArea;
	public static RSArea yellowPortalArea;
	public static RSArea pinkPortalArea;
	public static RSArea puplePortalArea;
	public static RSArea southGate;
	public static RSArea eastGate;
	public static RSArea westGate;
	public static RSArea lobbyBoat;
	public static RSArea lobby;

}
