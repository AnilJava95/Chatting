/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class ServerListener extends Thread {

	private int portNumber;
	private int clientCount;
	private FrameBehavior myFrm;
	private ServerSocket serverSocket;

	public ServerListener(String port, FrameBehavior mFrm) {
		portNumber = Integer.parseInt(port);
		serverSocket = null;
		clientCount = 1;
		Client g1 = new Client(null, mFrm);
		Info.clients = new ArrayList<Client>();
		Info.clients.add(g1);
		myFrm = mFrm;
	}

	public void run() {
		try {
			serverSocket = new ServerSocket(portNumber); // serverSocket(server)
		} catch (IOException ioEx) {
			System.exit(1);
		}

		do {
			try {
				Socket socket = serverSocket.accept(); // socket(connection)
				Client client = new Client(socket, myFrm);
				Info.clients.add(client);
				clientCount++;
			} catch (IOException ex) {
				Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
			}

		} while (clientCount <= 5);

	}
}
