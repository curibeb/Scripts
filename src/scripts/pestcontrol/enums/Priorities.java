package scripts.pestcontrol.enums;

public enum Priorities {

	DEFENDKNIGHT(1),ATTACKPORTALS(1), IDLEBOAT(0), GAMECHAT(3), GENERATEAREA(4), RIDEBOAT(2);

	private int priority;

	Priorities(final int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return this.priority;
	}
}
