package tcpSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerListener extends Thread
{

	private int portNumber;
	private int clientCount;
	private FrameBehavior myFrm;
	private ServerSocket serverSocket;

	public ServerListener(String port, FrameBehavior mFrm)
	{
		portNumber = Integer.parseInt(port);
		serverSocket = null;
		clientCount = 1;
		Client g1 = new Client(null, mFrm);
		ServerPanel.clients = new ArrayList<Client>();
		ServerPanel.clients.add(g1);
		myFrm = mFrm;
	}

	public void run()
	{
		try {
			serverSocket = new ServerSocket(portNumber); // serverSocket(server)
		} catch (IOException ioEx) {
			System.exit(1);
		}

		do {
			try {
				Socket socket = serverSocket.accept(); // socket(connection)
				Client client = new Client(socket, myFrm);
				ServerPanel.clients.add(client);
				clientCount++;
			} catch (IOException ex) {
				Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
			}
		} while (clientCount <= 5);
	}
}
