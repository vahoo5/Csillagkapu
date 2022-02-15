//A jatek jatekos altal latott resze: menu, lefutott parancsok.
import java.io.*;
class Application {
	
	//Azert staticok, mert csak egy letezik beloluk.
	public static General general;
	public static LabirinthManager maze;	
	public static void main(String[] args) throws IOException{
		//Menu egyszeru megvalositasa, teszteseteket tartalmazza felsorolas szinten.
		System.out.println("---------------------------------------------------------");
		System.out.println("|   			SZKELETON MENU		    	|");
		System.out.println("---------------------------------------------------------");
		System.out.println("|	1.Szakadekba eses es game over			|");
		System.out.println("|	2.Doboz lerakasa szakadekba			|");
		System.out.println("|	3.ZPM felvetel es jatek megnyerese		|");
		System.out.println("|	4.Doboz felvetele merlegrol			|");
		System.out.println("|	5.Doboz lerakasa merlegre			|");
		System.out.println("|	6.Doboz felvetele mezorol			|");
		System.out.println("|	7.Doboz lerakasa mezore				|");
		System.out.println("|	8.Lepes mezore					|");
		System.out.println("|	9.Fereglyukba lepes				|");
		System.out.println("|	10.Loves specialis falra			|");
		System.out.println("|	11.Loves csillagkapura				|");
		System.out.println("|	12.Loves specialis falra(van csillagkapu)	|");
		System.out.println("|	13.Merlegre lepes				|");
		System.out.println("|	14.Merlegrol lelepes				|");
		System.out.println("---------------------------------------------------------");
		System.out.println("Valassz egy tesztesetet es ird be a szamat!");
		
		//Valasztott teszteset szamanak bekerese standard inputrol.
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int i=0;
		i = Integer.parseInt(in.readLine());
		
		//Ezredes letrehozasa.
		general = new General();
		
		//Valasztott teszteset elkapasa, inditasa.
		switch (i) {
		
		//1:Szakadekba eses es game over
		case 1:
		  System.out.println("1.Szakadekba eses es game over");
		  maze = new LabirinthManager(1);
		  general.move(Direction.WEST);
		  break;
		  
		//2:Doboz lerakasa szakadekba
		case 2:
		  System.out.println("2.Doboz lerakasa szakadekba");
		  maze = new LabirinthManager(2);
		  general.setDirection(Direction.WEST);
		  general.drop();
		  break;
		  
		//3:ZPM felvetel es jatek megnyerese
		case 3:
		  System.out.println("3.ZPM felvetel es jatek megnyerese");
		  maze = new LabirinthManager(3);
		  general.move(Direction.WEST);
		  break;
		  
		//4:Doboz felvetele merlegrol
		case 4:
		  System.out.println("4.Doboz felvetele merlegrol");
		  maze = new LabirinthManager(4);
		  general.pick();
		  break;
		  
		//5:Doboz lerakasa merlegre
		case 5:
		  System.out.println("5.Doboz lerakasa merlegre");
		  maze = new LabirinthManager(5);
		  general.drop();
		  break;
		  
		//6:Doboz felvetele mezorol
		case 6:
		  System.out.println("6.Doboz felvetele mezorol");
		  maze = new LabirinthManager(6);
		  general.pick();
		  break;
		  
		//7:Doboz lerakasa mezore
		case 7:
		  System.out.println("7.Doboz lerakasa mezore");
		  maze = new LabirinthManager(7);
		  general.drop();
		  break;
		  
		//8:Lepes mezore
		case 8:
		  System.out.println("8.Lepes mezore");
		  maze = new LabirinthManager(8);
		  general.move(Direction.WEST);
		  break;
		  
		//9:Fereglyukba lepes
		case 9:
		  System.out.println("9.Fereglyukba lepes");
		  maze = new LabirinthManager(9);
		  general.move(Direction.WEST);
		  break;
		  
		//10:Loves specialis falra
		case 10:
		  System.out.println("10.Loves specialis falra");
		  maze = new LabirinthManager(10);
		  general.shoot(Color.YELLOW);
		  break;
		
		//11:Loves csillagkapura
		case 11:
		  System.out.println("11.Loves csillagkapura");
		  maze = new LabirinthManager(11);
		  general.shoot(Color.YELLOW);
		  break;
		
		//12:Loves specialis falra(van csillagkapu)
		case 12:
		  System.out.println("12.Loves specialis falra(van csillagkapu)");
		  maze = new LabirinthManager(12);
		  general.shoot(Color.BLUE);
		  break;
		
		//13:Merlegre lepes
		case 13:
		  System.out.println("13.Merlegre lepes");
		  maze = new LabirinthManager(13);
		  general.move(Direction.WEST);
		  break;
		
		//14:Merlegrol lelepes
		case 14:
		  System.out.println("14.Merlegrol lelepes");
		  maze = new LabirinthManager(14);
		  general.move(Direction.EAST);
		  break;  
		  
		//Egyeb esetben hibat dob.
		default:
		  System.out.println("Invalid selection");
		  break; // This break is not really necessary
		}
	}
	
	//Jatek vege. Gyozelem vagy vereseg kiirasa.
	public static void endGame(String winOrLose){
		System.out.println("Application.endGame(" + winOrLose + ")");
	}
}