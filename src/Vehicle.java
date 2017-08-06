
public class Vehicle {
	private String sipp;
	private String name;
	private Double price;
	private String supplier;
	private Double rating;
	private String cartype;
	private int score;
	private Double finalScore;
	
	public Vehicle(String sipp, String name, Double price, String supplier, Double rating) {
		this.sipp = sipp;
		this.name = name;
        this.price = price;
        this.supplier = supplier;
        this.rating = rating;
	}
	
	public String GetSipp() {
		return sipp;
	}
	
	public String GetName() {
		return name;
	}
	
	public Double GetPrice() {
		return price;
	}
	
	public String GetSupplier() {
		return supplier;
	}
	
	public Double GetRating() {
		return rating;
	}

	public String GetCartype() {
		return cartype;
	}

	public void SetCartype(String cartype) {
		this.cartype = cartype;
	}

	public int GetScore() { return score; }

	public void SetScore(int score){
		this.score = score;
		SetFinalScore();
	}

	public void SetFinalScore(){
		finalScore = score + rating;
	}

	public Double GetFinalScore() { return finalScore; }
}
