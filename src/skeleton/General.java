//Az ezredes osztalyanak megvalositasa.
//Az ezredest tudja iranyitani a jatekos a palyan.
import java.util.ArrayList;

public class General {
	//Tarolja az ezredes kezdo blockjat, iranyat, eleteinek szamat, nala levo ZPM-ek, illetve a palyan levo osszes ZPM szamat es a dobozokat.
	private AbstractBlock PosBlock;
	private Direction direction;
	private int lives = 3;
	private int ZPMCnt = 0;
	private int allZPMCnt;
	private ArrayList<Box> boxes;
	
	//A General konstruktora.
		//3 az alapbeallitott eletek szama, beallitja, hogy nincs nala ZPM, se doboz.
	public General(){
		boxes = new ArrayList<Box>();
	}
	
	//Konstruktor. Az ezredesnel levo dobozokat tarolja.
	public General(ArrayList<Box> boxes){
		this.boxes = new ArrayList<Box>(boxes);
	}
	
	//Visszaadja az ezredes poziciojat.
	public AbstractBlock getPosBlock(){ 
		System.out.println("General.getPosBlock()");
		return PosBlock;
	}
	
	//A kapott iranyba allitja az ezredes iranyat.
	public void setDirection(Direction dir){
		System.out.println("General.setDirection("+ dir +")");
		this.direction = dir;
	}
	
	//Visszaadja az ezredes iranyat.
	public Direction getDirection(){
		System.out.println("General.getDirection()");
		return direction;
	}
	
	//Beallitja az ezredes poziciojat a kapott blokkra.
	public void setPosBlock(AbstractBlock block){
		System.out.println("General.setPosBlock(block)");
		PosBlock = block;
	}
	
	//Ha meghivjak, akkor az ezredes ZPM szamlaloja megno eggyel.
	//(az osztalydiagrammal ellentetben nem kell megkapja magat a modult)
	public void collectZpm(){ 
		System.out.println("General.collectZPM()");		
		ZPMCnt++;
		
		//Itt megnezzuk, hogy felszedtuk-e az osszes ZPM-et.
		//Ha igen, akkor jelezzuk az Applicationnek, hogy nyertunk.
		if(ZPMCnt == allZPMCnt)
			Application.endGame("WIN!");
	}
	
	//Ha meghivjak, akkor meghal az ezredes.
	public void die(){
		//Megnezi, hogy van e meg vesztheto elete, ha nincs jelezzuk, hogy vesztett a jatekos.
		if(lives ==	0)
			Application.endGame("LOSE!");
		else
			lives--;
		System.out.println("General.die()");

	}
	
	//Visszaadja, hogy van-e az ezredesnel meg dobboz.
	public boolean hasBox(){
		System.out.println("General.hasBox()");
		return !boxes.isEmpty();
	}
	
	//Visszaadja az utoljara felvett dobozt.
	public Box getBox(){
		System.out.println("General.getBox()");
		return boxes.get(boxes.size() - 1);
	}
	
	//Az ezredest ellepteti az adott iranyba, ha lehet.
	public void move(Direction dir){
		System.out.println("General.move("+ dir +")");
		direction = dir;
			AbstractBlock block = PosBlock.getNeighbour(dir);
			//Ha ra lehet lepni, akkor beallitjuk a pozicionak.
			//Az eddigi blokkot pedig ertesitjuk, hogy elleptunk rola.
			if(block.isPassable()){
				PosBlock.notifyBlock();
				block.moveToThisBlock();
		}
	}
	
	//Felveszi a dobozt, ha van elotte.
	public void pick(){
		System.out.println("General.pick()");
		AbstractBlock b = PosBlock.getNeighbour(direction);
		//Leellenorizzuk, hogy visszaad-e dobozt. Ha igen akkor felvesszuk.
		if(b.getBox() != null){
				boxes.add(b.getBox());
			}	
	}
	
	//Lerakja maga ele a dobozt, ha letudja.
	public void drop(){
		System.out.println("General.drop()");

		if(PosBlock.getNeighbour(direction).isPassable()){
			AbstractBlock f = PosBlock.getNeighbour(direction);
			//Megnezzuk, hogy van-e rajta doboz. 
				if(hasBox())f.setBox(getBox());
		}
	}
	
	//Kapott szinu lovedeket lo.
	public void shoot(Color col){
		System.out.println("General.shoot("+col+")");
		
		AbstractBlock bulletPos;
		
		for( bulletPos = PosBlock.getNeighbour(direction); bulletPos.isPassable(); bulletPos = bulletPos.getNeighbour(direction)){
			//Elmegy a lovedek, ameddig nem utkozik valamibe.
		}
		//A blokk, amibe megakadt a lovedek lekezeli, hogy ralottek.
		bulletPos.shootOnThisBlock(col, direction);
	}
	
	//A palyan levo osszes Zpm szamanak megadasa.
	public void setallZpmCnt(int i) {
		this.allZPMCnt=i;
	}
}