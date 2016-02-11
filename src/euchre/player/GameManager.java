package euchre.player;

import euchre.game.GameLog;
import euchre.game.GameRunnerCallback;
import euchre.gui.*;
import euchre.network.ClientNetworkManager;
import euchre.network.ServerNetworkManager;

/**
 * 
 * @author krkary
 * @author Stephen A. Gutknecht
 *
 * The game manager acts as a sort of dealer. It asks each player if they want to order up a card, pick a suit, play a card, etc. in addition
 * to dealing the cards each hand. Also, the game manager passes important information on to the Round class, which in turn stores it to help 
 * run the game.
 */
public class GameManager {

	private boolean playersReady = false;
	private Player p1,p2,p3,p4;
	private Player playerIAm;
	private Player player1, player2, player3, player4;
	private Player dealer = player1;
	private Card upCard;
	private Deck deck = new Deck();
	private Team teamOne = new Team(null, null);
	private Team teamTwo = new Team(null, null);
	private GameLobby lobby;
	private boolean teamsComplete = false;
	private GameBoard board;
	private ServerNetworkManager server = null;
	private ClientNetworkManager client = null;
	private int currentTurnPlayerID;
	private char trump;
	private int TeamOneScore=0;
	private int TeamTwoScore=0;

	Card[] hand1 = new Card[5];
	Card[] hand2 = new Card[5];
	Card[] hand3 = new Card[5];
	Card[] hand4 = new Card[5];


	/**
	 * Begins gameplay.  If the current game manager is the server, then it will begin playing the game.
	 */
	public void playGame(){
		
		if(server != null){
			playRound();
		}
	}

	/**
	 * Plays a round of Euchre, consisting of five hands.
	 * Sets a new round in the board and deals the cards to the players.
	 */
	public void playRound(){
		board.newRound();
		if(server!=null) deal();
	}


	/**
	 * Creates a new deck of cards, shuffles the cards, and then
	 * deals five cards to each player..
	 *
	 * ToDo: review rules to see that cards are dealt in the correct compliance with rules
	 * ToDo: add an option for the player to cut the deck
	 * reference on dealing and cutting deck: http://www.bicyclecards.com/how-to-play/euchre/
	 */
	private void deal(){
		GameLog.outInformation("GM", "game deal()");
		deck = new Deck();									//Create a brand new deck of cards
		deck.shuffle();										//Shuffle the deck of cards

		for(int i=0;i<5;i++){
			hand1[i]=deck.drawCard();
			hand2[i]=deck.drawCard();
			hand3[i]=deck.drawCard();
			hand4[i]=deck.drawCard();
		}

		for(int i=0;i<5;i++){
			player1.getHand()[i] = hand1[i];
			player2.getHand()[i] = hand2[i];
			player3.getHand()[i] = hand3[i];
			player4.getHand()[i] = hand4[i];
		}

		upCard = deck.drawCard();
		board.setTurnedCard(upCard);
		board.setDealerName(dealer.getName());

		server.toClients("SetHand,1,"+player1.getHand()[0]+","+player1.getHand()[1]+","+player1.getHand()[2]+","+
				player1.getHand()[3]+","+player1.getHand()[4]);

		server.toClients("SetHand,2,"+player2.getHand()[0]+","+player2.getHand()[1]+","+player2.getHand()[2]+","+
				player2.getHand()[3]+","+player2.getHand()[4]);

		server.toClients("SetHand,3,"+player3.getHand()[0]+","+player3.getHand()[1]+","+player3.getHand()[2]+","+
				player3.getHand()[3]+","+player3.getHand()[4]);

		server.toClients("SetHand,4,"+player4.getHand()[0]+","+player4.getHand()[1]+","+player4.getHand()[2]+","+
				player4.getHand()[3]+","+player4.getHand()[4]);

		server.toClients("SetTurnedCard,"+upCard);

		initializeGameBoard(board);
		int next = nextPlayer(dealer).getPlayerID();
		server.toClients("SetDealerName," + dealer.getName());
		server.toClients("SetPlayerTurn," + next);
		setTurnPlayerID(next);
	}

	/**
	 * This method initializes the GameBoard.
	 * 
	 * @param GM The GameManager.
	 * @param GB The GameBoard.
	 */
	public void initializeGameBoard(GameBoard GB) {
		if (GameRunnerCallback.settingShowGameBoardForAI())
		{
			GB.setVisible(true);
		}
		else {
			// AI clients don't actually need a graphical interface
			if (GB.getGM().getPlayerIAm().isHuman()) {
				GB.setVisible(true);
			}
		}
		GB.updateBoard();
	}

