package dispatch;

import manager.*;
import java.util.Scanner;

public class SandwichTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SandwichManagement man = new SandwichManagement();
		int key;
		boolean flag = true;
		Scanner s = new Scanner(System.in);
		
		while(flag) {
			System.out.println("=========================");
			System.out.println("1)AddOrder 2)PrintList 3)serveOrder 4)terminate");
			key = s.nextInt();
			switch (key) {
			case 1:
				man.addOrder();
				break;
			case 2:
				man.printOrderList();
				break;
			case 3: 
				man.serveOrder();
				break;
			case 4:
				System.out.println("Bye!");
			default:
				flag = false;
				break;
			}
		}
		
		
	}

}
