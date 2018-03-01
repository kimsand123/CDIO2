package controller;

import socket.ISocketController;
import socket.SocketOutMessage;

public class HandleWeightCTRL {
	ISocketController socketCTRL;


	public HandleWeightCTRL(ISocketController socketCTRL) {
		this.socketCTRL = socketCTRL;
	}


	public void sendMessageToPrimaryDisplay(String msg) {
		socketCTRL.sendMessage(new SocketOutMessage("D " + msg + "crlf"));
	}

	public void sendMessageToSecondaryDisplay(String msg) {
		if (msg.length() <= 30) {
			socketCTRL.sendMessage(new SocketOutMessage("P111 " + msg + "crlf"));
		} else {
			//throw (MsgTooLongException);
		}
	}
	
	public void resetDisplay() {
		socketCTRL.sendMessage(new SocketOutMessage("DW crlf"));
	}
	
	public void setInputString(String msg) {
		socketCTRL.sendMessage(new SocketOutMessage());
	}
	
	public String getInput() {
		return new SocketInMessage(RM20, "RM20 8 " + msg + "\""+ "\"" + "\"&3" + "\"" +  " crlf")
	}
}
