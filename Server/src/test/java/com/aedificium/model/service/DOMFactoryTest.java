package com.aedificium.model.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.aedificium.model.service.exceptions.FileLoadException;

public class DOMFactoryTest {

	DOMFactory factory = new DOMFactory();
	
	@Test
	public void testGetDocument() throws FileLoadException {
		Document doc;
		doc = factory.getDocument();
		assertTrue(doc != null);
	}
	
	@Test
	public void testGetRoot() throws FileLoadException {
		Element root = factory.getRoot();
		assertTrue(root != null);
	}
	
	@Test
	public void testGetNodeList() throws FileLoadException {
		NodeList nodes = factory.getNodeList();
		assertTrue(nodes != null);
	}
	
	@Test
	public void testImpl() throws FileLoadException {
		String serviceName = "IStoreBook";
		String implName = new String();
		
		implName = factory.getImpl(serviceName);
		assertTrue(implName != null);
	}
	
	@Test
	public void testInt() throws FileLoadException {
		String portNum = "PortNumber";
		String number = new String();
		
		number = factory.getInt(portNum);
		assertTrue(number != null);
	}

}
