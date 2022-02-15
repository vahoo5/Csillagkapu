//Fal osztaly megvalositasa, AbstractBlockbol oroklodik.
public class Wall extends AbstractBlock {
	//Specialis fal-e, melyre love csillagkapu alakul ki.
	private boolean isSpecial;
	
	//Konstruktor.
	public Wall(int id,int[] n){
		super(id,n);
	}
	
	//Konstruktor, beallitja az isSpecial-t, athaladhatosagot es az index erteket.
	public Wall(int id,int[] neighbours,boolean isSpecial){
		super(id,neighbours);
		this.isSpecial=isSpecial;
		this.index = id;
		passable = false;
	}
	
	//Visszaadja a szomszedokat.
	public int[] getNeighboursIndex(){
		return neighbourIndexes;
	}
	
	//Falra nem lehet lepni.
	@Override
	public void moveToThisBlock() {
		System.out.println("Wall.moveToThisBlock()");
	}
	
	//Specialis fal eseten raloves kezelese.
	@Override
	public void shootOnThisBlock(Color col, Direction dir) {
		System.out.println("Wall.shootOnThisBlock("+col+","+dir+")");
			if (isSpecial){
			Application.maze.createStarGate(this, col, Application.maze.oppDir(dir));
		}
	}
	
	//Nem szukseges a jelzes neki, igy nem csinal semmit.
	@Override
	public void notifyBlock() {
		System.out.println("Wall.notifyBlock()");
	}
	
	//Null erteket ad vissza, hisz nem lehet rajta doboz.
	@Override
	public Box getBox() {
		return null;
	}
	
	public void setBox(Box box){
	}
}