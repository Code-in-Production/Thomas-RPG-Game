package TradingCardGame;
import java.awt.Color;
import java.awt.Event;
import java.util.*;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.*;
import net.dv8tion.jda.api.hooks.*;

/**
 * 
 * @author zhao
 * 
 * Things to add:
 * -You can still evolve on the first turn.
 * -Better AI
 * -Trainer cards
 * -More cards
 * -More effects
 * -Deck building screen
 */

public class Game extends ListenerAdapter {
	
	public static int state = 1;
	//As you progress through the game, the help pages update based on the state.
	//0 = do not accept any commands
	//1 = before starting the game
	//2 = name your character
	//3 = instructions
	//4 = pick starting Pokemon
	//5 = pick bench Pokemon
	//6 = your turn
	//7 = attaching energy to a Pokemon
	//8 = evolving a Pokemon
	public static GameState gameState;
	
	public static String name = "";
	
	public static int playingCardIndex = -1;
	
	public static GuildMessageReceivedEvent firstEvent;
	
	public static Card[] l = { //list of All Cards
			//Type, Weakness, Name, ID, stage, pre-evolution ID (0 if basic), attack1cost, attack1damage, attack2cost, attack2damage
		new Card(5, 0, "Energy", 		0, 0, 0, 0, 0, 0, 0, 0),
		new Card(2, 0, "Heatooth", 		1, 0, 0, 1, 10, 2, 30, 60),
		new Card(2, 0, "Embite", 		2, 1, 1, 2, 30, 3, 50, 90),
		new Card(2, 0, "Blazefang", 	3, 2, 2, 3, 50, 4, 80, 110),
		new Card(1, 0, "Riverush",		4, 0, 0, 1, 10, 1, 0, 30),
		new Card(1, 0, "Lakespeed", 	5, 1, 4, 2, 20, 2, 30, 50),
		new Card(1, 0, "Seaboost", 		6, 2, 5, 3, 60, 0, 0, 70),
		new Card(0, 0, "Seed-saw", 		7, 0, 0, 1, 20, 0, 0, 40),	
		new Card(0, 0, "Leaf-saw", 		8, 1, 7, 1, 20, 2, 30, 80),
		new Card(0, 0, "Tree-saw", 		9, 2, 8, 2, 40, 4, 80, 120),	
		new Card(3, 0, "Rookie", 		10, 0, 0, 1, 10, 2, 20, 50),
		new Card(3, 0, "Spearook", 		11, 1, 10, 1, 20, 2, 30, 70),	
		new Card(3, 0, "Castlerook", 	12, 2, 11, 3, 60, 3, 60, 90),
		new Card(0, 0, "Tracktor", 		13, 0, 0, 1, 10, 2, 20, 80),
		new Card(0, 0, "Tranion", 		14, 1, 13, 1, 0, 2, 40, 90),
		//new Card(6, 0, "Potion", 		15, 0, 0, 0, 0, 0, 0, 0),

		
		
	};
	
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		Random rand = new Random();
		if(event.getAuthor().getName().equals("TradingCardGameBot")) return;
		
		
		
		String[] command = event.getMessage().getContentRaw().split(" ");
		
