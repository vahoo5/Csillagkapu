//A LabirinthManager osztaly megvalositasa.
import java.util.List;
import java.util.Random;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import sun.security.action.GetBooleanAction;
import sun.security.util.Resources_zh_TW;

public class LabirinthManager implements Serializable{
	//Letaroljuk a palyat, a csillagkapukat es a kezdopoziciot.
	private static List<AbstractBlock> map;
	private boolean blueyellowWormHoleExists = true;
	private boolean redgreenWormHoleExists = false;
	static StarGate yellowStarGate = null;
	static StarGate blueStarGate = null;
	static StarGate greenStarGate = null;
	static StarGate redStarGate = null;
	private static int allZpmCnt;
	private static int JaffaZpmCnt;
	private static int GeneralZpmCnt;
	private static Random rand = new Random();



	public LabirinthManager(){
		allZpmCnt = 5;
		GeneralZpmCnt = JaffaZpmCnt = 0;
		map = new ArrayList<AbstractBlock>();
	}
	
	
	//Csillagkapu lekerese..
	public void setStarGate(StarGate s){
		if(s.getColor()==Color.BLUE)blueStarGate = s;
		else yellowStarGate = s;
	}
	
	//Irany megadasa.
	public Direction oppDir(Direction dir){
		if(dir == Direction.EAST)
			dir = Direction.WEST;
		else if(dir == Direction.WEST)
			dir = Direction.EAST;
		else if(dir == Direction.NORTH)
			dir = Direction.SOUTH;
		else
			dir = Direction.NORTH;

		return dir;
	}

	//Block lekerese.
	public AbstractBlock getBlock(int i){
		return map.get(i);
	}
	
	//Csillagkapu letrehozasa.
	public void createStarGate(Wall w,Color col,Direction dir){
		

		
		//Ha kek volt a lovedek, akkor kek csillagkapu.
		if(col == Color.BLUE){
			//ha letezik mar adoot szinu csillagkapu, toroljuk
			if(blueStarGate != null)deleteStarGate(Color.BLUE);
			
			blueStarGate = new StarGate(w.getIndex(),w.getNeighboursIndex(),dir, Color.BLUE);
			map.set(w.getIndex(), blueStarGate);
			
			System.out.println("CREATESTARGATE "+col);
			Application.log.println("CREATESTARGATE "+col);
			
			//Ha van mar masik szinu csillagkapu, akkor feregjaratot csinalunk.
			if(yellowStarGate != null)createWormHole(col);
		}
		
		//Egyebkent sarga csillagkapu.
		else if (col == Color.YELLOW){
			//ha letezik mar adoot szinu csillagkapu, toroljuk
			if(yellowStarGate != null)deleteStarGate(Color.YELLOW);
			
			yellowStarGate = new StarGate(w.getIndex(),w.getNeighboursIndex(),dir, Color.YELLOW);
			map.set(w.getIndex(), yellowStarGate);
			
			System.out.println("CREATESTARGATE "+col);
			Application.log.println("CREATESTARGATE "+col);			
			
			//Ha van mar masik szinu csillagkapu, akkor feregjaratot csinalunk.
			if(blueStarGate != null)createWormHole(col);
			
		}
		else if (col == Color.GREEN){
			//ha letezik mar adoot szinu csillagkapu, toroljuk
			if(greenStarGate != null)deleteStarGate(Color.GREEN);
			
			greenStarGate = new StarGate(w.getIndex(),w.getNeighboursIndex(),dir, Color.GREEN);
			map.set(w.getIndex(), greenStarGate);
			
			System.out.println("CREATESTARGATE "+col);
			Application.log.println("CREATESTARGATE "+col);
			
			//Ha van mar masik szinu csillagkapu, akkor feregjaratot csinalunk.
			if(redStarGate!= null)createWormHole(col);

		}else{
			//ha letezik mar adoot szinu csillagkapu, toroljuk
			if(redStarGate != null)deleteStarGate(Color.RED);
			
			redStarGate = new StarGate(w.getIndex(),w.getNeighboursIndex(),dir, Color.RED);
			map.set(w.getIndex(), redStarGate);
			
			System.out.println("CREATESTARGATE "+col);
			Application.log.println("CREATESTARGATE "+col);
			
			//Ha van mar masik szinu csillagkapu, akkor feregjaratot csinalunk.
			if(greenStarGate!= null)createWormHole(col);

		}
		

	}
	
