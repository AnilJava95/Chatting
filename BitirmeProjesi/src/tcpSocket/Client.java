/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpSocket;

import java.net.*;
import java.util.concurrent.ArrayBlockingQueue;
import javax.swing.JFrame;

/**
 *
 * @author student
 */
public class Client {

	public String nickname;
	private Socket socket;
	private FrameBehavior myFrame;
	public ArrayBlockingQueue<String> myInputMessages;
	public ArrayBlockingQueue<String> myOutputMessages;
	private HandlerReciever handleInput;
	private HandlerSender handleOutput;

	public Client(Socket argSocket, FrameBehavior frm) {
		socket = argSocket;
		myFrame = frm;
		myInputMessages = new ArrayBlockingQueue(100);
		myOutputMessages = new ArrayBlockingQueue(100);
		if (socket != null) {
			handleInput = new HandlerReciever(socket, myInputMessages, frm, this);
			handleOutput = new HandlerSender(socket, myOutputMessages);
			handleInput.start();
			handleOutput.start();
		}
	}
}
