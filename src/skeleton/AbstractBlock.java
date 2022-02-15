//AbstractBlock osztaly megvalositasa.
//Alap epitoelem, ebbol szarmazik a Field, Wall, Canyon es Door osztaly.
//Nem lehet peldanyositani.
public abstract class AbstractBlock {
	//Az elem indexenek, szomszedjainak es athaladhatosaganak tarolasa.
	protected int index;
	protected int[] neighbourIndexes = new int[4];
	protected boolean passable =true;
	
	//Az AbstractBlock konstruktorai.
	public AbstractBlock(){}
	public AbstractBlock(int id,int[] neighbours){
		this.index = id;
		this.neighbourIndexes=neighbours;
	}
	
	//Visszaadja az adott iranyu szomszedjat.
	public AbstractBlock getNeighbour(Direction dir){
		System.out.println("AbstractBlock.getNeighbour("+dir+")");
			AbstractBlock neighbour = null;
			switch(dir){
			case NORTH: neighbour= Application.maze.getBlock(neighbourIndexes[0]);break;
			case SOUTH: neighbour= Application.maze.getBlock(neighbourIndexes[1]);break;
			case EAST: neighbour= Application.maze.getBlock(neighbourIndexes[2]);break;
			case WEST: neighbour= Application.maze.getBlock(neighbourIndexes[3]);break;
			}
			return neighbour;
	}
	
	//Visszaadja az adott blokk indexet.
	public int getIndex(){
		System.out.println("AbstractBlock.getIndex()");
		return index;
	}
	
	//Visszaadja, hogy at lehet-e rajta jutni. Lovedek vagy ezredes.
	public boolean isPassable(){
		System.out.println("AbstractBlock.IsPassable()");
		return passable;
	}
	
	//Beallitjuk, az athaladhatosagat.
	public void setPassable(boolean tf){
		System.out.println("AbstractBlock.setPassable("+tf+")");
		passable = tf;
	}
	
	//Abstract fuggvenyek:
	public abstract void moveToThisBlock();
	public abstract void shootOnThisBlock(Color col,Direction dir);
	//notify() nev eseten hibat dob a fordito.
	public abstract void notifyBlock();
	public Box getBox(){
		return null;
	}
	public void setBox(Box box){
	}
}