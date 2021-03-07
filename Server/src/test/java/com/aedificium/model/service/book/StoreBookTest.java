package com.aedificium.model.service.book;


import static org.junit.Assert.assertTrue;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.aedificium.model.business.ServiceLoadException;
import com.aedificium.model.service.Factory;
import com.aedificium.model.service.exceptions.BookException;
import com.aedificium.model.service.exceptions.LogException;
import com.aedificium.model.service.hibernate.BaseSessionFactory;

import com.aedificium.model.domain.Book;

public class StoreBookTest {

	
	Logger logger = LogManager.getLogger();
	
	//name a services factory object
	Factory factory = Factory.getInstance();
	//name a book object
	private Book book1  = new Book 
			("The Road", 1998, false, "Cormac McCarthy", "936110549", "0v0emj4");
	
	private String title = book1.getTitle();

	@Test
	public void testStoreBookJDBC() 
			throws ServiceLoadException, 
			ClassNotFoundException, 
			BookException, IOException, 
			LogException, Throwable {
		
		BaseSessionFactory bsf = new BaseSessionFactory();

		Book book2 = new Book();
		//Get Store Book Impl
		IStoreBook storeBook;
		storeBook = (IStoreBook) factory.getService(IStoreBook.NAME);
		
		//Store book 1 in db
		storeBook.storeBook(book1);
		
		
		//Retrieve for db
		IGetBook getBook;
		getBook = (IGetBook) factory.getService(IGetBook.NAME);
		
		book2 = getBook.getBook(title);
		
		
		//Validate Retrieval
		assertTrue(book2.validate());
		
		//Delete from db
		bsf.deleteObject(book1);
		
	}

}
