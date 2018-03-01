package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import socket.SocketInMessage.SocketMessageType;

public class SocketController implements ISocketController {
	Set<ISocketObserver> observers = new HashSet<ISocketObserver>();
	private BufferedReader inStream;
	private PrintWriter outStream;
	//TODO Maybe add some way to keep track of multiple connections?


	@Override
	public void registerObserver(ISocketObserver observer) {
		observers.add(observer);
	}

	@Override
	public void unRegisterObserver(ISocketObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers(SocketInMessage message) {
		for (ISocketObserver socketObserver : observers) {
			socketObserver.notify(message);
		}
	}
	
	@Override
	public void sendMessage(SocketOutMessage message) {
		String msg = message.getMessage();
		int pointInString = 0;
		
		if (outStream!=null){
			System.out.println(msg + "  Is sent over socket");
			while(pointInString <= msg.length()) {
				
			}
			
			//TODO send something over the socket! 
		} else {
			//TODO maybe tell someone that connection is closed?
		}
	}

	@Override
	public String getMessage(String type, String message) {
		String returnValue = "";
			if (inStream!=null) {
				//TODO recieve something from socket
			} else {
				//TODO maybe tell someone that connection is closed or nothing is there?
			}
		
		return returnValue;
	}
	
	@Override
	public void run() {
		//TODO some logic for listening to a socket //(Using try with resources for auto-close of socket)
		try (ServerSocket listeningSocket = new ServerSocket(PORT)){ 
			while (true){
				waitForConnections(listeningSocket); 	
			}		
		} catch (IOException e1) {
			// TODO Maybe notify MainController?
			e1.printStackTrace();
		} 


	}

	private void waitForConnections(ServerSocket listeningSocket) {
		try {
			Socket activeSocket = listeningSocket.accept(); //Blocking call
			inStream = new BufferedReader(new InputStreamReader(activeSocket.getInputStream()));
			outStream = new PrintWriter(activeSocket.getOutputStream());
			String inLine = null;
			//.readLine is a blocking call 
			//TODO How do you handle simultaneous input and output on socket?
			//TODO this only allows for one open connection - how would you handle multiple connections?
			while (true){
				inLine = inStream.readLine();
				System.out.println(inLine);
				if (inLine==null) break;
				String[] inLineArray = inLine.split(" ");
				switch (inLineArray[0]) {
				case "D":// Display a message in the primary display
					//TODO Refactor to make sure that faulty messages doesn't break the system
					notifyObservers(new SocketInMessage(SocketMessageType.D, inLine.split(" ")[1])); 			
					break;
				case "DW": //Clear primary display
					//TODO implement
					break;
				case "P111": //Show something in secondary display
					//TODO implement
					break;
				case "T": // Tare the weight
					//TODO implement
					break;
				case "S": // Request the current load
					//TODO implement
					break;
				case "B": // Set the load
					//TODO implement
					break;
				case "Q": // Quit
					//TODO implement
					break;
				case "RM20": // Display a message in the secondary display and wait for response
					//TODO implement logic for RM command
					break;
				case "K":
					if (inLine.split(" ").length>1){
						notifyObservers(new SocketInMessage(SocketMessageType.K, inLine.split(" ")[1]));
					}
					break;
				default: //Something went wrong?
					//TODO implement
					break;
				}
			}
		} catch (IOException e) {
			//TODO maybe notify mainController?
			e.printStackTrace();
		} 
	}
}