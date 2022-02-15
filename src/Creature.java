import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Creature implements Serializable{
	protected Direction direction;
	protected AbstractBlock PosBlock;
	protected Field startField;
	protected int weight;
	public Creature(){
		this.direction = Direction.WEST;
	}
	public Creature(Direction direction, Field startField) {
		this.direction = direction;
		this.startField = startField;
	}
	public AbstractBlock getPosBlock() {
		return PosBlock;
	}
	public void setPosBlock(AbstractBlock PosBlock) {
		this.PosBlock = PosBlock;
		System.out.println(getClass().getName().toUpperCase()+" ARRIVEAT "+PosBlock.getClass().getName().toUpperCase()+"(ID="+PosBlock.getIndex()+")");
		Application.log.println(getClass().getName().toUpperCase()+" ARRIVEAT "+PosBlock.getClass().getName().toUpperCase()+"(ID="+PosBlock.getIndex()+")");
	}
	public Field getStartField() {
		return startField;
	}

	public void setDirection(Direction direction) {
		System.out.println(getClass().getName().toUpperCase()+" IS SET TO "+direction); 
		Application.log.println(getClass().getName().toUpperCase()+" IS SET TO "+direction);

		this.direction = direction;
	}
	@Override
	public String toString() {
		return getClass().getName().toUpperCase()+","+PosBlock.getIndex()+","+direction;
	}
	/**
	 * Kirajzolja az adott karaktert a palyara
	 * A karakter iranyat egy kis vonal jelzi
	 * @param g
	 */
	public void draw(Graphics g){
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		if(this.getClass() == General.class){
			g2d.setColor(new java.awt.Color(0,204,0));	//general szin
		}else if(this.getClass() == Jaffa.class){
			g2d.setColor(new java.awt.Color(230,150,0)); //jaffa szin
		}else
			g2d.setColor(new java.awt.Color(204,0,0));	//replicator szin
		
		int x = PosBlock.getIndex() % 10; //oszlopindex
		int y = PosBlock.getIndex() / 10;  //sorindex
		
		GamePanel p = Application.app.getGamePanel();
		int rectWidth = p.rectWidth;
		int rectHeight = p.rectHeight;
		int marginV = p.marginV;
		int marginH = p.marginH;

		//karakter abrazolasa
		g2d.fillOval(marginV + (x * rectWidth), marginH + (y * rectHeight),(int)( rectWidth), (int)(rectHeight));		
		
		g2d.setColor(java.awt.Color.black);
		g2d.drawOval(marginV + (x * rectWidth), marginH + (y * rectHeight),(int)( rectWidth), (int)(rectHeight));		
		
		switch(direction){
		case SOUTH:		g2d.drawLine(marginV + (x * rectWidth)+rectWidth/2, marginH + (y * rectHeight)+rectHeight/2,marginV + (x * rectWidth)+rectWidth/2, marginH + (y * rectHeight)+rectHeight);	break;
		case NORTH:		g2d.drawLine(marginV + (x * rectWidth)+rectWidth/2, marginH + (y * rectHeight)+rectHeight/2,marginV + (x * rectWidth)+rectWidth/2, marginH + (y * rectHeight));	break;
		case WEST:		g2d.drawLine(marginV + (x * rectWidth)+rectWidth/2, marginH + (y * rectHeight)+rectHeight/2,marginV + (x * rectWidth), marginH + (y * rectHeight)+rectHeight/2);break;
		case EAST:		g2d.drawLine(marginV + (x * rectWidth)+rectWidth/2, marginH + (y * rectHeight)+rectHeight/2,marginV + (x * rectWidth)+rectWidth, marginH + (y * rectHeight)+rectHeight/2);	break;
		}
		
	}
	public void writeObject(ObjectOutputStream os){
		try {
			os.writeObject(PosBlock.getIndex());
			os.writeObject(direction);
			os.writeObject(weight);		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void readObject(ObjectInputStream is){
			
		try {
		
			int PosIndex = (int)is.readObject();
			PosBlock = Application.maze.getBlock(PosIndex);
			
			direction = (Direction)is.readObject();
			weight = (int)is.readObject();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
