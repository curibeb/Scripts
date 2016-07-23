package scripts.tutorials.fishing;

import java.awt.Graphics;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.script.Script;
import org.tribot.script.interfaces.Painting;

import scripts.tutorials.fishing.methods.Bank;
import scripts.tutorials.fishing.methods.Fish;
import scripts.tutorials.fishing.utilities.Vars;

public class Main extends Script implements Painting {

	
	// that would be all folks hope you enjoyed and learned
	
	@Override
	public void run() {
		// This is where your script logic will be executed.
		// now the script didnt work, cuz we need to create out loop
		// without the loop the script will execute and terminate

		while (true) {
			// so what should we do next?
			// determine what we want our script to do
			// if our inventory is not full we should fish
			// if its full we should bank
			if (Inventory.isFull()) {
				if (Bank.isInBank())
					Bank.bank();
				else
					Walking.blindWalkTo(Vars.bankArea.getRandomTile());
			} else {
				// if we are in the fishing area then we fish
				if (Fish.isInFishArea())
					Fish.catchFish();
				else
					// if we are not there then we walk there
					// this will walk us to a random tile within the specified
					// area
					Walking.blindWalkTo(Vars.fishingArea.getRandomTile());
			}
		}
	}

	@Override
	public void onPaint(Graphics g) {
		boolean inBank = Vars.bankArea.contains(Player.getPosition());
		boolean inFishingSpot = Vars.fishingArea.contains(Player.getPosition());

		g.drawString("We are in the bank area: " + inBank, 300, 100);
		g.drawString("We are in the fishing area: " + inFishingSpot, 300, 130);
	}
}
