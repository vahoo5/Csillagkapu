import java.awt.Graphics;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.generic.RETURN;

//Fal osztaly megvalositasa, AbstractBlockbol oroklodik.
public class Wall extends AbstractBlock {
	//Specialis fal-e, melyre love csillagkapu alakul ki.
	protected boolean isSpecial;
	
	//Konstruktor.
	public Wall(int id,int[] n){
		super(id,n);
	}
	
	//Konstruktor, beallitja az isSpecial-t, athaladhatosagot es az index erteket.
	public Wall(int id,int[] neighbours,boolean isSpecial){
		super(id,neighbours);
		this.isSpecial=isSpecial;
		this.index = id;
		//Passable, shootable beallitasa.
		passable = false;
		shootable = false;
	}
	
	//Visszaadja a szomszedokat.
	public int[] getNeighboursIndex(){
		return neighbourIndexes;
	}
	
	//Falra nem lehet lepni.
	@Override
	public void moveToThisBlock(Character c) {
	}
	
	//Specialis fal eseten raloves kezelese.
	@Override
	public void shootOnThisBlock(Color col, Direction dir) {
			//Ha specialis falra lottunk.
			if (isSpecial){
				System.out.println(col+" BULLET HIT SPECIALWALL(ID="+index+")");
				Application.log.println(col+" BULLET HIT SPECIALWALL(ID="+index+")");
				
				//Csillagkapu letrehozasa a megfelelo szinnel.
				Application.maze.createStarGate(this, col, Application.maze.oppDir(dir));
				Application.app.getGamePanel().repaint();
				return;
			}
			System.out.println(col+" BULLET HIT WALL(ID="+index+")");
			Application.log.println(col+" BULLET HIT WALL(ID="+index+")");
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
	
	//Falra nem tudunk dobozt rakni, igy nem csinal semmit.
	public void setBox(Box box){
	}

	//Replicator nem lehet falon, igy nem csinal semmit.
	@Override
	public void moveToThisBlock(Replicator creature) {
		
	}
	
	@Override
	public String toString() {
		return super.toString()+" SPECIAL:"+ Boolean.toString(isSpecial).toUpperCase();
	}

	@Override
	public void draw(Graphics g) {
		if(!isSpecial)g.setColor(java.awt.Color.darkGray);
		else g.setColor(java.awt.Color.gray);
		
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