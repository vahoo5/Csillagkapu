//Ajto osztaly megvalositasa, AbstractBlock-bol oroklodik.
public class Door extends AbstractBlock {
	//Konstruktor.
	public Door(int index,int[]neighbours){
		super(index,neighbours);
		passable=false;
	}
	
	//Nyitas fuggveny, athaladhatosag beallitasra kerul.
	public void Open(boolean tf){
		System.out.println("Door.Open("+tf+")");
		passable=tf;
	}
	
	//Ralepes esemeny, ha athaladhatosag engedelyezve van, a pozicio erre az elemre valtozik.
	@Override
	public void moveToThisBlock() {
		if(passable==true)	
			Application.general.setPosBlock(this);	
		System.out.println("Door.moveToThisBlock()");
	}
	
	//Loves kezelese az ajtonal.
	@Override
	public void shootOnThisBlock(Color col, Direction dir) {		
		System.out.println("Door.shootOnThisBlock("+col+","+dir+")");
	}
	
	//Nem szukseges a jelzes neki, igy nem csinal semmit.
	@Override
	public void notifyBlock() {		
		System.out.println("Door.notifyBlock()");
	}
	
	//getBox() megvalositasa; de ajton nem lehet doboz, tehat felvenni sem lehet.
	@Override
	public Box getBox() {
		return null;
	}
	
	
	@Override
	public void setBox(Box box){
	}
	
}