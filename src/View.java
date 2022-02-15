import java.awt.Graphics;
import java.util.ArrayList;

public class View {
	private ArrayList<Drawable> components;
	
	public void UpdateAll(Graphics g){
		for(int i = 0; i < components.size(); i++)
			components.get(i).Update();
	}
}
