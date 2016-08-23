package scripts.advancedcutter.enums.treeareas;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public enum VarrockTrees {

	NORMALS_WEST {
		@Override
		public RSArea getArea() {
			return new RSArea(new RSTile(3173, 3376, 0), new RSTile(3153, 3418, 0));
		}

		@Override
		public RSTile getWalkTile() {
			return new RSTile(3164, 3407, 0);
		}
	},
	OAKS_SOUTH {
		@Override
		public RSArea getArea() {
			return new RSArea(new RSTile(3192, 3381, 0), new RSTile(3225, 3352, 0));
		}

		@Override
		public RSTile getWalkTile() {
			return new RSTile(3204, 3367, 0);
		}

	};

	public abstract RSArea getArea();

	public abstract RSTile getWalkTile();
}
