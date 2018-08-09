package com.my.manager;
import com.my.vo.*;
import java.util.*;

public class ListManager implements IManager{
	private ArrayList<Book> list;
	
	public ListManager() {
		list = new ArrayList<Book>();
	}
	
	@Override 
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
		
		list.add(b);
		return list.size();
	}
	
	@Override
	public int update() {
		Scanner s = new Scanner(System.in);
		System.out.println("수정할 책의 isbn을 입력하시오.");
		String isbn = s.next();
		
		for(Book b : list) {
			if(b.getIsbn().equals(isbn)) {
				int idx = list.indexOf(b);
				if(b instanceof Magazine) {
					Magazine n = null;
					
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
					
					n = new Magazine(isbn_, title, author, publisher, price, desc, year, month);
					list.set(idx, n);
				} else {
					Book n = null;
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
					
					n = new Book(isbn_, title, author, publisher, price, desc);
					list.set(idx, n);
				}
				break;
			}
		}
		return 0;
	}
	@Override
	public int delete() {
		Scanner s = new Scanner(System.in);
		System.out.println("삭제할 책의 isbn을 입력하시오.");
		String isbn = s.next();
		
		for(Book b :list){
			if(b.getIsbn().equals(isbn)) {
				int idx = list.indexOf(b);
				list.remove(idx);
				break;
			}
		}
		return list.size();
	}
	@Override
	public int list() {
		for(Book b : list) {
			System.out.println(b.toString());
		}
		return 0;
	}
	@Override
	public int find() {
		Scanner s = new Scanner(System.in);
		System.out.println("검색할 책의 isbn을 입력하시오.");
		String isbn = s.next();
		
		for(Book b :list){
			if(b.getIsbn().equals(isbn)) {
				System.out.println(b.toString());
				break;
			} else {
				System.out.println("못찾았당");
			}
		}
		
		return 0;
	}
}
