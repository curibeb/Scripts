package scripts.aiocooking.enums;

public enum Food {
	
	MEAT("Raw meat",30),
	SHRIMP("Raw shrimps",30),
	CHICKEN("Raw chicken",30),
	SARDINE("Raw sardine",40),
	HERRING("Raw herring",50),
	TROUT("Raw trout",70),
	PIKE("Raw pike",80),
	SALMON("Raw salmon",90),
	TUNA("Raw tuna",100),
	LOBSTER("Raw lobster",120),
	SWORDFISH("Raw swordfish",140),;
	
	private String name;
	private int xp;
	
	Food (String name, int xp){
		this.name = name;
		this.xp = xp;
	}
	
	public String getName(){
		return name;
	}
	
	public int getXp(){
		return xp;
	}
	
}
