package scripts.tutorials.fishing.methods;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;

import scripts.tutorials.fishing.utilities.Vars;

public class Bank {
	// this will be out banking class where we store our banking methods

	// we do the same thing for the banking

	public static boolean isInBank() {
		return Vars.bankArea.contains(Player.getPosition());
	}

	public static void bank() {
		// now we need to check if the banking screen is open

		if (Banking.isBankScreenOpen()) {
			// if the banking screen if open
			
			// we need to deposit the fish
			// if we deposited the fish
			if (Banking.depositAllExcept(Vars.fishingNetID) > 0){
				Timing.waitCondition(new Condition(){
					@Override
					public boolean active() {
						// if the condition here is met the script will continue
						return !Inventory.isFull();
					}
					// if the bank didnt open sleep for this amount of random time
				}, General.random(4000, 7000));
			}
		} else {
			// if the banking screen is not open we open it
			// we need to check if we clicked the bank to allow some sleep like
			// in the fishing method
			if (Banking.openBank()) {
				Timing.waitCondition(new Condition(){
					@Override
					public boolean active() {
						// if the condition here is met the script will continue
						return Banking.isBankScreenOpen();
					}
					// if the bank didnt open sleep for this amount of random time
				}, General.random(4000, 7000));
			}
		}
	}
}
