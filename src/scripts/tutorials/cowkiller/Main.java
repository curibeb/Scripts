package scripts.tutorials.cowkiller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.Projection;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

import scripts.tutorials.cowkiller.methods.Bank;
import scripts.tutorials.cowkiller.methods.Cows;

// now lets learn how to add a script manefist because right now 
// because our script now is uncategorized
// so lets categorize our script
// and now we have our script manifested
@ScriptManifest(authors = { "C#2bot" }, category = "Tutorials", name = "C#2bot Cow Killer")
public class Main extends Script implements Painting {

	@Override
	public void run() {
		// now the script exectuted and terminated, just like we stated
		// in the first tutorial because we want the script to perform certain
		// loop

		while (true) {
			// now lets write our logic
			// so what do we want to do?
			// kill cows, collect hide and bank?
			// ok

			if (Inventory.isFull()) {
				// if our inventory is full lets bank
				// okay we couldnt fully test out script today since hooks are broken
				// however lets try to bank our stuff now
				Bank.bank();
			} else {
				Cows.kill();
				
			}

		}

	}

	public void drawTile(RSTile tile, Graphics2D g, boolean fill, Color color) {
		// first lets change the color of the tiles projection
		g.setColor(color);
		if (tile.isOnScreen()) {
			if (Projection.getTileBoundsPoly(tile, 0) != null) {
				if (fill) {
					g.fillPolygon(Projection.getTileBoundsPoly(tile, 0));
				} else {
					g.drawPolygon(Projection.getTileBoundsPoly(tile, 0));
				}
			}
		}
	}

	@Override
	public void onPaint(Graphics g) {

//		// now lets test our lumbridge ground area
//		// well ground floor is okay lets test the upper floors
//		for (RSTile t : Vars.lumbridgeGroundFloor.getAllTiles()) {
//			this.drawTile(t, (Graphics2D) g, false, Color.white);
//		}
//		// right now our second area is okay
//		for (RSTile t : Vars.lumbridgeSecondFloor.getAllTiles()) {
//			this.drawTile(t, (Graphics2D) g, false, Color.red);
//		}
//		// for the upper floor
//		for (RSTile t : Vars.lumbridgeThirdFloor.getAllTiles()) {
//			this.drawTile(t, (Graphics2D) g, false, Color.yellow);
//		}
//		// there now all our lumbridge castle floors are perfect
//
//		// now lets test those areas
//		// so both areas are as we desire
//
//		for (RSTile t : Vars.betweenCowsAndCastle.getAllTiles()) {
//			this.drawTile(t, (Graphics2D) g, false, Color.orange);
//		}
//		for (RSTile t : Vars.cowsArea.getAllTiles()) {
//			this.drawTile(t, (Graphics2D) g, false, Color.magenta);
//		}
//		// its always return false
//		g.drawString("Are we in combat? " + Player.getRSPlayer().isInCombat(), 300, 300);
	}

}
