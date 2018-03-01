package controller;

import java.io.IOException;

import socket.ISocketController;
import socket.ISocketObserver;
import socket.SocketInMessage;
import socket.SocketOutMessage;
import weight.IWeightInterfaceController;
import weight.IWeightInterfaceObserver;
import weight.KeyPress;
/**
 * MainController - integrating input from socket and ui. Implements ISocketObserver and IUIObserver to handle this.
 * @author Christian Budtz
 * @version 0.1 2017-01-24
 *
 */
public class MainController implements IMainController, ISocketObserver, IWeightInterfaceObserver {
	public enum KeyState { K1, K2,K3,K4 }

	private ISocketController socketHandler;
	private IWeightInterfaceController weightController;
	private KeyState keyState = KeyState.K1;
	private WeighCTRL weight;
	
	public MainController(ISocketController socketHandler, IWeightInterfaceController weightInterfaceController) throws IOException{
		this.init(socketHandler, weightInterfaceController);
	
//		new WeighCTRL();
			
		
	}

	@Override
	public void init(ISocketController socketHandler, IWeightInterfaceController weightInterfaceController) {
		this.socketHandler = socketHandler;
		this.weightController=weightInterfaceController;
	}

	@Override
	public void start() {
		if (socketHandler!=null && weightController!=null){
			//Makes this controller interested in messages from the socket
			socketHandler.registerObserver(this);
			//Starts socketHandler in own thread
			new Thread(socketHandler).start();
			//TODO set up weightController - Look above for inspiration (Keep it simple ;))
		} else {
			System.err.println("No controllers injected!");
		}
	}

	//Listening for socket input
	@Override
	public void notify(SocketInMessage message) {
		// TODO handle eache type of command. (Remember that the weight might not be ready for input)
		switch (message.getType()) {
		case B:
			break;
		case Q:
			break;
		case D:
			weightController.showMessagePrimaryDisplay(message.getMessage()); 
			break;
		case DW:
			break;
		case S:
			break;
		case T:
			break;
		case P111:
			break;
		case RM204:
			break;
		case RM208:
			break;
		case K:
			handleKMessage(message);
			break;
		}

	}

	private void handleKMessage(SocketInMessage message) {
		switch (message.getMessage()) {
		case "1" :
			this.keyState = KeyState.K1;
			break;
		case "2" :
			this.keyState = KeyState.K2;
			break;
		case "3" :
			this.keyState = KeyState.K3;
			break;
		case "4" :
			this.keyState = KeyState.K4;
			break;
		default:
			socketHandler.sendMessage(new SocketOutMessage("ES"));
			break;
		}
	}
	
	//Listening for UI input
	@Override
	public void notifyKeyPress(KeyPress keyPress) {
		//TODO implement logic for handling input from ui
		switch (keyPress.getType()) {
		case SOFTBUTTON:
			break;
		case TARA:
			break;
		case TEXT:
			break;
		case ZERO:
			break;
		case CANCEL:
			break;
		case EXIT:
			break;
		case SEND:
			if (keyState.equals(KeyState.K4) || keyState.equals(KeyState.K3) ){
				socketHandler.sendMessage(new SocketOutMessage("K A 3"));
			}
			break;
		}
	}

	@Override
	public void notifyWeightChange(double newWeight) {
		// TODO Auto-generated method stub
	}
}
