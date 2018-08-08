package com.my.manager;

import java.util.Scanner;
import com.my.manager.*;
import com.my.vo.Book;
import com.my.vo.Magazine;

public class ArrayManager implements IManager{
	private Book[] books;
	private int index;
	public ArrayManager() {
		books = new Book[10];
	}
	
	public int insert() {
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
	
	public int update() {
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

	public int delete() {
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

	public int list() {
		for (int i = 0; i < index; ++i) {
			System.out.println(books[i].toString());
		}
		return 0;
	}

	public int find() {
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
