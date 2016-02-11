package euchre.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import euchre.game.GameLog;
import euchre.player.GameManager;


/**
 * A thread to manage the client's network connection. A socket is created to the server, and hangs the thread
 * waiting for input. Any received input is then passed to the EuchreProtocol, which will take the appropriate
 * action based on the formatted string.
 * 
 * @author mdhelgen
 * @author Stephen A. Gutknecht
 *
 */
public class ClientNetworkManager extends Thread{ // extends NetworkManager {   abstract this out later

	public static final String HOST_LOCALHOST = "localhost";

	Socket clientSocket = null;
	PrintWriter out = null;
	BufferedReader in;
	EuchreProtocol protocol;
	GameManager manager;

	String hostname;
	int port = 4444;
	boolean connecting = true;
	boolean isConnected = false;
	boolean running = true;
	String inputLine;

	boolean debug;

	/**
	 * Create a ClientNetworkManager to connect to localhost.
	 * 
	 */
	public ClientNetworkManager(){

		hostname = HOST_LOCALHOST;
		protocol = new EuchreProtocol();
		this.debug = protocol.debug;
	}

	/**
	 * Create a ClientNetworkManager to connect to the specified hostname.
	 *  
	 * @param hostname The hostname of the server to connect to.
	 */
	public ClientNetworkManager(String hostname){

		this.hostname = hostname;
		protocol = new EuchreProtocol();
	}

	/**
	 * Register a player with the server
	 * This will create a player object with the server and associate its connection to the player id.
	 * 
	 * @param name The name of the player
	 * @param id The random id of the player
	 */
	public void registerPlayer(String name, int id){

		toServer("Name," + name + "," + id);
	}

	/**
	 * Send a tokenized message to the server. 
	 * 
	 * @param tokenizedString A comma delimited message to be interpreted by EuchreProtocol by the server
	 */
	public void toServer(String tokenizedString){
		if (debug)
			GameLog.outInformation("CNM", "sending toServer " + tokenizedString);

		out.println(tokenizedString);
	}

	/**
	 * Set reference to game manager
	 * 
	 * @param gm The game manager
	 */
	public void setGameManager(GameManager gm){
		manager = gm;
		protocol.setGameManager(gm);
	}

	/**
	 * Get reference to game manager
	 * 
	 * @return The game manager
	 */
	public GameManager getGameManager(){
		return manager;
	}

	/**
	 * The thread's actions. Make a socket connection to the server and process any input
	 * 
	 */
	public void run(){

		GameLog.outInformation("CNM", "Starting client connect loop. host " + hostname + " port " + port);

		//run this loop continually
		while(true){

			if(connecting){

				// create the new socket connection
				try {
					clientSocket = new Socket(hostname, port);

					// if the host can't be resolved, break the loop and kill the thread
				} catch (UnknownHostException e) {

					System.out.println("Unknown Host:" + hostname);
					running = false;
					break; //while(true) loop breaks, thread finishes

					// host does not exist yet
				} catch (IOException e) {
					System.out.println("Connection to server refused, retry in 5 seconds. host " + hostname + " port " + port);
					running = false;
					try {
						// wait for 5 seconds
						Thread.sleep(5000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					//restart the while loop
					continue;
				}

				GameLog.outInformation("CNM", "Client now connected to server.");
				running = true;
				isConnected = true;

				//get reference to the output stream
				try {
					out = new PrintWriter(clientSocket.getOutputStream(), true);
					in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(debug)
					System.out.println("Connected to server " +  clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " my localPort " + clientSocket.getLocalPort());

				//send a message to the server
				//out.println("CLIENT");
				connecting = false;
			}

			if (running) {
				// Note: Java design is such that this onnly tells us that our local end closed connection
				//  ref: http://stackoverflow.com/questions/10240694/java-socket-api-how-to-tell-if-a-connection-has-been-closed
				if (! clientSocket.isConnected()) {
					running = false;
					GameLog.outError("CNM", "CNM_E1000 clientSocket NOT isConnected");
					// exit the entire loop
					return;
				}
				// Note: Java design is such that this onnly tells us that our local end closed connection
				//  ref: http://stackoverflow.com/questions/10240694/java-socket-api-how-to-tell-if-a-connection-has-been-closed
				if (clientSocket.isClosed()) {
					running = false;
					GameLog.outError("CNM", "CNM_E1001 clientSocket isClosed");
					// exit the entire loop
					return;
				}

				GameLog.outInformation("CNM", "Starting clientParse loop of commands from server.");
				try {
					while((inputLine = in.readLine()) != null){

						//output the message
						if(debug)
							System.out.println("Message from server: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " msg: " + inputLine);
						protocol.clientParse(inputLine);
					}

					GameLog.outWarning("CNM", "CNM_E1100 socket readLine returned null, server closed our socket?");
					// This is the indicator that the remote, server, closed connection
					running = false;
					// exit the entire loop
					return;
				} catch (IOException e) {
					GameLog.outError("CNM", "CNM_E1101 Exception on reading from server socket");
					e.printStackTrace();
				}
			}	
		}	
	}

	public boolean isConnected(){

		return isConnected;
	}
}
