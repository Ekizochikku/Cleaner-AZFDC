import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OnlyCarriers {
	
	private ArrayList<String> sp = new ArrayList<String>();
	private String theFile;
	private String shipName;
	
	public OnlyCarriers(String fileName, String shipName) throws IOException {
		this.theFile = fileName;
		this.shipName = shipName;
		getFileParams();
	}
	
	public void getFileParams() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(theFile));
		String line = br.readLine(); //Skip Header Line
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String[] fields = line.split("	");
			if (fields[0].equals(shipName)) {
				for (int i = 0; i < fields.length; i++) {
					sp.add(fields[i]);
				}
			}
			
		}
		br.close();
	}
	
	public String getShipName() {
		return sp.get(0);
	}
	
	public String getFaction() {
		return sp.get(1);
	}
	
	public String getShipClass() {
		return sp.get(2);
	}
	
	public String getShipType() {
		return sp.get(3);
	}
	
	public double getWepOneType() {
		return Double.parseDouble(sp.get(4));
	}
	
	public double getWepTwoType() {
		return Double.parseDouble(sp.get(5));
	}
	
	public double getWepThreeType() {
		return Double.parseDouble(sp.get(6));
	}
	
	
	public double getSlotOneEff() {
		return Double.parseDouble(sp.get(7));
	}
	
	
	public double getSlotTwoEff() {
		return Double.parseDouble(sp.get(8));
	}
	
	
	public double getSlotThreeEff() {
		return Double.parseDouble(sp.get(9));
	}
	
	
	public double getHealth() {
		return Double.parseDouble(sp.get(10));
	}
	
	
	public double getFirepower() {
		return Double.parseDouble(sp.get(11));
	}
	
	
	public double getTorpedo() {
		return Double.parseDouble(sp.get(12));
	}
	
	
	public double getAA() {
		return Double.parseDouble(sp.get(13));
	}
	
	public double getAviation() {
		return Double.parseDouble(sp.get(14));
	}
	
}
