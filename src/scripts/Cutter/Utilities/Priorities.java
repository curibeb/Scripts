package scripts.Cutter.Utilities;

public enum Priorities {

	BANK(0), CHOP(1), ENT(2), GEAR(3), POWERCHOP(3), RUN(3),;

	private int priority;

	Priorities(final int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return this.priority;
	}
}
