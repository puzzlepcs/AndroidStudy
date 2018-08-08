package manager;

import menu.*;
import java.util.Scanner;

public class SandwichManagement {
	private Sandwich[] sandwich;
	private int cnt;
	
	public SandwichManagement() {
		sandwich = new Sandwich[10];
	}
	
	public int addOrder() {
		Scanner s = new Scanner(System.in);
		System.out.println("1)비엘티 샌드위치  2)터키베이컨아보카도 샌드위치");
		int menu = s.nextInt();
		
		Sandwich ss = null;
		switch (menu) {
		case 1:
			ss = new ItalianBLTSandwich();
			break;
		case 2:
			ss = new TurkeyBaconAvocadoSandwich();
			break;
		default:
			System.out.println("1,2 중에 골라");
		}
		System.out.println("수량을 입력해주세요.");
		int amount = s.nextInt();
		
		ss.setAmount(amount);
		ss.prepareIngredient();
		sandwich[cnt] = ss;
		return ++cnt;
	} 
	public void printOrderList() {
		for (int i = 0; i < cnt; i++) {
			sandwich[i].printInfo();
		}
	}
	public void serveOrder() {
		Sandwich order = sandwich[0];
		for(int i = 1; i < cnt; i++) {
			sandwich[i-1] = sandwich[i];
		}
		cnt--;
		System.out.print("서빙되는 샌드위치: ");
		order.printInfo();
	}
}
