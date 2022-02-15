import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Mezo osztaly megvalositasa, AbstractBlockbol oroklodik.
public class Field extends AbstractBlock {
	//Letaroljuk, tartalmaz-e ZPM modult vagy dobozt.
	protected boolean containsZpm = false;
	protected boolean containsBox = false;
	protected List<Box> boxes = new ArrayList<Box>();
	
	//Konstruktor
	public Field(int id,int[] neighbours){
		super(id,neighbours);
		//Passable beallitasa.
		 passable=true;
	}
	
	//Parameterezett konstruktor.
	public Field(int id,int[] neighbours,boolean hasBox,boolean hasZpm){
		super(id,neighbours);
		//Ha van a mezon doboz, akkor nem athaladhato.
		if(hasBox){
			this.containsBox = true;
			passable = false;
			shootable = false;
			boxes.add(new Box(2));
		}
		//Ha nincs a mezon doboz, akkor athaladhato.
		else{
			passable = true;
			shootable = true;
		}
		//Zpm van a mezon.
		if(hasZpm){
			this.containsZpm = true;
		}
		
	}
	
	//Visszaadja van-e rajta doboz.
	public boolean getContainsBox(){
		if(!containsBox){
			System.out.println("NO BOX ON NEIGHBOUR");
			Application.log.println("NO BOX ON NEIGHBOUR");
		}
		return containsBox;
	}
	
	//Visszaadja van-e rajta ZPM modul.
	public boolean getContainsZpm(){
		return containsZpm;
	}
	
	//Visszaadja a rajta tarolt dobozt.
	@Override
	public Box getBox(){
		//Ha van rajta egy doboz, amit felvesznek, akkor passable es shootable lesz.
		if(getContainsBox()){
			if(boxes.size() == 1){
				passable = true;
				shootable = true;
				containsBox = false;
				System.out.println("COLLECTBOX");
				Application.log.println("COLLECTBOX");
			}
			return boxes.remove(boxes.size()-1);	//legfelso doboz visszaadasa es torlese a mezorol
		}
		return null;
	}
	
	//Rarak egy dobozt a mezore.
	@Override
	public void setBox(Box box){
		boxes.add(box);
		//Nem passable, shootable ha van a mezon doboz.
		passable = false;
		shootable = false;
		containsBox = true;

	}
	public void putZpm(){
		this.containsZpm = true;
		System.out.println("NEW ZPM CREATED AT:"+this.getClass().getName().toUpperCase()+"(ID="+index+")");
		Application.log.println("NEW ZPM CREATED AT:"+this.getClass().getName().toUpperCase()+"(ID="+index+")");
	}
	
	//A karakter ralep, ha nincs rajta doboz es osszegyujti a ZPM-et, ha van rajta.
	@Override
	public void moveToThisBlock(Character c) {
			c.setPosBlock(this);
			if(containsZpm == true){
				c.collectZpm();
				containsZpm = false;
		}
	}
	
	//Lovesnel megadja a lovedek helyzetet, iranyat, szinet.
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
		}
		else {
		System.out.println(col+" BULLET HIT "+getClass().getName().toUpperCase()+"(ID="+index+")");
		Application.log.println(col+" BULLET HIT "+getClass().getName().toUpperCase()+"(ID="+index+")");
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

	//ZPM-et tudunk rakni mezore.
	public void setZpm() {
		System.out.println("PUTZPM "+index);
		Application.log.write("PUTZPM "+index);
		//Beallitjuk, hogy tartalmaz a mezp ZPM-et.
		containsZpm = true;
	}
	
	@Override
	public String toString() {
		String s = super.toString();
		s += " BOXES:"+boxes.size()+" ZPM:"+Boolean.toString(containsZpm).toUpperCase();
		return s;
	}

	@Override
	public void draw(Graphics g) {
		if(containsBox)g.setColor(new java.awt.Color(185,156,107));
		else if (containsZpm)g.setColor(new java.awt.Color(80, 200, 100));
		else g.setColor(java.awt.Color.white);
	
		GamePanel p = Application.app.getGamePanel();
		int rectWidth = p.rectWidth;
		int rectHeight = p.rectHeight;
		int marginV = p.marginV;
		int marginH = p.marginH;
		
		int x = index % 10; //oszlopindex
		int y = index /10;  //sorindex
		
		g.fillRect(marginV + (x * rectWidth), marginH + (y * rectHeight), rectWidth, rectHeight);
	
		super.draw(g);
				
	}


}