	//Csillagkapu torlese.
	public void deleteStarGate(Color col){
		System.out.println("DELETESTARGATE "+col);
		Application.log.println("DELETESTARGATE "+col);

		
		//Kitoroljuk az adott szinu csillagkaput es letrehozunk helyette egy specialis falat.
		if(col == Color.BLUE){
			map.set(blueStarGate.getIndex(), new Wall(blueStarGate.getIndex(),blueStarGate.getNeighboursIndex(),true));
			blueStarGate=null;
			if(blueyellowWormHoleExists){
				blueyellowWormHoleExists = false;
				yellowStarGate.setPaired(false);
			}
		}
		//Kitoroljuk az adott szinu csillagkaput es letrehozunk helyette egy specialis falat.
		else if(col == Color.YELLOW){
			map.set(yellowStarGate.getIndex(), new Wall(yellowStarGate.getIndex(),yellowStarGate.getNeighboursIndex(),true));
			yellowStarGate=null;
			if(blueyellowWormHoleExists){
				blueyellowWormHoleExists = false;
				blueStarGate.setPaired(false);
			}
		}else if(col == Color.GREEN){
			map.set(greenStarGate.getIndex(), new Wall(greenStarGate.getIndex(),greenStarGate.getNeighboursIndex(),true));
			greenStarGate=null;
			if(redgreenWormHoleExists){
				redgreenWormHoleExists = false;
				redStarGate.setPaired(false);
			}
		}else{
			map.set(redStarGate.getIndex(), new Wall(redStarGate.getIndex(),redStarGate.getNeighboursIndex(),true));
			redStarGate=null;
			if(redgreenWormHoleExists){
				redgreenWormHoleExists = false;
				greenStarGate.setPaired(false);
			}
		}
	}
	
	//Sagra csillagkapu lekerese.
	public StarGate getYellowStarGate(){
		return yellowStarGate;
	}
	
	//Kek csillagkapu lekerese
	public StarGate getBlueStarGate(){
		return blueStarGate;
	}
	public static Field getRandomEmptyField(){
		AbstractBlock b = null;
		boolean empty_field = false;
		while(!empty_field){
			b = map.get(new Random().nextInt(map.size()));
			while(!(b.getClass() == Field.class || b.getClass() == Scale.class)){	//mezot vagy merleget keresunk
				b = map.get(rand.nextInt(map.size()));	//random mezo lekerese
			}
			Field f = (Field)b;
			if(f.isPassable() == true && f.getContainsZpm() == false){ //Ures-e a mezo
				empty_field = true;
				b = f;
			}
		}
		return (Field)b;
	}
	
	//Kezdo pozicio lekerese.
	public Field getStartField() {
		return getRandomEmptyField();
	}
	
	/**
	 * feregjarat letrehozasa
	 * @param col1
	 */
	public void createWormHole(Color col1){
		System.out.println("CREATEWORMHOLE");
		Application.log.println("CREATEWORMHOLE");

		
		if(col1 == Color.YELLOW || col1 == Color.BLUE){
			blueyellowWormHoleExists = true;
			blueStarGate.setPassable(true);
			blueStarGate.setIsPaired(true);
			yellowStarGate.setIsPaired(true);
			yellowStarGate.setPassable(true);
		}
		else{
			redgreenWormHoleExists = true;
			redStarGate.setPassable(true);
			greenStarGate.setIsPaired(true);
			redStarGate.setIsPaired(true);
			greenStarGate.setPassable(true);
		}
		//Csillagkapu parok beallitasa.
		
	}

	/**
	 * replikator atalakitja a szakadekot
	 * a szakadek kikerul a terkeprol, egy ures mezo adodik hozza
	 * @param index
	 */
	public void transformCanyon(int index) {
		System.out.println("TRANSFORM CANYON");
		Application.log.println("TRANSFORM CANYON");

		int[] n = map.get(index).getNeighbours();
		Field f = new Field(index, n);
		map.set(index,f);
	}

	public StarGate getGreenStarGate() {
		return greenStarGate;
	}
	public StarGate getRedStarGate() {
		return redStarGate;
	}

