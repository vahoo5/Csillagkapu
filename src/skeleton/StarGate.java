//A csillagkapu osztaly megvalositasa, a falbol oroklodik.
public class StarGate extends Wall {
	//Irany, szin, van-e csillagkapu parja meghatarozasa.
	private Direction direction;
	private Color color;
	private boolean isPaired;
	
	public StarGate(int index,int[] neighbours,Direction dir,Color color,boolean isPaired){
		super(index,neighbours);
		this.direction = dir;
		this.color = color;
		this.isPaired = isPaired;
		if (isPaired){
			passable=true;
		}
		else{
			passable=false;
		}
	}
	
	//Csillagkapu iranyanak atkonvertalasa integer-re.
	private int dirToInt(Direction dir){
		if(dir == Direction.EAST)
			return 0;
		else if(dir == Direction.WEST)
			return 2;
		else if(dir == Direction.NORTH)
			return 1;
		else 
			return 3;
	}
	
	//Konstruktor, beallitja az iranyt, szint es indexet.
	public StarGate(int index,int[] neighbours,Direction dir,Color col){
		super(index,neighbours,true);  //**specialis falkent inicalizalodik
		this.direction=dir;
		this.color=col;
	}
	
	//Visszaadja, hogy van-e parja.
	public boolean getIsPaired(){
		System.out.println("StarGate.getIsPaired()");
		return isPaired;
	}
	
	//Visszaadja az iranyat.
	public Direction getDirection(){
		System.out.println("StarGate.getDirection()");
		return direction;
	}
	
	//Visszaadja a szinet.
	public Color getColor(){
		System.out.println("StarGate.getColor()");
		return color;
	}

	//Ralepes esemeny kezelese.
	@Override
	public void moveToThisBlock(){
		//Ha van parja, megkeressuk.
		//Ha nincs, akkor nem csinalunk semmit.
		System.out.println("StarGate.moveToThisBlock()");
		if(isPaired){
			StarGate pair = null;
			if(color == Color.BLUE) {
				//Sarga par.
				pair = Application.maze.getYellowStarGate();
			}
			else{
				//Kek par.
				pair = Application.maze.getBlueStarGate();
			}
			//Lekeri a parjanak az iranyat es az ott levo szomszedot.
			Direction dir = pair.getDirection();
			AbstractBlock neighbour = pair.getNeighbour(dir);	
			//Atlepunk arra a blockra es beallitjuk az ezredes iranyat.
			Application.general.setPosBlock(neighbour);
			Application.general.setDirection(dir);
		}
	}
	
	//Loves esemeny kezelese.
	@Override
	public void shootOnThisBlock(Color bulletcolor,Direction dir){
		System.out.println("StarGate.shootOnThisBlock("+bulletcolor+","+dir+")");
		//Ha kulonbozo szinu a letezo csillagkapu, amire lovunk, mint a lovedek.
		if (bulletcolor != this.color){
			//Regi csillagkapu torlese.
			Application.maze.deleteStarGate(this.color);
			//Uj csillagkapu letrehozasa a regi helyen.
			Application.maze.createStarGate(this,bulletcolor,Application.maze.oppDir(dir));
		}
	}
	
	//A csillagkapu parjanak beallitasa.
	public void setPair(int index, Direction dir){
		neighbourIndexes[dirToInt(dir)] = index;
	}
}