package scripts.Cutter.Utilities;

public enum Trees {
	NORMAL(25.0), OAK(37.5), WILLOW(67.5), YEW(175.0), MAGIC(250.0),;

	private double xP;

	Trees(final double xp) {
		this.xP = xp;
	}

	public double getXp() {
		return this.xP;
	}
}
