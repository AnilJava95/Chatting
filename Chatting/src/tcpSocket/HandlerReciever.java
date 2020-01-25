/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpSocket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import javax.swing.JFrame;

/**
 *
 * @author student
 */
public class HandlerReciever extends Thread {
	
    private Socket socket;
    private Scanner input;
  //  private PrintWriter output;
    private ArrayBlockingQueue<String> myMessages;
    private FrameBehavior myFrame;
    private Client me;
	 
    public HandlerReciever(Socket argSocket, ArrayBlockingQueue<String> msg, 
            FrameBehavior frm, Client c)
    {
        //Set up reference to associated socket...
        socket = argSocket;
        myMessages = msg;
        myFrame = frm;
        me = c;
        try
        {
            input = new Scanner(socket.getInputStream());
        }
        catch(IOException ioEx)
        {
			  
        }
    }
	 
    public void run()
    {
        String received;
		  
        do
        {
            received = input.nextLine();     
           if(received != null && received != ""){
            myFrame.makeAction(received, me);
           }
           received = "";

        } while (!received.equals("QUIT"));

        try
        {
            if (socket != null)
            {
                System.out.println("Closing down connection...");
					 //input.close(); // Necessary?
                socket.close();
            }
        }
        catch(IOException ioEx)
        {
            System.out.println("Unable to disconnect!");
        }
    }
}