	/**
	 * Adds all client and host players. 
	 * 
	 * @param host The human player that is going to be the host of the game. 
	 * @param client1 The first player that is going to be a client in the game.
	 * @param client2 The second player that is going to be a client in the game.
	 * @param client3 The third player that is going to be a client in the game.
	 */
	public void setAllPlayers(Player host, Player client1, Player client2, Player client3){

		if (p1.getPlayerID()==host.getPlayerID()){
			playerIAm=host;
		}
		else if (p1.getPlayerID()==client1.getPlayerID()){
			playerIAm=client1;
		}
		else if (p1.getPlayerID()==client2.getPlayerID()){
			playerIAm=client2;
		}
		else if (p1.getPlayerID()==client3.getPlayerID()){
			playerIAm=client3;
		}
		p1=host;
		p2=client1;
		p3=client2;
		p4=client3;

		GameLog.outInformation("GM", "setAllPlayers completed, playersReady!");
		playersReady = true;
	}

	/**
	 * Sets the given player on the given team
	 * @param player The number of the player
	 * @param team The team to put that player on
	 */
	public void setTeam(int player, int team){
		Player play;

		//Determines which player object is being referred to
		if(player==1) play = p1;
		else if(player==2) play = p2;
		else if(player==3) play = p3;
		else play = p4;

		if (play==null)
		{
			GameLog.outError("GA", "play is null, aborting CODE_A3000. team: " + team);
			return;
		}
		//Sets the given player on the given team by putting that player in the corresponding "seat"
		//and then setting all of the appropriate team references
		if(team==1 && player1==null){
			player1=play;
			player1.setTeam(1);
			player1.setNumber(1);
		}
		else if(team==1 && player1!=null){
			player3=play;
			player3.setTeam(1);
			player3.setNumber(3);
		}
		else if(team==2 && player2==null){
			player2=play;
			player2.setTeam(2);
			player2.setNumber(2);
		}
		else if(team==2 && player2!=null){
			player4=play;
			player4.setTeam(2);
			player4.setNumber(4);
		}

		teamOne = new Team(player1,player3);
		teamOne.setTeamNumber(1);
		teamTwo = new Team(player2,player4);
		teamTwo.setTeamNumber(2);
		dealer = player1;
		teamsComplete = true;
		GameLog.outInformation("GA", "setTeam finished, teamsComplete now true, team: " + team + " player " + player);
	}

	/**
	 * Sets the next players turn by determining which player's turn it currently is.
	 * To avoid any confusion or problems, this works with the unique ID numbers of each player.
	 */
	public void setNextPlayerTurn(){

		if(currentTurnPlayerID == player1.getPlayerID()){
			currentTurnPlayerID = player2.getPlayerID();
			board.updateBoard();
		}
		else if (currentTurnPlayerID == player2.getPlayerID()){
			currentTurnPlayerID = player3.getPlayerID();
			board.updateBoard();
		}
		else if (currentTurnPlayerID == player3.getPlayerID()){
			currentTurnPlayerID = player4.getPlayerID();
			board.updateBoard();
		}
		else if (currentTurnPlayerID == player4.getPlayerID()){
			currentTurnPlayerID = player1.getPlayerID();
			board.updateBoard();
		}
	}

	public Player getCurrentTurnPlayer()
	{
		if(currentTurnPlayerID == player1.getPlayerID()){
			return player1;
		}
		else if (currentTurnPlayerID == player2.getPlayerID()){
			return player2;
		}
		else if (currentTurnPlayerID == player3.getPlayerID()){
			return player3;
		}
		else if (currentTurnPlayerID == player4.getPlayerID()){
			return player4;
		}
		else
		{
			GameLog.outError("GM", "getCurrentTurnPlayer unable to match currentTurnPlayerID " + currentTurnPlayerID);
			return null;
		}
	}

