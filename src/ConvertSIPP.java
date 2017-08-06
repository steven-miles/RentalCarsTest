
public class ConvertSIPP {
	private String carType, doors, transmission, fuel, aircon;
	public int score;

	public ConvertSIPP(String sipp) {
		carType = ConvertCarType(String.valueOf(sipp.charAt(0)));
		doors = ConvertDoors(String.valueOf(sipp.charAt(1)));
		transmission = ConvertTransmission(String.valueOf(sipp.charAt(2)));
		fuel = ConvertFuel(String.valueOf(sipp.charAt(3)));
		aircon = ConvertAirCon(String.valueOf(sipp.charAt(3)));
		score = GetScore();
	}
	
	public String ConvertCarType(String carTypeTest) {
		switch(carTypeTest) {
			case "M": return "Mini";
			case "E": return "Economy";
			case "C": return "Compact";
			case "I": return "Intermediate";
			case "S": return "Standard";
			case "F": return "Full Size";
			case "P": return "Premium";
			case "L": return "Luxuary";
			case "X": return "Special";
			default: return "SIPP Error";
		}
	}
	
	public String ConvertDoors(String doorsTest) {
		switch(doorsTest) {
			case "B": return "2 doors";
			case "C": return "4 doors";
			case "D": return "5 doors";
			case "W": return "Estate";
			case "T": return "Convertible";
			case "F": return "SUV";
			case "P": return "Pick up";
			case "V": return "Passenger van";
			default: return "SIPP Error";
		}
	}
	
	public String ConvertTransmission(String transmissionTest) {
		switch(transmissionTest) {
			case "M": return "Manual";
			case "A": return "Automatic";
			default: return "SIPP Error";
		}
	}
	
	public String ConvertFuel(String fuelTest) {
		switch(fuelTest) {
			case "N": return "Petrol";
			case "R": return "Petrol";
			default: return "SIPP Error";
		}
	}
	
	public String ConvertAirCon(String fuelTest) {
		switch(fuelTest) {
			case "N": return "No air conditioning";
			case "R": return "Air conditioning";
			default: return "SIPP Error";
		}
	}
	
	public String GetCarType() {
		return carType;
	}
	
	public String GetDoors() {
		return doors;
	}
	
	public String GetTransmission() {
		return transmission;
	}
	
	public String GetFuel() {
		return fuel;
	}
	
	public String GetAirCon() {
		return aircon;
	}

	private int GetScore(){
		int tempScore = 0;
		if(transmission.equals("Manual")) {
			tempScore+=1;
		}
		if(transmission.equals("Automatic")) {
			tempScore+=5;
		}
		if(aircon.equals("Air conditioning")) {
			tempScore+=2;
		}
		return tempScore;
	}
}