		if(state == 1) {
			if(command[0].equalsIgnoreCase(Main.prefix + "start")) {
				event.getChannel().sendMessage("What's your name? (Place an exclamation mark (!) before your name.)").queue();
				firstEvent = event;
				state = 2;
			} 
		} else if(state == 2) {
			if(command[0].startsWith(Main.prefix)) {
				name = command[0].substring(1);
				state = 0;
				event.getChannel().sendMessage(name + " is a player of the Pokemon Trading Card Game!").queue();
				event.getChannel().sendMessage("One day, " + name + " hears a rumor:").queue();
				event.getChannel().sendMessage("\"The grand master is waiting for someone else to inherit "
						+ "the legendary Pokemon cards!\"").queue();
				event.getChannel().sendMessage("Seeking the legendary cards, " + name + 
						" visits the card researcher Dr. Smith.").queue();

				event.getChannel().sendMessage("Dr. Smith: You want to inherit the legendary Pokemon cards?").queue();
				event.getChannel().sendMessage("Well, dueling is more fun than just collecting cards!").queue();
				event.getChannel().sendMessage("First thing, you need to learn how to play the game.").queue();

				event.getChannel().sendMessage("What would you like to ask about the game?").queue();
				event.getChannel().sendMessage("Options:").queue();
				event.getChannel().sendMessage("!Object").queue();
			//	event.getChannel().sendMessage("!Board").queue();
			//	event.getChannel().sendMessage("!Turns").queue();
			//	event.getChannel().sendMessage("!Attacking").queue();
			//	event.getChannel().sendMessage("!Types").queue();
				event.getChannel().sendMessage("!Nothing").queue();
				state = 3;
			}
		} else if(state == 3) {
			if(command[0].equalsIgnoreCase(Main.prefix + "object")) {
				event.getChannel().sendMessage("The object of the game is to knock out all of your opponent's Pokemon or draw"
						+ "all the prize cards; usually 5 prizes. You do this by sending your Pokemon "
						+ "into battle and attacking your opponent's Pokemon."
						+ "You also win if your opponent runs out of cards in his or her deck.").queue();
			} else if(command[0].equalsIgnoreCase(Main.prefix + "board")) {
				
			} else if(command[0].equalsIgnoreCase(Main.prefix + "turns")) {
				
			} else if(command[0].equalsIgnoreCase(Main.prefix + "attacking")) {
				
			} else if(command[0].equalsIgnoreCase(Main.prefix + "types")) {
				
			} else if(command[0].equalsIgnoreCase(Main.prefix + "nothing")) {
				state = 0;
				event.getChannel().sendMessage("Dr. Smith: They say that actions speak louder than words, so let's play a game.").queue();
				//event.getChannel().sendMessage("I'll be coaching you, so follow my advice. If you don't do as I say, we won't be "
					//	+ "able to proceed.").queue();
				event.getChannel().sendMessage("OK, then let's start the practice game!").queue();
				
				event.getChannel().sendMessage(":black_large_square::white_large_square::black_large_square::white_large_square:"
						+ ":black_large_square::white_large_square::black_large_square::white_large_square:").queue();
				event.getChannel().sendMessage("Dr. Smith: Since this is a practice duel, do not shuffle the deck."
						+ " Each player draws 7 cards.").queue();
				event.getChannel().sendMessage("Dr. Smith: Draw 7 cards, and get ready for the battle!").queue();
			//	event.getChannel().sendMessage("Choose your starting Active Pokemon. You can only choose Basic Pokemon to be your"
				//		+ "active Pokemon, so you can either choose Seed-saw or Riverush. For our practice duel, choose Seed-saw.");
				event.getChannel().sendMessage("For now, do this battle on your own.").queue();
				
				Card[] playerDeck = {							//hand		//prizes
						l[7], l[0], l[8], l[4], l[0], l[0], l[8], l[0], l[0], l[0],
																//last card drawn
						l[0], l[1], l[5], l[7], l[0], l[9], l[0], l[4], l[5], l[6],
						l[1], l[0], l[3], l[2], l[0], l[0], l[0], l[0], l[0], l[0],
						l[0], l[0], l[0], l[0], l[0], l[0], l[0], l[0], l[0], l[0],
						l[0], l[0], l[0], l[0], l[0], l[0], l[0], l[0], l[0], l[0]
				};
				Card[] opponentDeck = {								//hand		//prizes
						l[13], l[0], l[13], l[10], l[0], l[0], l[0], l[0], l[0], l[13],
															//last card drawn
						l[0], l[11], l[0], l[14], l[0], l[0], l[13], l[0], l[14], l[0],
						l[10], l[0], l[11], l[12], l[0], l[0], l[0], l[0], l[0], l[0],
						l[0], l[0], l[0], l[0], l[0], l[0], l[0], l[0], l[0], l[0],
						l[0], l[0], l[0], l[0], l[0], l[0], l[0], l[0], l[0], l[0]
				};
				gameState = new GameState(playerDeck, opponentDeck, 3);
				opponentSetup();
				event.getChannel().sendMessage("Choose your starting active Pokemon by name, in the form !play (name).").queue();
				for(int i = 0; i < gameState.playerHand.size(); i++) {
					event.getChannel().sendMessage(gameState.playerHand.get(i).describe()).queue();
				}
				state = 4;
			} else if(command[0].startsWith(Main.prefix)) {
				help(event);
			}
		} else if(state == 4) {
			if(command[0].equals(Main.prefix + "play") && command.length > 0) {

				String choice = command[1];
				
				for(int i = 0; i < gameState.playerHand.size(); i++) {
					if(gameState.playerHand.get(i).stage != 0 || gameState.playerHand.get(i).type >= 5) continue;
					if(choice.equalsIgnoreCase(gameState.playerHand.get(i).name)) {
						gameState.playerBench.add(gameState.playerHand.get(i));
						gameState.playerHand.remove(i);
						event.getChannel().sendMessage(gameState.playerBench.get(0).name + " was selected.").queue();
						event.getChannel().sendMessage("Select the Pokemon to be placed onto the bench.").queue();
						state = 5;
						return;
					}
				}
				
				event.getChannel().sendMessage("Make sure that you play a Basic Pokemon from your hand.").queue();
			} else if(command[0].startsWith(Main.prefix)) {
				help(event);
			}
		} else if(state == 5) {
			if(command[0].equals(Main.prefix + "play") && command.length > 0) {

				String choice = command[1];
				
				for(int i = 0; i < gameState.playerHand.size(); i++) {
					if(gameState.playerHand.get(i).stage != 0 || gameState.playerHand.get(i).type >= 5) {
						if(choice.equalsIgnoreCase(gameState.playerHand.get(i).name)) {
							event.getChannel().sendMessage("Make sure that you play a Basic Pokemon from your hand.").queue();
							return;
						}
						
						continue;
					}
					if(choice.equalsIgnoreCase(gameState.playerHand.get(i).name)) {
						if(gameState.fullBench()) {
							event.getChannel().sendMessage("Your Bench is full.").queue();
						} else {
							event.getChannel().sendMessage(gameState.playerHand.get(i).name + " was placed on the bench.").queue();
							gameState.playerBench.add(gameState.playerHand.get(i));
							gameState.playerHand.remove(i);
							return;
						}
					}
				}
				
			} else if(command[0].startsWith(Main.prefix + "done") && command.length > 0) { 
				state = 0;
				event.getChannel().sendMessage("Place " + gameState.startingPrizes + " prize cards.").queue();
				event.getChannel().sendMessage("Flip a coin to see who goes first.").queue();
				if(rand.nextInt(1) == 0) {
					event.getChannel().sendMessage("You go first.").queue();
					if(gameState.playerDeck.size() == 0) {
						event.getChannel().sendMessage("Unable to draw a card.").queue();
						event.getChannel().sendMessage("You lost the duel with Sam!").queue();
						state = 0;
						gameState.resetGame();
					} else {
						event.getChannel().sendMessage("Draw a card.").queue();
						gameState.playerHand.add(gameState.playerDeck.get(0));
						gameState.playerDeck.remove(0);
					}
					state = 6;
				} else {
					event.getChannel().sendMessage("You go second.").queue();
					opponentTakesTurn();
				}
			} else if(command[0].startsWith(Main.prefix)) {
				help(event);
			}
		} else if(state == 6) {
			//Show the hand at all times
			if(command[0].equals(Main.prefix + "viewhand")) {
				for(Card i : gameState.playerHand) {
					event.getChannel().sendMessage(i.describe()).queue();
				}
			}
			//Show the board
			else if(command[0].equals(Main.prefix + "viewboard")) {
				event.getChannel().sendMessage("`Opponent's side`").queue();
				event.getChannel().sendMessage("`Hand: " + gameState.opponentHand.size() + "`").queue();
				event.getChannel().sendMessage("`Deck: " + gameState.opponentDeck.size() + "     Discard: " +
				gameState.opponentDiscard.size() + "`").queue();
				event.getChannel().sendMessage("`Bench`").queue();
				for(int i = 1; i < gameState.opponentBench.size(); i++) {
					event.getChannel().sendMessage(gameState.opponentBench.get(i).describeSymbols()).queue();
				}
				event.getChannel().sendMessage("`Active`").queue();
				event.getChannel().sendMessage(gameState.opponentBench.get(0).describeSymbols()).queue();
				event.getChannel().sendMessage("`------------------------`").queue();
				event.getChannel().sendMessage("`Active`").queue();
				event.getChannel().sendMessage(gameState.playerBench.get(0).describeSymbols()).queue();
				event.getChannel().sendMessage("`Bench`").queue();
				for(int i = 1; i < gameState.playerBench.size(); i++) {
					event.getChannel().sendMessage(gameState.playerBench.get(i).describeSymbols()).queue();
				}
				event.getChannel().sendMessage("`Deck: " + gameState.playerDeck.size() + "     Discard: " +
						gameState.playerDiscard.size() + "`").queue();
				event.getChannel().sendMessage("`Hand: " + gameState.playerHand.size() + "`").queue();
				event.getChannel().sendMessage("`Your side`").queue();

			}
			//Play a card from your hand
			else if(command[0].equals(Main.prefix + "play") && command.length > 0) {
				//Note: two identical cards are basically the same
				String card = command[1];
				for(int i = 0; i < gameState.playerHand.size(); i++) {
					if(gameState.playerHand.get(i).name.equalsIgnoreCase(card)) {
						Card cardPlayed = gameState.playerHand.get(i);
						playingCardIndex = i;
						if(cardPlayed.type == 5) {
							event.getChannel().sendMessage("Please enter the index of the Pokemon you would like to "
									+ "attach energy to (0 = active, 1-5 for bench spaces), or !cancel to cancel.").queue();
							state = 7;
							return;
						} else if(cardPlayed.type == 6) {
							event.getChannel().sendMessage("Functionality for Trainer Cards will be added in the"
									+ " next update.").queue();
							return;
						} else if(cardPlayed.stage == 0) {
							if(gameState.fullBench()) {
								event.getChannel().sendMessage("Your Bench is full.").queue();
							} else {
								gameState.playerBench.add(gameState.playerHand.get(i));
								gameState.playerHand.remove(i);
							}
							return;
						} else {
							event.getChannel().sendMessage("Please enter the index of the Pokemon you would like to "
									+ "evolve. (0 = active, 1-5 for bench spaces), or !cancel to cancel.").queue();
							state = 8;
							return;
						}
					}
					
				}
			}
			
			//Attack and end turn
			else if(command[0].equals(Main.prefix + "attack")) {
				event.getChannel().sendMessage("Attacks: ").queue();
				int attack1cost = gameState.playerBench.get(0).attack1cost, attack2cost = gameState.playerBench.get(0).attack2cost;
				int attack1damage = gameState.playerBench.get(0).attack1damage, attack2damage = gameState.playerBench.get(0).attack2damage;
				
				event.getChannel().sendMessage("Energy attached: " + gameState.playerBench.get(0).attachedEnergy).queue(); 
				event.getChannel().sendMessage(attack1cost + " " + attack1damage).queue(); 
				if(attack2cost != 0) event.getChannel().sendMessage(attack2cost + " " + attack2damage).queue();
				event.getChannel().sendMessage("Please select attack 1, " + (attack2cost == 0 ? "" : "attack 2, ") + "or !cancel.").queue(); 
				state = 9;
			}
			
			//End the turn without attacking
			else if(command[0].equals(Main.prefix + "endturn")) {
				event.getChannel().sendMessage("Ended the turn without attacking.").queue();
				state = 0;
				opponentTakesTurn();
			}
			
			else if(command[0].startsWith(Main.prefix)) {
				help(event);
			}
			
			
		} else if(state == 7) {
			for(int i = 0; i < 5; i++) {
				if(command[0].equals(Main.prefix + "" + i)) {
					if(gameState.playerBench.size() <= i) {
						event.getChannel().sendMessage("You don't have that many Pokemon.").queue();
						return;
					} else {
						gameState.playerHand.remove(playingCardIndex);
						gameState.playerBench.get(i).attachedEnergy++;
						event.getChannel().sendMessage("Attached energy to " + gameState.playerBench.get(i).name + ".").queue();
						playingCardIndex = -1;
						state = 6;
						return;
					}
					
				}
			}
			if(command[0].equals(Main.prefix + "cancel")) {
				event.getChannel().sendMessage("Cancelled. Please continue your turn.").queue();
				state = 6;
			} 
			else if(command[0].startsWith(Main.prefix)) {
				help(event);
			}
		} else if(state == 8) {
			for(int i = 0; i < 5; i++) {
				if(command[0].equals(Main.prefix + "" + i)) {
					if(gameState.playerBench.size() <= i) {
						event.getChannel().sendMessage("You don't have that many Pokemon.").queue();
					} else if(gameState.playerHand.get(playingCardIndex).pre_id == gameState.playerBench.get(i).id) {
						event.getChannel().sendMessage(gameState.playerBench.get(i).name + " evolved into " +
					gameState.playerHand.get(playingCardIndex).name + ".").queue();

						int missingHP = gameState.playerBench.get(i).HP - gameState.playerBench.get(i).curHP;
						int curEnergy = gameState.playerBench.get(i).attachedEnergy;
						gameState.playerBench.set(i, gameState.playerHand.get(playingCardIndex));
						gameState.playerBench.get(i).attachedEnergy = curEnergy;
						gameState.playerBench.get(i).curHP = gameState.playerBench.get(i).HP - missingHP;
						gameState.playerHand.remove(playingCardIndex);
						
						playingCardIndex = -1;
						state = 6;
					} else {
						event.getChannel().sendMessage(gameState.playerHand.get(playingCardIndex).name + " can't evolve from "
								+ gameState.playerBench.get(i).name + "!").queue();
					}
					return;
				}
			}
			if(command[0].equals(Main.prefix + "cancel")) {
				event.getChannel().sendMessage("Cancelled. Please continue your turn.").queue();
				state = 6;
			}
			else if(command[0].startsWith(Main.prefix)) {
				help(event);
			}
		} else if(state == 9) {
			if(command[0].equals(Main.prefix + "1")) {
				if(gameState.playerBench.get(0).attachedEnergy >= gameState.playerBench.get(0).attack1cost) {
					state = 0;
					event.getChannel().sendMessage(gameState.playerBench.get(0).name + "'s attack 1!").queue();
					event.getChannel().sendMessage(gameState.opponentBench.get(0).name + " took " + 
							gameState.playerBench.get(0).attack1damage + " damage.");
					
					gameState.opponentBench.get(0).curHP -= gameState.playerBench.get(0).attack1damage;
					if(gameState.opponentBench.get(0).curHP < 0) gameState.opponentBench.get(0).curHP = 0;
					gameState.opponentBench.get(0).describeSymbols();
					if(gameState.opponentBench.get(0).curHP == 0) {
						event.getChannel().sendMessage(gameState.opponentBench.get(0).name + " was knocked out!").queue();
						event.getChannel().sendMessage("You drew a prize card.").queue();
						gameState.playerHand.add(gameState.playerPrizes.get(0));
						gameState.playerPrizes.remove(0);
						if(gameState.playerPrizes.size() == 0) {
							event.getChannel().sendMessage("You drew all the prizes. You win the duel.").queue();
							gameState.resetGame();
							state = 10;
						} else {
							opponentReplacePokemon();
						}
					} else {
						opponentTakesTurn();
					}
				} else {
					event.getChannel().sendMessage("Not enough energy.").queue();

				}
			} else if(command[0].equals(Main.prefix + "2") && gameState.playerBench.get(0).attack2cost != 0) {
				if(gameState.playerBench.get(0).attachedEnergy >= gameState.playerBench.get(0).attack2cost) {
					state = 0;
					opponentTakesTurn();
				} else {
					event.getChannel().sendMessage("Not enough energy.").queue();

				}
			} else if(command[0].equals(Main.prefix + "cancel")) {
				event.getChannel().sendMessage("Cancelled. Please continue your turn.").queue();
				state = 6;
			}
			else if(command[0].startsWith(Main.prefix)) {
				help(event);
			}
		}
		
