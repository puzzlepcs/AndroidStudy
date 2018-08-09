package com.my.dispatch;

import com.my.vo.Book;
//import com.my.vo.Magazine;
import java.util.Scanner;
import com.my.manager.*;

/**	Library program for managing books
 * 
 * @author puzzlepcs
 * input, delete, modify, view list and search books, terminate program
 * 
 * Program sequence
 * 		launch -> loop(menu -> choose -> execute) -> terminate
 * 
 * 상속과 오버라이딩 개념을 활용한 프로그램.
 * 		Upcasting (객체가 상위타입으로 형변환 가능함.)
 * 		
 * 
 */

public class BookTest {
	public static void main(String[] args) {
		String mainMenu = "1)조회   2)입력  3)수정  4)삭제  5)검색  6)종료";
		IManager man = new ListManager();
		boolean flag = true;
		int key;
		int index = 0;
		Scanner s = new Scanner(System.in);
		
		while(flag) {
			System.out.println(mainMenu);
			key = s.nextInt();
			switch (key) {
			case 1:		// 조회
				man.list();
				break;
			case 2:		// 입력
				man.insert();
				break;
			case 3:		// 수정
				man.update();
				break;
			case 4:		// 삭제
				man.delete();
				break;
			case 5:		// 검색
				man.find();
				break;
			case 6:		// 종료
				flag = false;
				System.out.println("프로그램을 종료합니다.");
				break;
			default:
				System.out.println("1~6사이의 숫자를 입력해 주세요.");
				break;
			}
		}
	}
}
	
