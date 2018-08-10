package com.my.excep;

public class DuplicatedIsbnException extends Exception {
	public DuplicatedIsbnException() {
		super("DuplicatedIsbnException:같은 값을 가질 수 없습니다.");
	}
	public void myMethod() {
		System.out.println("try another isbn...");
	}
}
