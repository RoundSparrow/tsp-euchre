package euchre.network;


import java.net.*;
import java.io.*;

import euchre.game.GameLog;

/**
 * A Runnable thread to facilitate the server's connections with the clients
 * 
 * @author mdhelgen
 * @author Stephen A. Gutknecht
 *
 */

public class EuchreConnectionThread extends Thread {

	private Socket socket = null;
	private BufferedReader in;
	private boolean running = true;
	private EuchreProtocol protocol;
	private ServerNetworkManager server;
	private PrintWriter out = null;


	/**
	 * Create a new EuchreConnectionThread.
	 * 
	 * @param name A identifier for the client
	 * @param s The socket connection to the client
	 */
	public EuchreConnectionThread(String name, Socket s, ServerNetworkManager server) {
		super(name);
		socket = s;
		protocol = new EuchreProtocol();
		this.server = server;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			GameLog.outError("ECT", "IOException creating socket output");
			e.printStackTrace();
		}
	}

	/**
	 * The thread's actions. It will wait for input to arrive from the socket, and output when it is recieved.
	 * 
	 */
	public void run() {
		try {
			//get reference to the socket's input stream
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String inputLine;

			//continually run this loop
			while (true){

				if(running)
					//if a message is recieved
					while((inputLine = in.readLine()) != null){

						//output the message
						server.parse(inputLine);
						//server.toClients(inputLine,this.hashCode());
					}

				GameLog.outWarning("ECT", "ECT_E1100 socket readLine returned null, client closed our socket? IP " + socket.getInetAddress() + " port " + socket.getPort());
				// This is the indicator that the remote, server, closed connection
				running = false;
				// exit the entire loop
				return;
				// ToDo: exit the application or reconnect (reconnect makes sense in situation of temporary network outage, such as router reboot).

				/*
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				*/
			}
		} catch (IOException e1) {
			GameLog.outError("ECT", "ECT_E1101 Exception on reading from hosted client socket (I am server)");
			e1.printStackTrace();
		}
	}

	/**
	 * Get a reference to the thread's print writer
	 * 
	 * @return The socket output stream for this thread
	 */
	public PrintWriter getPrintWriter(){
		return out;
	}
}