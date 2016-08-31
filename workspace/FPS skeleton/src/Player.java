//Team Jedi
//FPS class skeleton code



public class Game{}
public class Player {
	private int health; 
	private int armor;
	private Weapon equipment[];
	private Location position;
	private int currentWeap;
	
	public Player(){} 
	public void used(){}
	public void move(){}
	public void getHealth(){}
	public void getArmor(){}
	public void spawn(){}
	public void takeDamage(int damage){}
	public void switchWeapon(){}
	public void swapWeapon(){}
	public void addAmmo(int amount){}
}
public class Weapon {
	private int ammo;
	private int damage;
	private int clip;
	private int clipSize;
	private int rateOfFire;
	
	public Weapon(int damage, int rateOfFire, int clipSize){}
	public void shoot(){}
	public void reload(){}
	public void addAmmo(int amount){}
}
public class Map {
	private int x;
	private int y;
	private int z;
	
	public Map(int size){}
	public boolean inBound(Location position){}
}
public class Building {
	private Location position;
	
	public Building(){}
}
public class Location {
	private int x;
	private int y;
	private int z;
	
	public Location(){}
	public void setLocation(){}
}










