
public class Jaffa extends Character {
	//Jaffa konstruktora, ugyanolyan parameterekkel lehet letrehozni, mint a generalt
	public Jaffa(AbstractBlock pos, Direction dir,Boolean hasbox){
		PosBlock = pos;
		direction = dir;
		PosBlock.setPassable(false);
		PosBlock.setShootable(false);
		
		if(hasbox)box = new Box(1);
	}
	public Jaffa(){}
}
