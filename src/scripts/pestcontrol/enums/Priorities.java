package scripts.pestcontrol.enums;

public enum Priorities {

	DEFEND_KNIGHT(1),ATTACK_PORTALS(1), IDLE_BOAT(0), GAME_CHAT(3), GENERATE_AREA(4), RIDE_BOAT(2) ,RUN_AT(5);

	private int priority;

	Priorities(final int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return this.priority;
	}
}
