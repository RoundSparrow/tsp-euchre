package euchre.game;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import euchre.gui.*;
import euchre.player.*;
import euchre.network.*;
import javax.swing.JOptionPane;

/**
 * @author Timothy Ward
 * @author Stephen A. Gutknecht
 * 
 * This class is the highest order class of the Euchre program. It is responsible for the highest level interactions between components.
 */
public class Game {

	public static final boolean DEBUG_EXEC_OUTPUT = false;
	public static final boolean LOCALGAME_SHOW_LOBBY = true;


	/**
	 * This method is the first method called in the program. This method is 
	 * responsible for instantiating all objects and running the overall program.
	 * 
	 * @param args The String argument array.
	 * @throws InterruptedException Not thrown, the program will wait for input forever because this is not thrown.
	 */
	public static void main(String [] args) throws InterruptedException{
		try{
			//setup host and client objects, in a new game
			GameManager GM = new GameManager();

			//if this process is for a human
			if(args.length==0){

				//declare GUI welcome window to ask if host or client
				Welcome welcomeWindow = new Welcome();
				welcomeWindow.setVisible(true);

				//wait for the user to decide the game type
				while (welcomeWindow.isWinodwComplete()==false) Thread.sleep(500);

				//retrieve the user's desired game choice and dispose of the welcome window
				char gameChoice = welcomeWindow.getGameChoice();
				welcomeWindow.setVisible(false);
				welcomeWindow.dispose();

				//create the users desired player type based on the game choice
				if (gameChoice == 'h') createHostPlayer(GM);
				else if(gameChoice == 'c') createClientPlayer(GM);
				else if(gameChoice == 'a') createLocalGame(GM);
			}

			//if this process is for an AI
			else if (args.length > 0){
				if (args[0].equals("-ai")){
					GameLog.outInformation("GA", "AI client launch, params: " + Arrays.toString(args));

					//define difficulty and computer name
					String computerName = args[2];
					String difficulty = args[1];

					//create new client and join network
					ClientNetworkManager client = createNewClient(GM, "localhost");

					//make a new AI
					AI computer;

					//make a new game board and a new human to pass to the game manager
					if (difficulty == "e")      computer = new EasyAI(client);
					else if (difficulty == "m") computer = new MediumAI(client);
					else if (difficulty == "h") computer = new HardAI(client);
					else                        computer = new MediumAI(client);

					GameBoard GB = new GameBoard();
					GB.setGameManager(GM);
					GM.setGameBoard(GB);
					GM.newPlayer(computer);
					computer.setName(computerName);

					while(client.isConnected() == false) Thread.sleep(500);

					GameLog.outInformation("G", "ClientNetworkManager isConnected, send RegisterPlayer AI");
					client.toServer("RegisterPlayer,AI," + computerName + "," + difficulty + "," + computer.getPlayerID());

					//wait for everyone to join before continuing
					while(GM.areTeamsComplete() == false){
						Thread.sleep(500);
					}
				}
			}

			//play the game
			GM.playGame();

			//wait for the game to end, then display the winner and exit
			while (GM.gameWinner()==0) Thread.sleep(1000);
			JOptionPane.showMessageDialog(null, "Team " + GM.gameWinner() + " wins!!!");
			System.exit(0);
		}

		//if the program threw an error, print and exit with status code 1
		catch(Throwable t){
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * This method creates the specified number of AIs in separate instantiations of the software.
	 * 
	 * @param numberOfAIs The number of AI's to spawn.
	 * @param difficultyOfAIOne The difficulty (if any) of the first AI.
	 * @param difficultyOfAITwo The difficulty (if any) of the second AI.
	 * @param difficultyOfAIThree The difficulty (if any) of the third AI.
	 */
	private static void spawnAIs(int numberOfAIs, char difficultyOfAIOne, char difficultyOfAITwo, char difficultyOfAIThree){
		//return if the number of AIs is zero
		if(numberOfAIs == 0) return;

		//if there are more than zero AIs, spawn up to three
		try {
			String[] cmdarray = {"java", "-jar", System.getProperty("user.dir") + "/build/jar/Euchre.jar" , "-ai", "" + difficultyOfAIOne, "AI One"};

			//spawn the first AI
			if (difficultyOfAIOne != 'x'){
				GameLog.outInformation("GA", "spawnAIs difficultyOfAIOne " + difficultyOfAIOne + " args: " + Arrays.toString(cmdarray));
				executeApplicationInstance(cmdarray);
				Thread.sleep(250);
			}

			//spawn the second AI
			if (difficultyOfAITwo != 'x'){
				cmdarray[4] = "" + difficultyOfAITwo;
				if (difficultyOfAIOne == 'x') cmdarray[5] = "AI One";
				else cmdarray[5] = "AI Two";
				GameLog.outInformation("GA", "spawnAIs difficultyOfAITwo " + difficultyOfAITwo + " args: " + Arrays.toString(cmdarray));
				executeApplicationInstance(cmdarray);
				Thread.sleep(250);
			}

			//spawn the third AI
			if (difficultyOfAIThree != 'x'){
				cmdarray[4] = "" + difficultyOfAIThree;
				if (difficultyOfAIOne == 'x' && difficultyOfAITwo == 'x') cmdarray[5] = "AI One";
				else if ((difficultyOfAIOne == 'x' && difficultyOfAITwo != 'x')  ||  (difficultyOfAIOne != 'x' && difficultyOfAITwo == 'x')) cmdarray[5] = "AI Two";
				else cmdarray[5] = "AI Three";
				GameLog.outInformation("GA", "spawnAIs difficultyOfAIThree " + difficultyOfAIThree + " args: " + Arrays.toString(cmdarray));
				executeApplicationInstance(cmdarray);
			}
			Thread.sleep(3500);		
		} 
		catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static void executeApplicationInstance(String[] args) {
		try {
			if (1==1)
			{
				GameLog.outError("GM", "skipping launch");
				return;
			}

			Process process = Runtime.getRuntime().exec(args);

			if (DEBUG_EXEC_OUTPUT) {
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

				try {
					String line = "";
					while ((line = stdInput.readLine()) != null) {
						System.out.println(line);
					}
					stdInput.close();
				} catch (Exception e) {
					GameLog.outError("GA", "Exception on process stdInput");
					e.printStackTrace();
				}

				try {
					String line = "";
					while ((line = stdError.readLine()) != null) {
						System.out.println(line);
					}
					stdError.close();
				} catch (Exception e) {
					GameLog.outError("GA", "Exception on process stdError");
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will just create a host object, and will also create the appropriately
	 * specified number of AI and Human players to accompany.
	 * 
	 * @param GM The GameManager object for the network and game board.
	 * @throws InterruptedException Not thrown, the program will wait for input forever because this is not thrown.
	 */
	private static void createHostPlayer(GameManager GM) throws InterruptedException{

		//create the new host and it's game board 
		GM.newPlayer(new Human());
		GameBoard GB = new GameBoard();
		GB.setGameManager(GM);
		GM.setGameBoard(GB);

		//create a new server for the host
		ServerNetworkManager server = createNewServer(GM);

		//open the window for the user to input the game data
		HostGameSetup hostSetup = new HostGameSetup(GM);
		hostSetup.setVisible(true);

		//make the specified number of AI's once the user specifies the correct number of AIs
		while (hostSetup.setupComplete()==false) Thread.sleep(500);

		String packetPayload = "RegisterPlayer,Human," + hostSetup.getPlayerName() + ",x" + "," + GM.getp1().getPlayerID();
		server.getParser().serverParse(packetPayload, "Game_HumanSetup");
		spawnAIs(hostSetup.getNumAIs(), hostSetup.getGameLobby().getplayer2Difficulty(), hostSetup.getGameLobby().getplayer3Difficulty(), 'x');

		//wait until the user has input name and number of additional human players	
		while (hostSetup.getGameLobby() == null || hostSetup.getGameLobby().setupComplete() == false) Thread.sleep(500);

		//initialize the host's game board
		GM.initializeGameBoard(GB);

		//spawn client game boards
		server.toClients("SpawnGameBoard");
	}

	/**
	 * The method will create a local only game, it is for when a user chooses to play against
	 * three computers.
	 * 
	 * @param GM The GameManager object for the network, and game board.
	 * @throws InterruptedException Not thrown, the program will wait for input forever because this is not thrown.
	 */
	private static void createLocalGame(GameManager GM) throws InterruptedException{

		//create a window to ask for name and game info
		SetupLocal local = new SetupLocal();
		local.setVisible(true);

		//create the new host, its game board and its server
		GM.newPlayer(new Human());
		GameBoard GB = new GameBoard();
		GB.setGameManager(GM);
		GM.setGameBoard(GB);
		ServerNetworkManager server = createNewServer(GM);

		//wait for ai difficulty information, then make the ai's
		while (local.getSetupComplete() == false) Thread.sleep(500);

		// Register the local human player
		String packetPayload = "RegisterPlayer,Human," + local.getPlayerName() + ",x" + "," + GM.getp1().getPlayerID();
		server.getParser().serverParse(packetPayload, "Game_HumanSetup");

		// We will not show the lobby GUI, but we will create it for the sake of not having a null object
		GameLobby lobby = new GameLobby(1, local.getPlayerName(), GM);
		lobby.setVisible(LOCALGAME_SHOW_LOBBY);
		GM.setLobby(lobby);

		spawnAIs(3, local.getComputer1Difficulty(), local.getComputer2Difficulty(), local.getComputer3Difficulty());
		// the final launch of in spawnAIs did a 3500ms sleep

		GameLog.outInformation("GM", "after spawning AIs with sleep, connected clients, isPlayersReady? " + GM.isPlayersReady());

        // wait for ai difficulty information, then make the ai's
		while (GM.isPlayersReady() == false) Thread.sleep(500);

		if (! GM.isPlayersReady())
		{
			GameLog.outError("GM", "no isPlayersReady()");
		}
		else {
			GameLog.outInformation("GM", "I believe we have all the players, isPlayersReady is true. Advancing!");

			GM.setTeam(1, 1);
			server.toClients("SetTeam,1,1");
			GM.setTeam(2, 1);
			server.toClients("SetTeam,2,1");
			GM.setTeam(3, 2);
			server.toClients("SetTeam,3,2");
			GM.setTeam(4, 2);
			server.toClients("SetTeam,4,2");

			//initialize the host game board
			GM.initializeGameBoard(GB);

			//wait half a second for the ai's to finish spawning, then spawn the client game boards
			Thread.sleep(500L);
			GameLog.outInformation("GM", "I am host server, telling all clients to SpawnGameBoard!");
			server.toClients("SpawnGameBoard");
		}
	}

	/**
	 * This method will create a client object.
	 * 
	 * @param GM The GameManager object for the network and to pass the new client to.
	 * @param GUI The welcome window for user input.
	 * @throws InterruptedException Not thrown, the program will wait for input forever because this is not thrown.
	 */
	private static void createClientPlayer(GameManager GM) throws InterruptedException{

		//make a new game board and a new human to pass to the game manager
		Human human = new Human();
		GameBoard GB = new GameBoard();
		GB.setGameManager(GM);
		GM.setGameBoard(GB);
		GM.newPlayer(human);

		//make a new window to ask for user input
		ClientGameSetup clientSetup = new ClientGameSetup();
		clientSetup.setVisible(true);

		//wait for user to input data
		while(clientSetup.hasInput() == false) Thread.sleep(500);

		//create new client and its network from given ip address and name
		ClientNetworkManager client = createNewClient(GM, clientSetup.getIP());

		while(client.isConnected() == false) Thread.sleep(500);
		GameLog.outInformation("G", "ClientNetworkManager isConnected, send RegisterPlayer Human");
		client.toServer("RegisterPlayer,Human," + clientSetup.getClientName().trim() + ",x" + "," + human.getPlayerID());

		//wait for everyone to join before continuing
		while(GM.areTeamsComplete() == false) Thread.sleep(500);
		clientSetup.dispose();

	}

	/**
	 * This method creates a new server, and passes all of the needed references regarding it.
	 * 
	 * @param GM The GameManager that the server and it need a reference to and from.
	 * @throws InterruptedException Not thrown, the program will wait for input forever because this is not thrown.
	 */
	private static ServerNetworkManager createNewServer(GameManager GM) throws InterruptedException{
		ServerNetworkManager network = new ServerNetworkManager();
		network.setGameManager(GM);
		GM.setServerNetworkManager(network);
		network.start();
		Thread.sleep(500);
		return network;
	}

	/**
	 * This method creates a new server, and passes all of the needed references regarding it.
	 * 
	 * @param GM The GameManager that the server and it need a reference to and from.
	 * @param String The IP address that the client needs to connect to.
	 * @throws InterruptedException Not thrown, the program will wait for input forever because this is not thrown.
	 */
	private static ClientNetworkManager createNewClient(GameManager GM, String ip) throws InterruptedException{
		ClientNetworkManager client;
		if (ip.equals("localhost")) client = new ClientNetworkManager();
		else client = new ClientNetworkManager(ip);
		GM.setClientNetworkManager(client);
		client.setGameManager(GM);
		client.start();
		Thread.sleep(500);
		return client;
	}

}
