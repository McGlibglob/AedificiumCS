package com.aedificium.server;

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aedificium.model.service.DOMFactory;
import com.aedificium.model.service.exceptions.FileLoadException;


public class AedificiumThreadedServer {
	
	//Create Logger
	static Logger logger = LogManager.getLogger();
	
	static int threads = 1;
	
	
	
	
	AedificiumThreadedServer()
	{
		
	}
	
	public static void startServer() 
	 {
	     try
	     {
	        logger.info("The Aedificium Server is up and going!");

	        @SuppressWarnings("resource") 
	        
	        int portNum = getPortNum("PortNumber");
			ServerSocket ss = new ServerSocket(portNum);

	        for (;;)
	        {   
	        	Socket socket = ss.accept();
				
	        	logger.info("Thread # " + threads);
	        	
	        	AedificiumThreadedServerHandler ash = new AedificiumThreadedServerHandler(socket);
	        	ash.start();   
	        	
	        	threads++;
	        }
	     }
	     catch (Exception e)
	     {  logger.error("Aedificium server failed to start ", e);
	     }    	  	

	    } //End ServerStart () method
	
	public static int getPortNum(String portReq)
	{
		String portNum;
		DOMFactory domFactory = new DOMFactory();
		int portNumInt = 0;
		try {
			portNum = domFactory.getInt(portReq);
			portNumInt = Integer.parseInt(portNum);
			
		} catch (FileLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return portNumInt;
		
	}//End getPortNum
	
	public static void main(String arg[]) {
		startServer();
	}
	
}//End of AedificiumServer Class.