	/**
	 * Hozzaad egyet a karakter zpm-jeihez
	 * @param character
	 */
	public static  void addZpm(Character character) {
		if (character == Application.general){
			GeneralZpmCnt++;
			System.out.println("COLLECTZPM ZPMCNT="+GeneralZpmCnt);
			Application.log.println("COLLECTZPM ZPMCNT="+GeneralZpmCnt);
			if(GeneralZpmCnt % 2 == 0){
				CreateZpm();
				allZpmCnt++;
				return;
			}
			
			
		}else{
			JaffaZpmCnt++;
			System.out.println("COLLECTZPM ZPMCNT="+JaffaZpmCnt);
			Application.log.println("COLLECTZPM ZPMCNT="+JaffaZpmCnt);
		}
		
		//jatek vege, osszeszedtuk a zpm-eket a palyan
		if(JaffaZpmCnt + GeneralZpmCnt == allZpmCnt){
			if(JaffaZpmCnt == GeneralZpmCnt)Application.endGame("GAME TIE!\nGENERAL ZPM: "+GeneralZpmCnt+"\nJAFFA ZPM: "+JaffaZpmCnt);
			Application.endGame(GeneralZpmCnt > JaffaZpmCnt ? "GENERAL WINS!\nGENERAL ZPM: "+GeneralZpmCnt+"\nJAFFA ZPM: "+JaffaZpmCnt : "JAFFA WINS!\nGENERAL ZPM: "+GeneralZpmCnt+"\nJAFFA ZPM: "+JaffaZpmCnt);
		}
	}


	/**
	 * Zpm elhelyezese a palyan
	 */
	public static  void CreateZpm() {  //RANDOM
		System.out.println("CREATEZPM");
		Application.log.println("CREATEZPM");
		
		getRandomEmptyField().putZpm();
	}

	/**
	 * Zpm elhelyezese a palyan
	 * @param fieldIndex
	 */
	private  void CreateZpm(int fieldIndex) {  //DETERMINISZTIKUS
		Field f = (Field)map.get(fieldIndex);
		f.setZpm();
		allZpmCnt++;
		System.out.println("PUTZPM "+fieldIndex);
		Application.log.println("PUTZPM "+fieldIndex);

	}
	
	public void addBlock(AbstractBlock a){
			map.add(a);
	}
	
	/**
	 * Mezok statuszanak kilistazasa
	 */
	public void listStatusz(){
		for(AbstractBlock s:map){
			System.out.println(s);
		}
		System.out.println("map size:"+map.size());
		System.out.println("\nGENERALZPM:"+GeneralZpmCnt+" JAFFAZPM:"+JaffaZpmCnt+" ALL:"+allZpmCnt);
	}

	/**
	 * Visszaad egy random iranyt, Replicator mozgatasahoz kell
	 * @return
	 */
	public Direction getRandomDirection() {
		int dirnum = new Random().nextInt(Direction.values().length); //Random irany generalasa
		return Direction.values()[dirnum];		//enumkent adjuk vissza
	}
	
	/**
	 *Mezonkent kirajzolja a palyat
	 * @param g
	 */
	public void drawMap(Graphics g){
		for(AbstractBlock b : map){
			b.draw(g);
		}
	}
	
	public void writeObject(ObjectOutputStream os){
		try {
			os.writeObject(map);
			os.writeObject(blueyellowWormHoleExists);
			os.writeObject(redgreenWormHoleExists);
			os.writeObject(blueStarGate);
			os.writeObject(yellowStarGate);
			os.writeObject(greenStarGate);
			os.writeObject(redStarGate);
			os.writeObject(allZpmCnt);
			os.writeObject(JaffaZpmCnt);
			os.writeObject(GeneralZpmCnt);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void readObject(ObjectInputStream is){
		try {
			map = (List<AbstractBlock>)is.readObject();
			blueyellowWormHoleExists = (boolean)is.readObject();
			redgreenWormHoleExists = (boolean)is.readObject();
			blueStarGate = (StarGate)is.readObject();
			yellowStarGate = (StarGate)is.readObject();
			greenStarGate = (StarGate) is.readObject();
			redStarGate = (StarGate)is.readObject();
			allZpmCnt = (int)is.readObject();
			JaffaZpmCnt = (int)is.readObject();
			GeneralZpmCnt = (int) is.readObject();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}