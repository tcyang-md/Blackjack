/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS OR COPIED FROM ONLINE RESOURCES. Chris Yang
*/


import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Card {

	// Define instance variables (data members) of class Card
	String value;
	String suit;

	// Default constructor with no argument
	// NOTE: You can overload the constructor but you must
	// have this default constructor and it must generate a valid card
	public Card() {
		this.value = "Ace";
		this.suit = "Spade";
	}
	
	// constructor with arguments, overloaded
	public Card(String num, String suit) {
		this.value = num;
		this.suit = suit;
	}

	// Override the method equals which is inherited from class Object
	// (similar to what we did in class Employee), and make it return true if the
	// two cards have the same suit and rank
	// Important: Use the @Override annotation
	@Override
	public boolean equals(Object newCard) {
		if (newCard instanceof Card) {
			return ((this.value).equals(((Card) newCard).value) && (this.suit).equals(((Card) newCard).suit));
		}
		System.out.println("Error not card data type");
		return false;
	}

	// Public static method that takes in an empty deck and constructs a
	// randomly shuffled standard 52-card deck. A standard 52-card deck
	// is comprised of 13 ranks in each of the four French suits:
	// clubs, diamonds, hearts, and spades. Each suit includes an Ace, a King,
	// a Queen, and a Jack with the numeric cards from two to ten.
	// Important: Two calls to the build deck should likely return 2 different
	// shuffles.
	public static void buildDeck(ArrayList<Card> deck) {

		// sets suits and values
		String[] suitArray = new String[] { "Hearts", "Diamonds", "Spades", "Clubs" };
		String[] valueArray = new String[] { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

		// make unique cards in order Hearts A-K, Diamonds A-K, etc...
		for (int i = 0; i < suitArray.length; i++) {
			for (int x = 0; x < valueArray.length; x++) {
				deck.add(new Card(valueArray[x], suitArray[i]));
			}
		}

		// shuffle cards
		for (int i = 0; i < deck.size(); i++) {
			Card temp = new Card(deck.get(i).value, deck.get(i).suit);
			int rand = (int) (Math.random() * deck.size());

			deck.set(i, deck.get(rand));
			deck.set(rand, temp);
		}
	}

	// Method that takes a non-empty deck and deals 2 cards to the player's hand
	// and deals 2 cards to the dealer's hand. The deck at the end of
	// this method should have 4 less cards than when it started.
	public static void initialDeal(ArrayList<Card> deck, ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
		
		// Player card first, then dealer card second, twice
		for (int i = 0; i < 2; i++) {
			playerHand.add(deck.get(0));
			dealerHand.add(deck.get(1));
			deck.remove(0);
			deck.remove(1);
		}

	}

	// Method that takes a non-empty deck and deals 1 card to the hand.
	// The deck at the end of this method should have
	// 1 less card than when it started.
	public static void dealOne(ArrayList<Card> deck, ArrayList<Card> hand) {
		// deal out first random card, then remove that card
		hand.add(deck.get(0));
		deck.remove(0);
	}

	// Method that gets the total value of the hand. The Jack, Queen, and
	// King cards take on the value 10, while an Ace can be 1 or 11.
	// Thus, if the hand contains a 10 of Spades and a Jack of Hearts
	// it will return a 20. If the hand contains a 5 of Clubs and a
	// Queen of Spades, this method should return a 15.
	public static int getHandValue(ArrayList<Card> hand) {
		int handValue = 0;
		int aceCounter = 0;
		// counts number of aces that are 11s
		
		for (int i = 0; i < hand.size(); i++) { 
			// iterates through whole hand
			
			if (hand.get(i).value.equals("Jack") || hand.get(i).value.equals("Queen") || hand.get(i).value.equals("King")) { 
				// first checks face cards
				handValue += 10;
				
			} else if (!hand.get(i).value.equals("Ace")) { 
				// checks the number cards
				handValue += Integer.parseInt(hand.get(i).value);
				
			} else { 
				// for sure aces
				if (handValue + 11 > 21) { 
					// ace as last card drawn if over set 1, draw more
					handValue += 1;
				} else { 
					// checks ace, not over 21, so 11, add to acecounter counting 11's
					handValue += 11;
					aceCounter++;
				}
			}			
			
		}
		
		// drew an ace early and adjust if over 21
		if (handValue > 21 && aceCounter != 0) {
			handValue -= 10*aceCounter;
		}
		return handValue;
	}

	// Method that checks whether the given hand's value exceeds 21.
	public static boolean checkBust(ArrayList<Card> hand) {
		if(getHandValue(hand) > 21) {
			return true;
		} else {
			return false;
		}
	}

	// Method that conduct the dealer's turn. The return value should be
	// true if the dealer busts and false otherwise. For the dealer's turn,
	// your code should continue to hit (or get a card) if the hand is less than 17
	// otherwise stand if the hand is greater than or equal to 17.
	public static boolean dealerTurn(ArrayList<Card> deck, ArrayList<Card> hand) {
		while (getHandValue(hand) < 17) {
			dealOne(deck, hand);
		}
		return checkBust(hand);
	}

	// Fill in the code that determines who wins. The return value should be
	// 1 if the player wins and 2 if the dealer wins. Winning is determined by who
	// has
	// the closer value to 21 without busting (going over 21).
	public static int whoWins(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
		if (checkBust(playerHand) == false) {
			// checks if player busts, if busts, dealer auto wins
			if(checkBust(dealerHand) == true) {
				// checks if dealer busts when player doesn't
				return 1;
			} else {
				// if neither bust, winner = closer hand, if tie dealer win
				if(21-getHandValue(playerHand) < 21-getHandValue(dealerHand)) {
					return 1;
				} else {
					return 2;
				}
			}
		}
		return 2;
	}

	// Method that describes the card (value and suit) located at index 1
	// in the hand. This is used to show one of the cards that the dealer has.
	public static String displayCard(ArrayList<Card> hand) {
		return hand.get(1).value + " of " + hand.get(1).suit;
	}

	// Method that describes the cards (values and suits) in the hand.
	public static String displayHand(ArrayList<Card> hand) {
		String handToString = "";
		
		for (int i = 0; i < hand.size(); i++) {
			String thisCard = hand.get(i).value + " of " + hand.get(i).suit + " ";
			handToString = handToString.concat(thisCard);
		}
		return handToString;
	}

	// Method that clears both the player hand and dealer hand.
	// There should be no cards in either hand after this method is called.
	public static void clearHands(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
		playerHand.clear();
		dealerHand.clear();
	}

	//  Add any methods as necessary

	// Do not change anything after this!
	public static void main(String[] args) {

		int playerChoice, winner;
		ArrayList<Card> deck = new ArrayList<Card>();

		playerChoice = JOptionPane.showConfirmDialog(null, "Ready to Play Blackjack?", "Blackjack",
				JOptionPane.OK_CANCEL_OPTION);

		if ((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
			System.exit(0);

		Object[] options = { "Hit", "Stand" };
		boolean isBusted, dealerBusted;
		boolean isPlayerTurn;
		ArrayList<Card> playerHand = new ArrayList<>();
		ArrayList<Card> dealerHand = new ArrayList<>();

		do { // Game loop
				// Clear the hands
			clearHands(playerHand, dealerHand);
			// Clear the deck and build if not enough cards left to play a new game
			if (deck.size() <= 12) {
				deck.clear();
				buildDeck(deck);
			}

			initialDeal(deck, playerHand, dealerHand);
			isPlayerTurn = true;
			isBusted = false;
			dealerBusted = false;

			while (isPlayerTurn) {
				System.out.println("Player hand value is:" + String.valueOf(getHandValue(playerHand)));

				// Shows the hand and prompts player to hit or stand
				playerChoice = JOptionPane.showOptionDialog(null,
						"Dealer shows " + displayCard(dealerHand) + "\nYour hand is: " + displayHand(playerHand)
								+ "\nWhat do you want to do?",
						"Hit or Stand", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[0]);

				// Player chooses to close the game
				if (playerChoice == JOptionPane.CLOSED_OPTION)
					System.exit(0);

				// Player chooses to Hit
				else if (playerChoice == JOptionPane.YES_OPTION) {
					dealOne(deck, playerHand);
					System.out
							.println("Player hand value after hitting is:" + String.valueOf(getHandValue(playerHand)));
					isBusted = checkBust(playerHand);
					if (isBusted) {
						// Case: Player Busts
						playerChoice = JOptionPane.showConfirmDialog(null,
								"Your hand: " + displayHand(playerHand) + "\nPlayer has busted!", "You lose",
								JOptionPane.OK_CANCEL_OPTION);

						if ((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
							System.exit(0);

						isPlayerTurn = false;
					}
				}
				// Player chooses to Stand
				else {
					isPlayerTurn = false;
				}
			}
			System.out.println("Dealer hand value is:" + String.valueOf(getHandValue(dealerHand)));

			if (!isBusted) { // Continues if player hasn't busted
				dealerBusted = dealerTurn(deck, dealerHand);
				System.out.println("Dealer hand value after turn is:" + String.valueOf(getHandValue(dealerHand)));

				if (dealerBusted) { // Case: Dealer Busts
					playerChoice = JOptionPane.showConfirmDialog(null,
							"The dealer's hand: " + displayHand(dealerHand) + "\n \nYour hand: "
									+ displayHand(playerHand) + "\nThe dealer busted.\nCongratulations!",
							"You Win!!!", JOptionPane.OK_CANCEL_OPTION);

					if ((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						System.exit(0);
				}

				else { // The Dealer did not bust. The winner must be determined
					winner = whoWins(playerHand, dealerHand);

					if (winner == 1) { // Player Wins
						playerChoice = JOptionPane.showConfirmDialog(null,
								"The dealer's hand: " + displayHand(dealerHand) + "\n \nYour hand: "
										+ displayHand(playerHand) + "\nCongratulations!",
								"You Win!!!", JOptionPane.OK_CANCEL_OPTION);

						if ((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
							System.exit(0);
					}

					else { // Player Loses
						playerChoice = JOptionPane.showConfirmDialog(null,
								"The dealer's hand: " + displayHand(dealerHand) + "\n \nYour hand: "
										+ displayHand(playerHand) + "\nBetter luck next time!",
								"You lose!!!", JOptionPane.OK_CANCEL_OPTION);

						if ((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
							System.exit(0);
					}
				}
			}
		} while (true);
	}
}