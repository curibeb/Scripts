package scripts.tutorials.flaxpicker.utilities;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Vars {

	public static final RSArea flaxArea = new RSArea(new RSTile(2735, 3435, 0), new RSTile(2756, 3454, 0));
	public static final RSArea bankArea = new RSArea(new RSTile(2716, 3501, 0), new RSTile(2734, 3487, 0));

	public static final RSTile flaxTile = new RSTile(2742, 3445, 0);

	public static String flax = "Flax";
	
	// store the time before script started here
	public static long beforeStart = System.currentTimeMillis();
	public static long timeRan;
	public static int flaxPicked = 0;
	public static long flaxPickerPerHour =0;
}
