package scripts.tutorials.cowkiller.methods;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Objects;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSGroundItem;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.tutorials.cowkiller.utilities.Vars;

public class Cows {

	public static boolean isAtCow() {
		return Vars.cowsArea.contains(Player.getPosition());
	}

	public static void kill() {
		if (isAtCow()) {
			// if we are at the cows we should attack
			// however remember that we have an obstacle that we need to tackle
			// so lets declare a tile inside the cows area
			// now that we have a tile inside the cows area we can check if that
			// tile is reachable

			// if the tile is reachable and is not an object
			// then attack
			if (PathFinding.canReach(Vars.canReach, false)) {
				// this check is always returning false
				if (!Player.getRSPlayer().isInCombat()) {
					// if we should loot
					if (Vars.shouldLoot) {
						// lets check if the tile has the loot
						if (tileHasLoot())
							// if it does we should loot it
							loot();
						else
							// else we should get back to killing
							Vars.shouldLoot = false;
					} else {
						attack();
					}
				} else {
					// if we are in combat we need to find if the cow died so we
					// can grab out looting tile
					// the problem here is that the combat hooks are broken
					// and we cant check if the player is in actual combat
					// th
					getLootTile();
				}
			} else {
				// if we cant reach that tile that means the gate is closed
				// hence we need to open the gate
				openGate();
			}
		} else {
			// if we are not at cows we should walk there
			// now we are the upper floors we need to get down before we get to the cows
			
			if (Vars.lumbridgeThirdFloor.contains(Player.getPosition())){
				// if at third floor we climb down
				Bank.clickStairCase(new RSTile(3205, 3208, 2), "Climb-down", new Condition() {
					@Override
					public boolean active() {
						return Player.getPosition().getPlane() == 1;
					}
				});
			}
			
			if (Vars.lumbridgeSecondFloor.contains(Player.getPosition())){
				// if is in second floor we climb down
				Bank.clickStairCase(new RSTile(3204, 3207, 1), "Climb-down", new Condition() {
					@Override
					public boolean active() {
						return Player.getPosition().getPlane() == 0;
					}
				});
			}
			if (Vars.lumbridgeGroundFloor.contains(Player.getPosition())){
				// if at ground floor we walk to the cows
				WebWalking.walkTo(Vars.gateTile);
			}
		}
	}

	public static boolean tileHasLoot() {
		RSGroundItem[] cowHide = GroundItems.findNearest("Cow hide");
		if (cowHide.length > 0) {
			for (RSGroundItem i : cowHide) {
				if (Vars.lootTile != null) {
					// so if the item exists and its on the position we killed
					// the cow
					// then we should lot it
					return i.getPosition().equals(Vars.lootTile);
				}
			}
		}
		return false;
	}

	public static void getLootTile() {
		RSNPC[] cow = NPCs.findNearest("Cow");
		if (cow.length > 0) {
			// now here we need to loop through all the npcs in the area to get
			// our desired npc
			for (RSNPC c : cow) {
				if (c.isInteractingWithMe() && c.getAnimation() == Vars.deadCowAnimation)
					Vars.lootTile = c.getPosition();
			}
		}
	}

	public static void loot() {
		// anways lets move on to looting since we cant do much about
		// determining where we are in combat or not

		// assuming we were able to grab our loot tile we would loot the hide as
		// follows
		RSGroundItem[] loot = GroundItems.getAt(Vars.lootTile);
		if (loot.length > 0) {
			if (loot[0].isOnScreen()) {
				if (loot[0].click("Take"))
					Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							// if we looted we should sleep until theres no hide
							return !tileHasLoot();
						}

					}, General.random(4000, 6000));
			} else {
				Walking.blindWalkTo(loot[0]);
			}
		}
	}

	public static void openGate() {
		// so what we are doing here is that we are grabing what ever object in
		// that tile
		// which is the gate
		RSObject[] gate = Objects.findNearest(15, "Gate");
		if (gate.length > 0) {
			if (gate[0].getPosition().distanceTo(Vars.gateTile) < 3) {

				// okay now that we have trouble shooted and found a proper
				// solution
				// lets again check if the object is on screen
				if (gate[0].isOnScreen()) {
					if (gate[0].click("Open"))
						// if we click open the gate we should wait till its
						// opened
						Timing.waitCondition(new Condition() {
							@Override
							public boolean active() {
								return !Objects.isAt(Vars.gateTile, "Gate");
							}
						}, General.random(4000, 7000));
				} else {
					Walking.blindWalkTo(gate[0]);
				}
			}
		}
	}

	public static void attack() {
		// now we have opened the gate and determined that we can kill the cows
		// so lets do so
		RSNPC[] cow = NPCs.findNearest("Cow");
		if (cow.length > 0) {
			if (cow[0].isOnScreen()) {
				// need to check if the cow is in combat too
				if (!cow[0].isInCombat()) {
					if (cow[0].click("Attack"))
						Timing.waitCondition(new Condition() {
							@Override
							public boolean active() {
								// combats hooks are currently broken and
								// requires
								// update, thus
								// there will be problems detecting that we are
								// incombat none the less
								// lets work things out
								return Player.getRSPlayer().isInCombat();
							}
						}, General.random(4000, 6000));
				} else {
					Walking.blindWalkTo(cow[0]);
				}
			}
		}
	}

}