	/**
	 * 
	 * This method accepts a round object and interprets it relative to the game.
	 * 
	 * @param one The first team to be interpreted.
	 * @param two The second team to be interpreted.
	 */
	public void interpretRound(int tOne, int tTwo){
		int teamOneTricks = tOne;
		int teamTwoTricks = tTwo;

		if(teamOneTricks == 5 && board.getTeamWhoOrdered().equals(teamOne))	TeamOneScore=TeamOneScore+2;
		else if (teamTwoTricks == 5 && board.getTeamWhoOrdered().equals(teamTwo)) TeamTwoScore=TeamTwoScore+2;
		else if (teamOneTricks >= 3 && board.getTeamWhoOrdered().equals(teamOne)) TeamOneScore++;
		else if (teamTwoTricks >= 3 && board.getTeamWhoOrdered().equals(teamTwo)) TeamTwoScore++;
		else if(teamOneTricks < 3 && board.getTeamWhoOrdered().equals(teamOne)) TeamTwoScore+=2;
		else if(teamTwoTricks < 3 && board.getTeamWhoOrdered().equals(teamTwo)) TeamOneScore+=2;
		else System.out.println("ERROR: THE ROUND WINNER WAS NOT CORRECTLY DETERMINED");

		if(teamOne.getPlayerOne().equals(playerIAm) || teamOne.getPlayerTwo().equals(playerIAm)){
			board.setWePoints(TeamOneScore);
			board.setTheyPoints(TeamTwoScore);
		}
		if(teamTwo.getPlayerOne().equals(playerIAm) || teamTwo.getPlayerTwo().equals(playerIAm)){
			board.setWePoints(TeamTwoScore);
			board.setTheyPoints(TeamOneScore);
		}
	}

	public void setTrump(char trump) {
		this.trump = trump;
		board.setTrumpLabel(trump);
		board.trumpSet();
	}

	/**
	 * Finds the next player after a given player
	 * @param p Given player
	 * @return The player after the given player
	 */
	public Player nextPlayer(Player p) {
		if(p.equals(player1)) return player2;
		else if(p.equals(player2)) return player3;
		else if(p.equals(player3)) return player4;
		else return player1;
	}

	/**
	 * This method compares the scores of the two teams and returns the winning team
	 * if there is one or null otherwise.
	 * 
	 * @return Team The winning team
	 */
	public int gameWinner(){
		if(TeamOneScore >= 10){
			return 1;
		}
		else if(TeamTwoScore >= 10){
			return 2;
		}
		else{
			return 0;
		}
	}

	public void newPlayer(Player p){
		p1=p;
	}

	public Player getDealer(){
		return dealer;
	}

	public Player getPlayerIAm(){
		return playerIAm;
	}

	public Player getPlayer1(){
		return player1;
	}

	public Player getPlayer2(){
		return player2;
	}

	public Player getPlayer3(){
		return player3;
	}

	public Player getPlayer4(){
		return player4;
	}

	public Player getp1(){
		return p1;
	}
	public Player getp2(){
		return p2;
	}
	public Player getp3(){
		return p3;
	}
	public Player getp4(){
		return p4;
	}

	public void setGameBoard(GameBoard gb){
		board = gb;
	}

	public GameBoard getGameBoard(){
		return board;
	}

	public boolean areTeamsComplete(){
		return teamsComplete;
	}
	public void setTeamsComplete(boolean b){
		teamsComplete = b;
	}

	public void setServerNetworkManager(ServerNetworkManager s){
		server = s;
	}

	public void setClientNetworkManager(ClientNetworkManager c){
		client = c;
	}

	public ServerNetworkManager getServerNetworkManager(){
		return server;
	}

	public ClientNetworkManager getClientNetworkManager(){
		return client;
	}

	public boolean isServer(){
		return (client==null);
	}

	public boolean isPlayersReady() { return playersReady; }

	public void setTurnPlayerID(int id){
		currentTurnPlayerID = id;
		Player testCurrentPlayer = getCurrentTurnPlayer();
		board.updateBoard();
	}

	public int getCurrentTurnPlayerID(){
		return currentTurnPlayerID;
	}

	public boolean isMyTurn(){
		return currentTurnPlayerID == playerIAm.getPlayerID();
	}

	public boolean isDealer(){
		return playerIAm.getPlayerID() == dealer.getPlayerID();
	}

	public char getTrump() {
		return trump;
	}

	public void setp1(Player player1){
		this.p1 = player1;
	}
	public GameLobby getLobby() {
		return lobby;
	}

	public void setLobby(GameLobby lobby) {
		this.lobby = lobby;
	}

	public void setDealer(Player newDealer){
		dealer = newDealer;
	}
	public Team getTeamOne(){
		return teamOne;
	}

	public Team getTeamTwo(){
		return teamTwo;
	}
	/**
	 * Sets the player for the current game manager
	 * @param p The player to set
	 */
	public void setPlayerIAm(Player p){
		if (p1.equals(playerIAm)){
			p1 = p;
			p1.setPlayerID(playerIAm.getPlayerID());
		}
		if (p2.equals(playerIAm)){
			p2 = p;
			p1.setPlayerID(playerIAm.getPlayerID());
		}
		if (p3.equals(playerIAm)){
			p3 = p;
			p1.setPlayerID(playerIAm.getPlayerID());
		}
		if (p4.equals(playerIAm)){
			p4 = p;
			p1.setPlayerID(playerIAm.getPlayerID());
		}
		playerIAm = p;
	}
}