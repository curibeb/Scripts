package scripts.tutorials.flaxpicker.methods;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;

import scripts.tutorials.flaxpicker.utilities.Vars;

// all classes should be uppercase unlike your methods lower case then uppercase
public class Bank {

	private static boolean isInBank() {
		return Vars.bankArea.contains(Player.getPosition());
	}

	public static void bank() {
		// if we are at bank
		if (isInBank()) {
			// and the bank screen is open
			if (Banking.isBankScreenOpen()){
				Vars.flaxPicked += Inventory.getCount(Vars.flax);
				if (Banking.depositAll() > 0){
					// if we deposited everything we need to check if we did
					Timing.waitCondition(new Condition(){
						@Override
						public boolean active() {
							return !Inventory.isFull();
						}					
					}, General.random(4000, 7000));
				}
			}else{
				// else if we are at bank and banking screen not open
				// we shall open it
				if (Banking.openBank()){
					// if we click open bank we should sleep for an x amount of time
					Timing.waitCondition(new Condition(){
						@Override
						// this will allow is to sleep in a range of 4000 to 7000 ms
						// until the condition below is met the sleep will hault
						public boolean active() {
							return Banking.isBankScreenOpen();
						}				
					}, General.random(4000, 7000));
				}
			}
		} else {
			// else if we are not in bank area and we need to bank
			// we walk there
			WebWalking.walkToBank();
		}
	}

}
