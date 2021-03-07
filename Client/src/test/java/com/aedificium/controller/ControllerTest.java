package com.aedificium.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.aedificium.model.domain.Book;

public class ControllerTest {

	@Test
	public void testPerformAction() {
		boolean status = false;
		String addBook = "Add Book";
		String deleteBook = "Delete Book";
		
		Book book = new  Book("The House of Leaves", 2000, true, 
				"Danielewski, Mark Z.", "0375703764", "aoeirujd");
		String title = book.getTitle();
		Controller ac = new Controller();
		
		status = ac.performAction(book, addBook);
		
		assertTrue(status);
		
		ac.performAction(title, deleteBook);
	}

}
