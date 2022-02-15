import java.awt.Graphics;

public class CreatureView implements Drawable {
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		Application.g.drawRect(	Application.general.getPosBlock().getIndex()%BLOCKS_IN_ROW,
								(int)Application.general.getPosBlock().getIndex()/BLOCKS_IN_COLUMN, 
								BLOCK_SIZE,
								BLOCK_SIZE);
		Application.g.drawRect(	Application.replicator.getPosBlock().getIndex()%BLOCKS_IN_ROW,
								(int)Application.replicator.getPosBlock().getIndex()/BLOCKS_IN_COLUMN, 
								BLOCK_SIZE,
								BLOCK_SIZE);
		Application.g.drawRect(	Application.jaffa.getPosBlock().getIndex()%BLOCKS_IN_ROW,
								(int)Application.jaffa.getPosBlock().getIndex()/BLOCKS_IN_COLUMN, 
								BLOCK_SIZE,
								BLOCK_SIZE);
	}
}
