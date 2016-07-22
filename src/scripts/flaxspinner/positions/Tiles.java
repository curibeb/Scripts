package scripts.flaxspinner.positions;

import org.tribot.api2007.types.RSTile;

public enum Tiles {

	LUMBYFIRSTFLOORLADDERTILE(new RSTile(3204, 3207, 0)),

	LUMBYSECONDFLOORLADDERTILE(new RSTile(3204, 3207, 1)),

	LUMBYTHIRDFLOORLADDERTILE(new RSTile(3205, 3208, 2)),

	LUMBYDOORTILE(new RSTile(3207, 3214, 1)),

	CATHERBYDOORTILE(new RSTile(2716, 3472, 0)),

	CATHERBYFIRSTFLOORLADDERTILE(new RSTile(2715, 3470, 1)),

	CATHERBYGROUNDFLOORLADDERTILE(new RSTile(2715, 3470, 0)),

	CATHERBYWALKTODOORTILE(new RSTile(2717, 3472, 0)),;

	private RSTile tile;

	Tiles(RSTile tile) {
		this.tile = tile;
	}

	public RSTile getTile() {
		return tile;
	}

}
