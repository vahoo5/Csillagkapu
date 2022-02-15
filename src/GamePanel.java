import java.awt.Graphics;

import javax.swing.JPanel;

import sun.org.mozilla.javascript.internal.SecurityController;

public class GamePanel extends JPanel {

	
	public static int rectWidth;
	public static int rectHeight;
	public static int marginV;
	public static int marginH;
	
	public GamePanel(){
		setSize(600, 600);
		rectWidth = (int) (this.getWidth()/10 *0.96);
		rectHeight = (int) (this.getHeight()/10 *0.96);
		marginV = (this.getWidth() - 10 * rectWidth )/2; 
		marginH = (this.getHeight() - 10 * rectHeight )/2; 
	
	}
	/**
	 * Grafikus felulet kirajzolasaert felelos fuggveny
	 * Negyzetracsszeruen epiti fel a palyat
	*/
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Application.maze.drawMap(g);
		Application.general.draw(g);
		Application.jaffa.draw(g);
		if(Application.replicator != null)	//repaint eseten szukseges a vizsgalat
			Application.replicator.draw(g);
		
	}
}
