import java.awt.Graphics;

public interface Drawable {
	public static int BLOCK_SIZE = 5;
	public static int BLOCKS_IN_ROW = 10;//TODO változtatni kell, amekkora a pálya
	public static int BLOCKS_IN_COLUMN = 10;//TODO változtatni kell, amekkora a pálya
	public void Update();
}
