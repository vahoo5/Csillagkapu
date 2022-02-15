
import java.io.IOException;

public class Replicator  extends Creature implements Runnable  {
	boolean stop = false;
	int weight = 0;
	
	//Replicator konstruktora
	public Replicator(AbstractBlock pos, Direction dir){
		PosBlock = pos;
		PosBlock.setShootable(false);
		direction = dir;
	}
	public Replicator(){}
	
	/**
	 * A replikator viselkedese: 
	 * lep egyet veletlenszeruen majd 2 mp-et alszik es ujra lep
	 */
	@Override
	public void run() {
		while(!stop){

			try {
				Thread.sleep(1500);	//	1.5 mp-enkent lep
				move();
//				Thread.sleep(10000);	//10 mp-enkent lep
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void stop(){
		stop = true;
	}
	
	/**
	 * Replicator veletlenszeru mozgatasa
	 * Eloszor leker egy veletlen iranyt, majd megnezi, hogy lehet-e lepni az adott mezore
	 * Ha igen odalep
	 */
	public synchronized void move() {		
			Direction random_dir = Application.maze.getRandomDirection();
			direction = random_dir;
			AbstractBlock n = PosBlock.getNeighbour(random_dir);
			
		if(n.isPassable() || n.getContainsBox() || n == Application.general.getPosBlock() || n == Application.jaffa.getPosBlock()){ //passable vagy dobozos mezo a szomszed
			PosBlock.setShootable(true);
			setPosBlock(n);
			n.setShootable(false);
			n.moveToThisBlock(this);

		}
		Application.app.getGamePanel().repaint();

	}

}