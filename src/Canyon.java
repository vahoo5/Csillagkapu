import java.awt.Graphics;
import java.io.IOException;

import sun.applet.AppletPanel;

//A szakadek osztalyt valositja meg, az AbstractBlock-bol oroklodik.
class Canyon extends AbstractBlock {
	
	//Konstruktor.
	public Canyon(int id,int[] n){
		super(id,n);
		passable = true;
	}
	
	//Ha szakadekba lepunk, akkor a karakter eletet veszit, replikator meghal es mezove alakul.
	//Ha nincs vege a jateknak, akkor beallitja az ezredest a start-pozicioba.
	@Override
	public void moveToThisBlock(Replicator r){
		System.out.println("REPLICATOR DISAPPEARS");
		Application.log.write("REPLICATOR DISAPPEARS");
			//Replicator megszunik es mezo lesz a szakadek azon resze helyett.		
			
			Application.replicator.stop();
			Application.replicator = null;
			Application.app.replicator_null = true;
			Application.maze.transformCanyon(index);	
		 	
	}
	
	//Erre a blokkra nem tudunk raloni, igy nem csinal a fuggveny semmit, ha meghivjak.
	@Override
	public void shootOnThisBlock(Color col, Direction dir) {
	}
	
	//Nem szukseges a jelzes neki, igy nem csinal semmit.
	@Override
	public void notifyBlock() {
	}
	
	//Null erteket ad vissza, hisz nem lehet rajta doboz.
	@Override
	public Box getBox() {
		return null;
	}
	
	//Athaladhatosag lekerdezese.
	@Override
	public boolean isPassable(){
		return true;
	}
	
	//Doboz lerakasa es megsemmisitese.
	@Override
	public void setBox(Box box){
		box = null;
		System.out.println("BOX DESTROYED");
		Application.log.println("BOX DESTROYED");
	}

	//Ha karakter lep ra, akkor meghivja a die() fuggvenyet.
	@Override
	public void moveToThisBlock(Character c) {
		c.setPosBlock(this);		
		Field start = Application.maze.getStartField();
		c.die();
		c.setPosBlock(start);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public void draw(Graphics g) {
		
		g.setColor(java.awt.Color.RED);
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