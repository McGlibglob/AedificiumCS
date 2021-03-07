package com.aedificium.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aedificium.model.domain.Book;

public class Controller {

//Create Logger
	Logger logger = LogManager.getLogger();
	
	Socket socket = null;
	
	boolean status;
	
	public boolean performAction(Object object, String action)
	{
		//ATTRIBUTES
		
		boolean status = false;
		
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		 
		
		  try
		  {
				 //Setup the socket
			 socket = new Socket(InetAddress.getLocalHost(), 8181);
			 
			 //Send up the in and out.
			 out = getOutputStream(out);
			 in = getInputStream(in);
			 
			 //Send the action String.
			  sendObject(out, action);
			  
			  switch (action)
			  {
			 	case "Add Book":
				     
				 //Send book and retrieve status
				  sendObject(out, object);
				  status = receiveBoolean(in);
				  
			 	case "Delete Book":
			 		
			 	//Delete book from database.
			 		sendObject(out, object);
			 		
			  }//End of Switch
		  }//End of try
		  
		  catch(Exception e) { System.out.println("Error" + e); }
		   
		   //Close everything out
		  finally 
		  	{close(in, out, socket);}
		   
		   return status;
	} //End performAction() method
	
	
	private void sendObject(ObjectOutputStream out, Object object) throws IOException
	{
		out.flush();
        out.writeObject(object);
	}//End of sendObject() method.
	
	
	private String receiveString(ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		String string = (String)in.readObject();
		return string;
	}//End of receivedString() method.
	
	
	private boolean receiveBoolean(ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		boolean b = (boolean)in.readObject();
		return b;
	}//End of receivedBoolean() method.
	
	private Book receiveBook(ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		Book book = (Book)in.readObject();
		return book;
	}//End of receivedBook() method.
	
	
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
	
	
	private void close(ObjectInputStream in, ObjectOutputStream out, Socket socket)
	{
		{
	        try
	        {
	    	 
	    	  out.close();
		      in.close();
		      socket.close();
	        }
	        catch(Exception e){}
		  }
	}//End of the close method().
	
}//End of AedificiumController class.