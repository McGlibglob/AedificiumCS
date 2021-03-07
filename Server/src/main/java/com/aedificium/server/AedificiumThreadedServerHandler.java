package com.aedificium.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aedificium.model.business.BookMgr;
import com.aedificium.model.business.ServiceLoadException;
import com.aedificium.model.domain.Book;
import com.aedificium.model.service.exceptions.BookException;
import com.aedificium.model.service.exceptions.LibrarianException;
import com.aedificium.model.service.exceptions.LogException;

public class AedificiumThreadedServerHandler extends Thread{
	
	
	//Create Logger
	Logger logger = LogManager.getLogger();
	BookMgr bookMgr = new BookMgr();
	boolean status;
	
	
	//ATTRIBUTES
	private Socket socket;
	
	
	//CONSTRUCTORS
	
	//Default
	public AedificiumThreadedServerHandler() {
		
	}
	
	//Default
		public AedificiumThreadedServerHandler(Socket incoming) {
			socket = incoming;
		}
	
	//METHODS
	
	//A method that is constantly running to catch incoming sockets and hand them over to the server for management.
	@Override
	public void run()
	{	
		
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		String action = null;

		
		try {
			in = getInputStream(in);
			out = getOutputStream(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		action = getActionString(in);
		
		
		switch (action)
		{
			case "Add Book":
				try {
					status = addBookAndValidate(in);
					sendObject(out, status);
				} catch (ClassNotFoundException | IOException | ServiceLoadException | BookException e) {
					e.printStackTrace();
				}
			case "Delete Book":
				deleteBook(in);
				
		}//End of switch
			

		closeSocket(in, out);
        
        
        
        
     }//End run() method
	
	
	private void deleteBook(ObjectInputStream in)
	{
		String title = null;
		
		try {
			title = receiveString(in);
			System.out.println("Delete book " + title);
			bookMgr.deleteBook(title);
		} catch (ClassNotFoundException | IOException | ServiceLoadException e) {
			e.printStackTrace();
		}
	}
	
	
	private String getActionString(ObjectInputStream in)
	{
		String action = null;
		
		try {
			action = receiveString(in);
			System.out.println("[" + action + "] is the action to be done.");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return action;
	}//End of getActionString() method.
	
	
	private boolean addBookAndValidate(ObjectInputStream in) throws ClassNotFoundException, IOException, ServiceLoadException, BookException
	{
		boolean status = false;
		Book book = new Book();
		Book book2 = new Book();
		String title = null;
		
		book = receiveBook(in);
		System.out.println(book.toString());
		storeBook(book);
		
		title = book.getTitle();
		
		book2 = bookMgr.getBook(title);
		
		status = book2.validate();
		
		return status;
	}
	
	
	private void sendObject(ObjectOutputStream out, Object object) throws IOException
	{
		out.flush();
        out.writeObject(object);
	}//End of sendObject() method.
	
	
	private String receiveString(ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		String string = (String)in.readObject();
		return string;
	}//End of receivedBoolean() method.
	
	
	private Book receiveBook(ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		Book book = (Book)in.readObject();
		return book;
	}//End of receivedBook() method.
	
	
	private void storeBook(Book book)
	{
		bookMgr = new BookMgr();
		try {
			bookMgr.createBook(book, "babyshoes");
		} catch (ServiceLoadException | BookException | LibrarianException | LogException | SQLException e) {
			e.printStackTrace();
		}
	}//End of storeBook() method.
	
	
	private ObjectInputStream getInputStream(ObjectInputStream in) throws IOException
	{
		in = new ObjectInputStream(socket.getInputStream());
		return in;
	}//End of getInputStream() method.
	
	
	private ObjectOutputStream getOutputStream(ObjectOutputStream out) throws IOException
	{
		out = new ObjectOutputStream(socket.getOutputStream());
		return out;
	}//End of getOutputStream() method.
	
	
	public void closeSocket(ObjectInputStream in, ObjectOutputStream out)
	{
		try
        {
      	
	        in.close();
	        out.close();
	        socket.close();
    	}
        
        catch (Exception e) {}
	}
	
}//End of AedificiumServerHandlerClass.
