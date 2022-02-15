//Mezo osztaly megvalositasa, AbstractBlockbol oroklodik.
public class Field extends AbstractBlock {
	//Letaroljuk, tartalmaz-e ZPM modult vagy dobozt.
	protected boolean containsZpm = false;
	protected boolean containsBox = false;
	protected Box box;
	
	//Konstruktor.
	public Field(int id,int[] neighbours){
		super(id,neighbours);
		 passable=true;
	}
	
	//Parameterezett konstruktor.
	public Field(int id,int[] neighbours,boolean hasBox,boolean hasZpm){
		super(id,neighbours);
		//Ha van a mezon doboz, akkor nem athaladhato.
		if(hasBox){
			this.containsBox = true;
			passable = false;
			box = new Box();
		}
		//Ha nincs a mezon doboz, akkor athaladhato.
		else{
			passable = true;
		}
		//Zpm van a mezon.
		if(hasZpm){
			this.containsZpm = true;
		}
		
	}
	
	//Visszaadja van-e rajta doboz.
	public boolean getContainsBox(){
		System.out.println("Field.getContainsBox()");
		return containsBox;
	}
	
	//Visszaadja van-e rajta ZPM modul.
	public boolean getContainsZpm(){
		System.out.println("Field.getContainsZpm()");
		return containsZpm;
	}
	
	//Visszaadja a rajta tarolt dobozt.
	@Override
	public Box getBox(){
		System.out.println("Field.getBox()");
		return this.box;
	}
	
	//Rarak egy dobozt a mezore.
	@Override
	public void setBox(Box box){
		System.out.println("Field.setBox(box)");
		this.box = box;
	}
	
	//Visszaadja az athaladhatosagot.
	@Override
	public boolean isPassable(){
		System.out.println("Field.isPassable()");
		return passable;
	}
	
	//Az ezredes ralep, ha nincs rajta doboz es osszegyujti a ZPM-et, ha van rajta.
	@Override
	public void moveToThisBlock() {
		System.out.println("Field.moveToThisBlock()");
		if (containsBox == false){
			Application.general.setPosBlock(this);
			if(containsZpm == true){
				Application.general.collectZpm();
				containsZpm = false;
			}
		}
	}
	
	//Lovesnel megadja a lovedek helyzetet, iranyat, szinet.
	@Override
	public void shootOnThisBlock(Color col, Direction dir) {
		System.out.println(this.getClass()+" shootOnThisBlock("+col+","+dir+")");
	}

	//Nem szukseges a jelzes neki, igy nem csinal semmit.
	@Override
	public void notifyBlock() {
		System.out.println("Field.notifyBlock()");
	}
}