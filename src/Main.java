import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.stream.JsonReader;

public class Main {
	//create array to hold vehicles
	static ArrayList<Vehicle> vehiclesArray = new ArrayList<>();
	
	public static void main(String[] args) {
		//find the vehicles
		GetVehicles();
		
		//now we have an array of the objects do the four tasks.
		//task 1
		PrintVehiclesPriceAsc(vehiclesArray);
		
		//space between tasks
		System.out.println();
		
		//task 2
		PrintSIPP(vehiclesArray);
		System.out.println();
		
		//task 3
		PrintHighestRaterPerCarType(vehiclesArray);
		System.out.println();

		//task 4
		PrintScores(vehiclesArray);

		//part 2: do the same with a REST API
		
	}
	
	private static void GetVehicles(){
		try {
			JsonReader reader = new JsonReader(new FileReader("assets/vehicles.json"));
			
			//move through the file to find the array
			reader.beginObject();
			reader.nextName();		
			reader.beginObject();
			reader.nextName();
			reader.beginArray();

			while(reader.hasNext()){

				//get the values to create a new vegicle object
				reader.beginObject();
				reader.nextName();
				String sipp = reader.nextString();
				reader.nextName();
				String name = reader.nextString();
				reader.nextName();
				Double price = reader.nextDouble();
				reader.nextName();
				String supplier = reader.nextString();
				reader.nextName();
				Double rating = reader.nextDouble();
				reader.endObject();

				Vehicle vehicle = new Vehicle(sipp, name, price, supplier, rating);

				//add these vehicles to the array
				vehiclesArray.add(vehicle);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	private static void PrintVehiclesPriceAsc(ArrayList<Vehicle> vehicles) {
		//task 1: list of vehicles in ascending price value
		//first reorder the array
		Collections.sort(vehicles, new Comparator<Vehicle>(){
			public int compare(Vehicle vehicle1, Vehicle vehicle2) {
				if (vehicle1.GetPrice() >= vehicle2.GetPrice()) {
					return +1;
				}
				else {
					return -1;
				}
			}
		});
		
		//print out the header
		System.out.println("Vehicles in ascending price value:");
		
		//loop through array and print in the correct order
		// code below used for printing to console
		for (int i = 0; i < vehicles.size(); i++) {
			System.out.println(vehicles.get(i).GetName() + " - " + vehicles.get(i).GetPrice());
		}
	}
	
	private static void PrintSIPP(ArrayList<Vehicle> vehicles) {
		//task 2: print out specifications
		//first print the header
		System.out.println("Vehicle Speicifications");
		//loop through array
		for(int i=0; i < vehicles.size(); i++) {
			//check the sipp values for this vehicle
			ConvertSIPP sipp = new ConvertSIPP(vehicles.get(i).GetSipp());
			//print out specifications
		
			//check for errors first for records to omit
			if(vehicles.get(i).GetName().equals("SIPP Error") || 
		       vehicles.get(i).GetSipp().equals("SIPP Error") || 
		       sipp.GetCarType().equals("SIPP Error") ||
			   sipp.GetDoors().equals("SIPP Error") ||
			   sipp.GetTransmission().equals("SIPP Error") ||
			   sipp.GetFuel().equals("SIPP Error") ||
			   sipp.GetAirCon().equals("SIPP Error")) {
			}
			else {
					System.out.println(vehicles.get(i).GetName() + " - " +
									   vehicles.get(i).GetSipp() + " - " +
									   sipp.GetCarType() + " - " +
									   sipp.GetDoors() + " - " +
									   sipp.GetTransmission() + " - " +
									   sipp.GetFuel() + " - " +
									   sipp.GetAirCon());
			}
		}
	}
	
	private static void PrintHighestRaterPerCarType(ArrayList<Vehicle> vehicles) {
		//order each car by name for comparison
		Collections.sort(vehicles, new Comparator<Vehicle>(){
			public int compare(Vehicle vehicle1, Vehicle vehicle2) {
		        return vehicle1.GetName().compareTo(vehicle2.GetName());
		    }
		});
		
		//for each car name check which has the highest score then add to an array
		ArrayList<Vehicle> highestScores = new ArrayList<>();
		
		//loop through array
		for(int i=0; i < vehicles.size(); i++) {
			//check the sipp values for this vehicle
			ConvertSIPP sipp = new ConvertSIPP(vehicles.get(i).GetSipp());
			//associate the car type with this object
			vehicles.get(i).SetCartype(sipp.GetCarType());
			//add at this point as we will compare later
			highestScores.add(vehicles.get(i));
			for (int j=0; j < highestScores.size(); j++){
				//associate the car type with the comparison
				ConvertSIPP compsipp = new ConvertSIPP(highestScores.get(j).GetSipp());
				highestScores.get(j).SetCartype(compsipp.GetCarType());
				if(vehicles.get(i).GetName().equals(highestScores.get(j).GetName()) && vehicles.get(i).GetCartype().equals(highestScores.get(j).GetCartype())){
					if(vehicles.get(i).GetRating() > highestScores.get(j).GetRating()) {
						//if the name and car type is the same then compare the ratings
						//only display the highest rating
						//at this point it is clear the vehicle in vehicles has a higher score than the one in highestScores so remove the highestScores
						highestScores.remove(j);
					}
					else if(vehicles.get(i).GetRating() < highestScores.get(j).GetRating()){
						highestScores.remove(highestScores.size()-1);
					}
				}
			}
		}

		//sort the highest scorers by descending ratings
		Collections.sort(highestScores, new Comparator<Vehicle>(){
			public int compare(Vehicle vehicle1, Vehicle vehicle2) {
				if (vehicle1.GetRating() >= vehicle2.GetRating()) {
					return -1;
				}
				else {
					return +1;
				}
			}
		});

		//print the header
		System.out.println("Highest rated supplier per car type");
		//display the highest scores
		for(int i=0; i < highestScores.size(); i++) {
			System.out.println(highestScores.get(i).GetName() + " - " +
					highestScores.get(i).GetCartype() + " - " +
					highestScores.get(i).GetSupplier() + " - " +
					highestScores.get(i).GetRating());
		}
	}

	private static void PrintScores(ArrayList<Vehicle> vehicles){
		//print the header
		System.out.println("Highest scoring vehicles");

		//first loop through the array and set the scores for each vehicle
		for(int i=0; i < vehicles.size(); i++) {
			ConvertSIPP sipp = new ConvertSIPP(vehicles.get(i).GetSipp());
			vehicles.get(i).SetScore(sipp.score);
		}

		//now order the array
		Collections.sort(vehicles, new Comparator<Vehicle>(){
			public int compare(Vehicle vehicle1, Vehicle vehicle2) {
				if (vehicle1.GetFinalScore() >= vehicle2.GetFinalScore()) {
					return -1;
				}
				else {
					return +1;
				}
			}
		});

		//finally loop through and print the array
		for(int i=0; i < vehicles.size(); i++) {
			System.out.println(vehicles.get(i).GetName() + " - " +
							   vehicles.get(i).GetScore() + " - " +
							   vehicles.get(i).GetRating() + " - " +
							   vehicles.get(i).GetFinalScore());
		}
	}
}

