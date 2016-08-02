package scripts.api.conditions;

import org.tribot.api.rs3.Player;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;

public class Conditions {

	private static Conditions conditions = null;

	public static Conditions get() {
		return conditions != null ? conditions : new Conditions();
	}

	public Condition bankOpen() {
		return new Condition() {
			@Override
			public boolean active() {
				return Banking.isBankScreenOpen();
			}
		};
	}

	public Condition bankClosed() {
		return new Condition() {
			@Override
			public boolean active() {
				return !Banking.isBankScreenOpen();
			}
		};
	}

	public Condition animating() {
		return new Condition() {
			@Override
			public boolean active() {
				return Player.getAnimation() != -1;
			}
		};
	}

	public Condition notAnimating() {
		return new Condition() {
			@Override
			public boolean active() {
				return Player.getAnimation() != -1;
			}
		};
	}

	public Condition playerMoving() {
		return new Condition() {
			@Override
			public boolean active() {
				return Player.isMoving();
			}
		};
	}

	public Condition playerIdle() {
		return new Condition() {
			@Override
			public boolean active() {
				return !Player.isMoving();
			}
		};
	}

}
