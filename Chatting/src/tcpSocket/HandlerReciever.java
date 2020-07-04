package tcpSocket;

import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class HandlerReciever extends Thread
{

	private Socket socket;
	private Scanner input;
	private FrameBehavior myFrame;
	private Client me;

	public HandlerReciever(Socket argSocket, ArrayBlockingQueue<String> msg,
		FrameBehavior frm, Client c)
	{
		// Set up reference to associated socket.
		socket = argSocket;
		myFrame = frm;
		me = c;
		try {
			input = new Scanner(socket.getInputStream());
		} catch (IOException ioEx) {

		}
	}

	public void run()
	{
		String received;

		do {
			received = input.nextLine();
			if (received != null && received != "")
				myFrame.makeAction(received, me);
			received = "";
		} while (!received.equals("QUIT"));

		try {
			if (socket != null)
			{
				System.out.println("Closing down connection...");
				input.close();
				socket.close();
			}
		} catch (IOException ioEx) {
			System.out.println("Unable to disconnect!");
		}
	}
}
