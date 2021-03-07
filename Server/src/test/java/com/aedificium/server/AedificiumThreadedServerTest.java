package com.aedificium.server;

import static org.junit.Assert.*;

import org.junit.Test;

public class AedificiumThreadedServerTest {

	@Test
	public void test() {
		
		int portNum =0;
		portNum = AedificiumThreadedServer.getPortNum("PortNumber");
		System.out.println(portNum);
		assertTrue(portNum == 8181);
	}

}
