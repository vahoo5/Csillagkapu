//A szakadek osztalyt valositja meg, az AbstractBlock-bol oroklodik.
class Canyon extends AbstractBlock {
	
	//Konstruktor.
	public Canyon(int id,int[] n){
		super(id,n);
		passable = true;
	}
	
	//Ha szakadekba lepunk, akkor az ezredes eletet veszit.
	//Ha nincs vege a jateknak, akkor beallitja az ezredest a start-pozicioba.
	@Override
	public void moveToThisBlock() {
		System.out.println("Canyon.moveToThisBlock()");
		Application.general.setPosBlock(this);
		Application.general.die();
		Field start = Application.maze.getStartField();
		Application.general.setPosBlock(start);
	}
	
	//Erre a blokkra nem tudunk raloni, igy nem csinal a fuggveny semmit, ha meghivjak.
	@Override
	public void shootOnThisBlock(Color col, Direction dir) {
		System.out.println("Canyon.shootOnThisBlock("+col+","+dir+")");
	}
	
	//Nem szukseges a jelzes neki, igy nem csinal semmit.
	@Override
	public void notifyBlock() {
		System.out.println("Canyon.notifyBlock()");
	}
	
	//Null erteket ad vissza, hisz nem lehet rajta doboz.
	@Override
	public Box getBox() {
		System.out.println("Canyon.getBox()");
		return null;
	}
	
	//Athaladhatosag lekerdezese.
	@Override
	public boolean isPassable(){
		System.out.println("Canyon.isPassable()");
		return true;
	}
	
	//Doboz lerakasa es megsemmisitese.
	@Override
	public void setBox(Box box){
		System.out.println("Canyon.setBox("+box.getClass()+")");
		box = null;
	}
	
}