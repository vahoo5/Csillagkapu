import java.awt.Graphics;
import java.io.IOException;

//Ajto osztaly megvalositasa, AbstractBlock-bol oroklodik.
public class Door extends AbstractBlock {
	//Konstruktor.
	public Door(int index,int[]neighbours){
		super(index,neighbours);
		//Nem athaladhato.
		passable=false;
		shootable = false;
	}
	
	//Nyitas fuggveny, athaladhatosag beallitasra kerul.
	public void Open(boolean tf){
		if(tf == true){
			System.out.println("OPEN DOOR(ID="+index+")");
			Application.log.println("OPEN DOOR(ID="+index+")");
		}
		else {
			System.out.println("CLOSE DOOR(ID="+index+")");
			Application.log.println("CLOSE DOOR(ID="+index+")");
		}
		passable=tf;
		shootable=tf;
	}
	
	//Ralepes esemeny, ha athaladhatosag engedelyezve van, a pozicio erre az elemre valtozik.
	@Override
	public void moveToThisBlock(Character c) {
		c.setPosBlock(this);	
	}
	
	//Loves kezelese az ajtonal.
	@Override
	public void shootOnThisBlock(Color col, Direction dir) {		
		if(Application.replicator != null && this==Application.replicator.PosBlock){
			Application.replicator.stop();
			Application.replicator = null;
			Application.app.replicator_null = true;
			shootable = true;
			System.out.println("BULLET HIT REPLICATOR\nREPLICATOR DISAPPEARS");
			Application.log.println("BULLET HIT REPLICATOR\nREPLICATOR DISAPPEARS");
			Application.app.getGamePanel().repaint();
		}else{
		System.out.println(col+" BULLET HIT DOOR(ID="+index+")");
		Application.log.println(col+" BULLET HIT DOOR(ID="+index+")");
		}
	}
	
	//Nem szukseges a jelzes neki, igy nem csinal semmit.
	@Override
	public void notifyBlock() {		
	}
	
	//Ha a replikator ralep, akkor jelzi.
	@Override
	public void moveToThisBlock(Replicator r) {
	}
	
	@Override
	public String toString() {
		String s = super.toString();
		if(passable) s+= " OPEN";
		else s += " CLOSED";
		return s;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		
		if(!passable)g.setColor(new java.awt.Color(165,113,78));
		else g.setColor(new java.awt.Color(185,135,100));
		
		GamePanel p = Application.app.getGamePanel();
		int rectWidth = p.rectWidth;
		int rectHeight = p.rectHeight;
		int marginV = p.marginV;
		int marginH = p.marginH;
		
		int x = index % 10; //oszlopindex
		int y = index /10;  //sorindex
		
		g.fillRect(marginV + (x * rectWidth), marginH + (y * rectHeight), rectWidth, rectHeight);
	
		
	}	
}