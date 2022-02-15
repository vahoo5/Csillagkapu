//Merleg osztaly megvalositasa, a mezobol oroklodik.
public class Scale extends Field {
	//A merleghez tartozo ajto.
	private Door doorToOpen;
	
	//Konstruktor.
	public Scale(int id,int[] n){
		super(id,n);
		passable=true;
	}
	
	//Konstruktor. DoorToOpen-t allitja.
	public Scale(int id,int[] n,Door d,boolean hasBox,boolean hasZpm){
		super(id,n,hasBox,hasZpm);
		this.doorToOpen=d;
		if(hasBox){
			passable=false;
			box = new Box();
		}
	}
	
	//Az ezredes ralep, ha nincs rajta doboz es osszegyujti a ZPM-et, ha van rajta. Ajtot nyit.
	@Override
	public void moveToThisBlock(){
		System.out.println("Scale.moveToThisBlock()");
		if(containsBox == false){
			Application.general.setPosBlock(this);
			doorToOpen.Open(true);
			if(containsZpm == true){
				Application.general.collectZpm();
				containsZpm = false;
			}
		}
	}
	
	//Doboz merlegre rakasa, ajto nyitasa.
	@Override
	public void setBox(Box box){
		System.out.println("Scale.setBox(box)");
		this.box=box;
		doorToOpen.Open(true);
	}
	
	//Doboz merlegrol levetele, ajto zarasa.
	@Override
	public Box getBox(){
		System.out.println("Scale.getBox()");
		doorToOpen.Open(false);
		return box;
	}
	
	//Lelepes esemeny ertesitesere. Ajtot bezarja.
	@Override
	public void notifyBlock(){
		System.out.println("Scale.notifyBlock()");
		doorToOpen.Open(false);
	}
}