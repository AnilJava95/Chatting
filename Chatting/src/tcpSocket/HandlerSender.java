package tcpSocket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandlerSender extends Thread
{
	private Socket socket;
	private PrintWriter output;
	private ArrayBlockingQueue<String> myMessages;

	public HandlerSender(Socket argSocket, ArrayBlockingQueue<String> msg)
	{
		socket = argSocket;
		myMessages = msg;
		try {
			output = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException ioEx) {

		}
	}

	public void run()
	{
		String message;

		try {
			do {
				message = myMessages.take();
				output.println(message);

			} while (message != "QUIT");
		} catch (InterruptedException ex) {
			Logger.getLogger(HandlerSender.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			if (socket != null)
			{
				System.out.println("Closing down connection...");
				output.close();
				socket.close();
			}
		} catch (IOException ioEx) {
			System.out.println("Unable to disconnect!");
		}
	}
}
