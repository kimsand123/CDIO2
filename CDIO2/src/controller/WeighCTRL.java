package controller;

import java.io.IOException;

import socket.ISocketController;
import socket.SocketController;
import socket.SocketInMessage;
import socket.SocketOutMessage;

public class WeighCTRL {
	
	ISocketController socketCTRL = new SocketController();
	HandleWeightCTRL weight = new HandleWeightCTRL(socketCTRL);
	

	public WeighCTRL() throws IOException{
		
		socketCTRL.
		socket.sendMessage(WrapMessage("TEST"));
		
	}
	
	private SocketOutMessage WrapMessage(String msg) {
		return new SocketOutMessage(msg);
	}
	
	
}
