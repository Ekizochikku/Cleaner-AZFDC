import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Enemy {
	
	private GUIUtility utility;
	 
	 private String world;
	 
	 private String enemyName;
	 
	 private int enemyLevel;
	 
	 private double health;
	 
	 private String armor;
	 
	 private double AA;
	 
	 private String shipType;
	 
	 private String nation;
	 
	 public Enemy(String enemy, String world) throws FileNotFoundException, IOException {
		 ArrayList<String> ep = utility.getEnemyParameters(enemy, world);
		 setVariables(ep);
	 }
	 
	 private void setVariables(ArrayList<String> ep) {
		 world = ep.get(0);
		 enemyName = ep.get(1);
		 enemyLevel = Integer.parseInt(ep.get(2));
		 health = Double.parseDouble(ep.get(3));
		 armor = ep.get(4);
		 AA = Double.parseDouble(ep.get(5));
		 shipType = ep.get(6);
		 nation = ep.get(7);
	 }
	 
	 public String getWorld() {
		 return world;
	 }
	 
	 public String getEnemyName() {
		 return enemyName;
	 }
	 
	 public int getEnemyLvl() {
		 return enemyLevel;
	 }
	 
	 public double getHealth() {
		 return health;
	 }
	 
	 public String getArmor() {
		 return armor;
	 }
	 
	 public double getAA() {
		 return AA;
	 }
	 
	 public String getShipType() {
		 return shipType;
	 }
	 
	 public String getNation() {
		 return nation;
	 }
	 
}
