import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Skill {

	GUIUtility utility;
	
	private String skillName;
	
	private String skillDesc;
	
	private String skillUsers;
	
	private double injureRatio;
	
	private double dmgRatio;
	
	//These are 0s or 1s to determine if dmgRatio will be applied to the damage calculation.
	private int dmgRatioCannon;
	
	private int dmgRatioTorpedo;
	
	private int dmgRatioAviation;
	
	// Boosts to flat stats.
	private double buffToFirepower;
	
	private double buffToTorpedo;
	
	private double buffToAviation;
	
	private double buffToAA;
	
	// Boosts against factions.
	private double dmgToHMS;
	
	private double dmgToUSS;
	
	private double dmgToIJN;
	
	private double dmgToKMS;
	
	private double dmgToROC;
	
	private double dmgToFFNF;
	
	private double dmgToMNF;
	
	private double dmgToSIREN;
	
	//Boosts against ship type.
	private double dmgToDD;
	
	private double dmgToCL;
	
	private double dmgToCA;
	
	private double dmgToLC;
	
	private double dmgToBC;
	
	private double dmgToBB;
	
	private double dmgToAB;
	
	private double dmgToCVL;
	
	private double dmgToCV;
	
	private double dmgToSUB;
	
	//Ammo Type Buff
	private double HEBUFF;
	
	private double APBUFF;
	// Initial and enhance buff
	
	private double initialEnhance;
	
	private double manualEnhance;
	
	//Injury and Damage.Buffs that multiply by X buffs
	private double injuryByCannon;
	
	private double injuryByTorpedo;
	
	private double injuryByAir;
	
	private double dmgByCannon;
	
	private double dmgByTorp;
	
	private double dmgByAir;
	
	private double comboDamage;
	
	//Critical Damage Buffs
	private double critByCannon;
	
	private double critByTorp;
	
	private double critByAir;
	
	// Salvo Damage bonus by BC and BB
	private double salvoBonus;
	
	public Skill(String skillName) throws FileNotFoundException, IOException {
		utility = new GUIUtility();
		ArrayList<String> list = utility.getSkillParameters(skillName);
		setParameters(list);
	}
	
	private void setParameters(ArrayList<String> list) {
		this.skillName = list.get(0);
		this.skillDesc = list.get(1);
		this.skillUsers = list.get(2);
		this.injureRatio = Double.parseDouble(list.get(3));
		this.dmgRatio = Double.parseDouble(list.get(4));
		this.dmgRatioCannon = Integer.parseInt(list.get(5));
		this.dmgRatioTorpedo = Integer.parseInt(list.get(6));
		this.dmgRatioAviation = Integer.parseInt(list.get(7));
		this.buffToFirepower = Double.parseDouble(list.get(8));
		this.buffToTorpedo = Double.parseDouble(list.get(9));
		this.buffToAviation = Double.parseDouble(list.get(10));
		this.buffToAA = Double.parseDouble(list.get(11));
		
		this.dmgToHMS = Double.parseDouble(list.get(12));
		this.dmgToUSS = Double.parseDouble(list.get(13));
		this.dmgToIJN = Double.parseDouble(list.get(14));
		this.dmgToKMS = Double.parseDouble(list.get(15));
		this.dmgToROC = Double.parseDouble(list.get(16));
		this.dmgToFFNF = Double.parseDouble(list.get(17));
		this.dmgToMNF = Double.parseDouble(list.get(18));
		this.dmgToSIREN = Double.parseDouble(list.get(19));
		
		this.dmgToDD = Double.parseDouble(list.get(20));
		this.dmgToCL = Double.parseDouble(list.get(21));
		this.dmgToCA = Double.parseDouble(list.get(22));
		this.dmgToLC = Double.parseDouble(list.get(23));
		this.dmgToBC = Double.parseDouble(list.get(24));
		this.dmgToBB = Double.parseDouble(list.get(25));
		this.dmgToAB = Double.parseDouble(list.get(26));
		this.dmgToCVL = Double.parseDouble(list.get(27));
		this.dmgToCV = Double.parseDouble(list.get(28));
		this.dmgToSUB = Double.parseDouble(list.get(29));
		
		this.HEBUFF = Double.parseDouble(list.get(30));
		this.APBUFF = Double.parseDouble(list.get(31));
		this.initialEnhance = Double.parseDouble(list.get(32));
		this.manualEnhance = Double.parseDouble(list.get(33));
		this.injuryByCannon = Double.parseDouble(list.get(34));
		this.injuryByTorpedo = Double.parseDouble(list.get(35));
		this.injuryByAir = Double.parseDouble(list.get(36));
		this.dmgByCannon = Double.parseDouble(list.get(37));
		this.dmgByTorp = Double.parseDouble(list.get(38));
		this.dmgByAir = Double.parseDouble(list.get(39));
		this.comboDamage = Double.parseDouble(list.get(40));
		this.critByCannon = Double.parseDouble(list.get(41));
		this.critByTorp = Double.parseDouble(list.get(42));
		this.critByAir = Double.parseDouble(list.get(43));
		this.salvoBonus = Double.parseDouble(list.get(44));
	}
	
	public String getSkillName() {
		return skillName;
	}
	
	public String getSkillDesc() {
		return skillDesc;
	}
	
	public String getSkillUsers() {
		return skillUsers;
	}
	
	public double getInjureRatio() {
		return injureRatio;
	}
	
	public double getDmgRatio() {
		return dmgRatio;
	}
	
	public int getDmgRatioCannon() {
		return dmgRatioCannon;
	}
	
	public int getDmgRatioTorpedo() {
		return dmgRatioTorpedo;
	}
	
	public int getDmgRatioAviation() {
		return dmgRatioAviation;
	}
	
	public double getBuffToFirepower() {
		return buffToFirepower;
	}
	
	public double getBuffToTorpedo() {
		return buffToTorpedo;
	}
	
	public double getBuffToAviation() {
		return buffToAviation;
	}
	
	public double getBuffToAA() {
		return buffToAA;
	}
	
	public double getDmgToHMS() {
		return dmgToHMS;
	}
	
	public double getDmgToUSS() {
		return dmgToUSS;
	}
	
	public double getDmgToIJN() {
		return dmgToIJN;
	}
	
	public double getDmgToKMS() {
		return dmgToKMS;
	}
	
	public double getDmgToROC() {
		return dmgToROC;
	}
	
	public double getDmgToFFNF() {
		return dmgToFFNF;
	}
	
	public double getDmgToMNF() {
		return dmgToMNF;
	}
	
	public double getDmgToSIREN() {
		return dmgToSIREN;
	}
	
	public double getDmgToDD() {
		return dmgToDD;
	}
	
	public double getDmgToCL() {
		return dmgToCL;
	}
	
	public double getDmgToCA() {
		return dmgToCA;
	}
	
	public double getDmgToLC() {
		return dmgToLC;
	}
	
	public double getDmgToBC() {
		return dmgToBC;
	}
	
	public double getDmgToBB() {
		return dmgToBB;
	}
	
	public double getDmgToAB() {
		return dmgToAB;
	}
	
	public double getDmgToCVL() {
		return dmgToCVL;
	}
	
	public double getDmgToCV() {
		return dmgToCV;
	}
	
	public double getDmgToSUB() {
		return dmgToSUB;
	}
	
	public double getHEBuff() {
		return HEBUFF;
	}
	
	public double getAPBUff() {
		return APBUFF;
	}
	
	public double getInitialEnhance() {
		return initialEnhance;
	}
	
	public double getManualEnhance() {
		return manualEnhance;
	}
	
	public double getInjuryByCannon() {
		return injuryByCannon;
	}
	
	public double getInjuryByTorpedo() {
		return injuryByTorpedo;
	}
	
	public double getInjuryByAir() {
		return injuryByAir;
	}
	
	public double getDmgByCannon() {
		return dmgByCannon;
	}
	
	public double getDmgByTorpedo() {
		return dmgByTorp;
	}
	
	public double getDmgByAir() {
		return dmgByAir;
	}
	
	public double getComboDamage() {
		return comboDamage;
	}
	
	public double getCritByCannon() {
		return critByCannon;
	}
	
	public double getCritByTorpedo() {
		return critByTorp;
	}
	
	public double getCritByAir() {
		return critByAir;
	}
	
	public double getSalvoBonus() {
		return salvoBonus;
	}
	
	
	
}