		else if(command[0].startsWith(Main.prefix)) {
			//event.getChannel().sendMessage("Enter !start to start the game!");

			help(event);
		}
	}
	
	public void help (GuildMessageReceivedEvent event) {
		EmbedBuilder help = new EmbedBuilder();
		help.setColor(Color.BLUE);
		help.setDescription("Help");
		help.addField("!", "Opens the help menu. Place an ! before every command.", false);
		if(state == 1) help.addField("!start", "Start the game.", false);
		if(state == 3) {
			help.addField("!object", "Learn about the object of the game.", false);
		//	help.addField("!board", "Learn about how to view the board.", false);
		//	help.addField("!turns", "How every turn goes.", false);
		//	help.addField("!attacking", "How to attack.", false);
			help.addField("!nothing", "Nothing to ask.", false);
		}
		if(state == 4) {
			help.addField("!play (name)", "Place card (name) onto the Active Pokemon space. It has to be a Basic Pokemon.", false);
		}
		if(state == 5) {
			help.addField("!play (name)", "Place card (name) onto the Bench Pokemon space. It has to be a Basic Pokemon.", false);
			help.addField("!done", "Finish this step.", false);
		}
		if(state == 6) {
			help.addField("!viewhand", "View your hand.", false);
			help.addField("!viewboard", "View the board.", false);
			help.addField("!play (name)", "Play a card from your hand by name. If the card is a Basic Pokemon, places it on the bench."
					+ " If the card is an Energy card, you will be asked to decide which Pokemon to attach it to. "
					+ "If it is an evolved Pokemon, you will be asked to decide which Pokemon to evolve. "
					+ "You can also play Trainer cards.", false);
			help.addField("!attack", "Attack. You will be asked which attack to use.", false);
			help.addField("!endturn", "End the turn.", false);

		}
		
		event.getChannel().sendMessage(help.build()).queue();
	}
	
	public void opponentSetup() {
		int maxHP = 0, maxHPIndex = -1;
		for(int i = 0; i < gameState.opponentHand.size(); i++) {
			if(gameState.opponentHand.get(i).stage == 0 && gameState.opponentHand.get(i).type <= 4 && gameState.opponentHand.get(i).HP > maxHP) {
				maxHP = gameState.opponentHand.get(i).HP;
				maxHPIndex = i;
			}
		}
		gameState.opponentBench.add(gameState.opponentHand.get(maxHPIndex));
		gameState.opponentHand.remove(maxHPIndex);
		for(int i = 0; i < gameState.opponentHand.size(); i++) {
			if(gameState.opponentHand.get(i).stage == 0 && gameState.opponentHand.get(i).type <= 4) {
				gameState.opponentBench.add(gameState.opponentHand.get(i));
				gameState.opponentHand.remove(i);
				i--;
			}
		}
	}
	public void opponentTakesTurn() {
		
		//draw a card
		firstEvent.getChannel().sendMessage("Draw a card.").queue();
		gameState.opponentHand.add(gameState.opponentDeck.get(0));
		gameState.opponentDeck.remove(0);
		
		//Play any basic Pokemon onto the bench
		
		
		//Decide whether to evolve
		
		//Attach an Energy card intelligently
		
		//Attack
		
		//End turn
		firstEvent.getChannel().sendMessage("Draw a card.").queue();
		gameState.playerHand.add(gameState.playerDeck.get(0));
		gameState.playerDeck.remove(0);
		state = 6;
	}
	
	public void opponentReplacePokemon() {
		
	}
}