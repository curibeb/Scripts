package scripts.cutter.api.entities;

import org.tribot.api2007.Player;
import org.tribot.api2007.Players;
import org.tribot.api2007.types.RSPlayer;

public class InteractPlayer {

	private RSPlayer[] players() {
		return Players.getAll();
	}

	public boolean interactingWithMe() {
		RSPlayer[] players = this.players();
		if (players.length == 0)
			return false;
		for (RSPlayer p : players) {
			return p.isInteractingWithMe();
		}
		return false;
	}
	
	public boolean playersNearMe() {
		RSPlayer[] players = this.players();
		if (players.length == 0)
			return false;
		for (RSPlayer p : players) {
			return Player.getPosition().distanceTo(p) <= 15;
		}
		return false;
	}
	
}
