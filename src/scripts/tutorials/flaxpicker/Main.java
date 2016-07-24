package scripts.tutorials.flaxpicker;

import java.awt.Graphics;

import org.tribot.api.Timing;
import org.tribot.api2007.Inventory;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

import scripts.tutorials.flaxpicker.methods.Bank;
import scripts.tutorials.flaxpicker.methods.Flax;
import scripts.tutorials.flaxpicker.utilities.Vars;

@ScriptManifest(authors = { "C#2bot" }, category = "tutorials", name = "flax picking tutorial")
public class Main extends Script implements Painting {

	// that would be all, hope this helped and good luck!
	// hello and welcome to the very last tutorial in my series
	// today we will be making a flax picking script
	// okay lets start with the usuals

	@Override
	public void run() {
		while (true) {
			// so what do we want to do?
			// we want to pick and bank flax right?
			// okay lets write our logic

			if (Inventory.isFull()) {
				// if inventory is full we need to bank
				Bank.bank();
			} else {
				Flax.pick();
			}
		}
	}

	@Override
	public void onPaint(Graphics g) {
		// now lets say we wanted to add some paint
		// we want to keep track of flax picked , per hour and time ran
		Vars.timeRan = System.currentTimeMillis() - Vars.beforeStart;
		Vars.flaxPickerPerHour = (long) (Vars.flaxPicked * (360000D / Vars.timeRan));

		g.drawString("Flax Picked: " + Vars.flaxPicked, 300, 315);
		g.drawString("Flax Picked/Hour: " + Vars.flaxPickerPerHour, 300, 330);
		g.drawString("Time Script Running: " + Timing.msToString(Vars.timeRan), 300, 345);

	}

}
