package TradingCardGame;
import java.util.*;

public class GameState {

	public ArrayList<Card> playerDeck = new ArrayList<Card>();
	public ArrayList<Card> opponentDeck = new ArrayList<Card>();
	public ArrayList<Card> playerHand = new ArrayList<Card>();
	public ArrayList<Card> opponentHand = new ArrayList<Card>();
	public ArrayList<Card> playerDiscard = new ArrayList<Card>();
	public ArrayList<Card> opponentDiscard = new ArrayList<Card>();

	public ArrayList<Card> playerBench = new ArrayList<Card>(); //0 = the active Pokemon
	public ArrayList<Card> opponentBench = new ArrayList<Card>();
	
	public int startingPrizes;
	public ArrayList<Card> playerPrizes = new ArrayList<Card>();
	public ArrayList<Card> opponentPrizes = new ArrayList<Card>();
	
	GameState(Card[] player, Card[] opponent, int prizes) {
		//Starts a game
		for(int i = 0; i < player.length; i++) playerDeck.add(player[i]);
		for(int j = 0; j < opponent.length; j++) opponentDeck.add(opponent[j]);
		
		for(int i = 0; i < 7; i++) {
			playerHand.add(playerDeck.get(0));
			playerDeck.remove(0);
			opponentHand.add(opponentDeck.get(0));
			opponentDeck.remove(0);
		}
		
		startingPrizes = prizes;
		for(int i = 0; i < prizes; i++) {
			playerPrizes.add(playerDeck.get(0));
			playerDeck.remove(0);
			opponentPrizes.add(opponentDeck.get(0));
			opponentDeck.remove(0);
		}
	}
	
	public void resetGame() {
		 playerDeck = new ArrayList<Card>();
		 opponentDeck = new ArrayList<Card>();
		 playerHand = new ArrayList<Card>();
		 opponentHand = new ArrayList<Card>();
		 playerDiscard = new ArrayList<Card>();
		 opponentDiscard = new ArrayList<Card>();

		 playerBench = new ArrayList<Card>(); //0 = the active Pokemon
		 opponentBench = new ArrayList<Card>();
		
		 int startingPrizes;
		 int playerPrizes;
		 int opponentPrizes;
	}
	public boolean fullBench() {
		return playerBench.size() >= 6 ? true : false;
	}
}
