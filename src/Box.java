import java.io.Serializable;

//A doboz osztalyt valositja meg.
public class Box implements Serializable{
	//Doboz sulya.
	private int weight;

	//A doboz sulyanak beallitasa.
	public Box(int weight) {
		this.weight = weight;
	}

	//Megadja a doboz sulyat.
	public int getWeight() {
		return weight;
	}
}
