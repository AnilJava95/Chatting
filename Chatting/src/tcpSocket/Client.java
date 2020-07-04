package tcpSocket;

import java.net.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Client
{
	public String nickname;
	private Socket socket;
	private FrameBehavior myFrame;
	public ArrayBlockingQueue<String> myInputMessages;
	public ArrayBlockingQueue<String> myOutputMessages;
	private HandlerReciever handleInput;
	private HandlerSender handleOutput;

	public Client(Socket argSocket, FrameBehavior frm)
	{
		socket = argSocket;
		myFrame = frm;
		myInputMessages = new ArrayBlockingQueue(100);
		myOutputMessages = new ArrayBlockingQueue(100);
		if (socket != null)
		{
			handleInput = new HandlerReciever(socket, myInputMessages, frm, this);
			handleOutput = new HandlerSender(socket, myOutputMessages);
			handleInput.start();
			handleOutput.start();
		}
	}
}
