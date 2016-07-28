package scripts.pestcontrol.enums;

public enum Offsets {
	
	
	AROUND_VOID_KNIGHT_XTILE(14,-7),
	AROUND_VOID_KNIGHT_YTILE(-13,16),
	VOID_KNIGHT_XTILE(8,-5),
	VOID_KNIGHT_YTILE(-5,5),
	GAME_BOAT_XTILE(0,23),
	GAME_BOAT_YTILE(3,15),
	VOID_KNIGHT_TILE(1,-15),
	FULL_GAME_XTILE(+100,-100),
	FULL_GAME_YTILE(-100,+100),
	BLUE_PORTAL_XTILE(30, 2),
	BLUE_PORTAL_YTILE(15 ,-8),
	YELLOW_PORTAL_XTILE(20 ,-28),
	YELLOW_PORTAL_YTILE(6,-15),
	PINK_PORTAL_XTILE(-3,-31),
	PINK_PORTAL_YTILE(-15,-15),
	PURPLE_PORTAL_XTILE(-34, -10),
	PURPLE_PORTAL_YTILE(-18 ,5),
	SOUTH_GATEX_TILE(4 ,-10),
	SOUTH_GATE_YTILE(-2 ,-4),
	EAST_GATE_XTILE(18 ,-5),
	EAST_GATEY_TILE(11 ,5),
	WEST_GATE_XTILE(-17 ,5),
	WEST_GATEY_TILE(-10 ,-5),;
	private int xOffset;
	private int yOffset;
	
	Offsets(final int xOffset, final int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public int getXOffset(){
		return this.xOffset;
	}
	
	public int getYOffset(){
		return this.yOffset;
	}
	
}
