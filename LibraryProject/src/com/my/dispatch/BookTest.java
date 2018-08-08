package com.my.dispatch;

import com.my.vo.Book;
import com.my.vo.Magazine;
import java.util.Scanner;

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
		Book[] books = new Book[10];
		BookTest bt = new BookTest();
		boolean flag = true;
		int key;
		int index = 0;
		Scanner s = new Scanner(System.in);
		
		while(flag) {
			System.out.println(mainMenu);
			key = s.nextInt();
			switch (key) {
			case 1:		// 조회
				bt.list(books, index);
				break;
			case 2:		// 입력
				index = bt.insert(books, index);
				break;
			case 3:		// 수정
				bt.update(books, index);
				break;
			case 4:		// 삭제
				index = bt.delete(books, index);
				break;
			case 5:		// 검색
				bt.find(books, index);
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
	
	public int insert(Book[] books, int index) {
		Scanner s = new Scanner(System.in);
		System.out.println("1)Book  2)Magazine");
		int input = s.nextInt();
				
		System.out.print("isbn = ");
		String isbn = s.next();
		System.out.print("title = ");
		String title = s.next();
		System.out.print("author = ");
		String author = s.next();
		System.out.print("publisher = ");
		String publisher = s.next();
		System.out.print("price = ");
		int price = s.nextInt();
		System.out.print("desc = ");
		String desc = s.next();
		
		Book b = null;
		
		if(input == 2) {
			System.out.println("year = ");
			int year = s.nextInt();
			System.out.println("month = ");
			int month = s.nextInt();
			b = new Magazine(isbn, title, author, publisher, price, desc, year, month);
		} else if(input == 1) {
			b = new Book(isbn, title, author, publisher, price, desc);	
		}
		books[index] = b;
		return ++index;
	}
	
	public int update(Book[] books, int index) {
		Scanner s = new Scanner(System.in);
		System.out.println("수정할 책의 isbn을 입력하시오.");
		String isbn = s.next();
		for(int i = 0; i < index; i++){
			if(books[i].getIsbn().equals(isbn)) {
				
				if(books[i] instanceof Book) {
					Book b = books[i];
					System.out.print("isbn = ");
					String isbn_ = s.next();
					System.out.print("title = ");
					String title = s.next();
					System.out.print("author = ");
					String author = s.next();
					System.out.print("publisher = ");
					String publisher = s.next();
					System.out.print("price = ");
					int price = s.nextInt();
					System.out.print("desc = ");
					String desc = s.next();
					b = new Book(isbn_, title, author, publisher, price, desc);
					books[i] = b;
				} else if(books[i] instanceof Magazine) {
					Magazine b = (Magazine)books[i];
					System.out.print("isbn = ");
					String isbn_ = s.next();
					System.out.print("title = ");
					String title = s.next();
					System.out.print("author = ");
					String author = s.next();
					System.out.print("publisher = ");
					String publisher = s.next();
					System.out.print("price = ");
					int price = s.nextInt();
					System.out.print("desc = ");
					String desc = s.next();
					System.out.println("year = ");
					int year = s.nextInt();
					System.out.println("month = ");
					int month = s.nextInt();
					b = new Magazine(isbn_, title, author, publisher, price, desc, year, month);
					books[i] = b;
				}
				break;
			}
		}
		return 0;
	}

	public int delete(Book[] books, int index) {
		Scanner s = new Scanner(System.in);
		System.out.println("삭제할 책의 isbn을 입력하시오.");
		String isbn = s.next();
		
		for(int i = 0; i < index; i++){
			if(books[i].getIsbn().equals(isbn)) {
				books[i] = books[index-1];
				index--;
				break;
			}
		}
		return index;
	}

	public int list(Book[] books, int index) {
		for (int i = 0; i < index; ++i) {
			System.out.println(books[i].toString());
		}
		return 0;
	}

	public int find(Book[] books, int index) {
		Scanner s = new Scanner(System.in);
		System.out.println("검색할 책의 isbn을 입력하시오.");
		String isbn = s.next();
		
		boolean res = false;
		
		for(int i = 0; i < index; i++){
			if(books[i].getIsbn().equals(isbn)) {
				System.out.println(books[i].toString());
				res = true;
			}
		}
		
		if(!res) 
			System.out.println("못찾았당");
		return index;
	}
}
