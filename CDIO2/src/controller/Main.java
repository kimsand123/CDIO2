package controller;

import java.io.IOException;

import socket.ISocketController;
import socket.SocketController;
import weight.IWeightInterfaceController;
import weight.gui.WeightInterfaceControllerGUI;
/**
 * Simple class to fire up application and inject implementations
 * @author Christian
 *
 */
public class Main {

	public static void main(String[] args) throws IOException{
		ISocketController socketHandler = new SocketController();
		IWeightInterfaceController weightController = new WeightInterfaceControllerGUI();
		//Injecting socket and uiController into mainController - Replace with improved versions...
		IMainController mainCtrl = new MainController(socketHandler, weightController);
		//.init and .start could be merged
		mainCtrl.start();
	}
}
