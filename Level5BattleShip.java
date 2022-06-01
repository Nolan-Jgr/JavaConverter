import java.util.Scanner;

public class Battleship_main {
	public static void main(String[] args) {
		int x = 0, y = 0;
		Scanner sc = new Scanner(System.in);
		Battleship b = new Battleship();
		b.hideShip();
		System.out.println("Sink all of the enemy's ships!");
		while (b.getIsRunning()) {
			b.printPrompt1();
			y = sc.nextInt();
			b.printPrompt2();
			x = sc.nextInt();
			b.selectPosition(y, x);
			b.printSea();
		}
		sc.close(); 
}
public class Battleship {
	private int i, j, hit;
	private boolean isRunning = true;
	private char [][]  sea = new char[8][8];
	private char [][]  ships = new char[8][8];

	public Battleship() {
		for (i = 0; i < sea.length; i++) {
			for (int j = 0; j < sea[0].length; j++) {
				sea[i][j] = ' ';
			}
		}
	}

	public void hideShip() {
		int random1Y = (int) (Math.random() * 3);
		int random1X = (int) (Math.random() * 1);
		int random2Y = (int) (Math.random() * 8);
		int random2X = (int) ((Math.random() * 2) + 2);
		for (int i = 0; i < 5; i++) {
			// ships[1 + i][1] = 's';
			ships[random1Y + i][random1X] = 's';
		}
		for (int i = 0; i < 3; i++) {
			// ships[6] [4 + i] = 's';
			ships[random2Y][random2X + i] = 's';
		}
	}

	public void selectPosition(int y, int x) {
		i = 0;
		if (ships[y][x] == 's') {
			sea[y][x] = '!';
			System.out.println("A hit!");
			hit++;
			if (hit == 8) {
				System.out.println("You have sunk all their battleships!");
				isRunning = false;
			}
		} else {
			sea[y][x] = 'X';
			System.out.println("A miss!");
		}
	}

	public void printSea() {
		i = 0;
		j = 0;
		for (i = 0; i < sea.length; i++) {
			for (j = 0; j < sea[0].length; j++) {
				System.out.print("|" + sea[i][j] + "| ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}

	public void printPrompt1() {
		System.out.println("Pick an Y location to fire at (0-7): ");
	}

	public void printPrompt2() {
		System.out.println("Pick a X location to fire at (0-7): ");
	}

	public boolean getIsRunning() {
		return isRunning;
	}

	public int getHit() {
		return hit;
	}
}
	}






