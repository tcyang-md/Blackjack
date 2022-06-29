# Blackjack :black_joker:
I created Blackjack in **Java** using **JOptionPane** as my GUI and an **ArrayList** of card objects to simulate a deck of cards. The program follows the traditional rules of Blackjack where the dealer must draw to 16 and stand on all 17s. 

## Instructions
Download the card.java file in your IDE and run. The game should appear as a JOptionPane pop up and you will be able to play. Have fun!

## Breakdown
``` java
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Card {
  
  // constructors for the card object called by buildDeck
  public Card()
  public Card(String num, String suit)
  
  // checks if one card is exactly the same as another card (suit and value)
  @Override
  public boolean equals(Object newCard)
  
  // creates a unique deck of cards and shuffles them using a Fisher-Yates shuffling algorithm where a loop iterates 
  // through every index in the ArrayList of the deck, swapping the current card with another random card.
  public static void buildDeck(ArrayList<Card> deck)
  
  // deals 4 cards, 2 to player, 2 to dealer
  public static void initialDeal(ArrayList<Card> deck, ArrayList<Card> playerHand, ArrayList<Card> dealerHand)
  
  // helper methods
  public static void dealOne(ArrayList<Card> deck, ArrayList<Card> hand)
  public static int getHandValue(ArrayList<Card> hand)
  public static boolean checkBust(ArrayList<Card> hand)
  public static boolean dealerTurn(ArrayList<Card> deck, ArrayList<Card> hand)
  public static int whoWins(ArrayList<Card> playerHand, ArrayList<Card> dealerHand)
  public static String displayCard(ArrayList<Card> hand)
  public static String displayHand(ArrayList<Card> hand)
  public static void clearHands(ArrayList<Card> playerHand, ArrayList<Card> dealerHand)
  
  // using JOptionPane, display game with hit and stay options
  public static void main(String[] args)
}
```

## Demo Example
![first](https://user-images.githubusercontent.com/70073219/176310926-a59f6ced-4558-4ad7-b722-98f57ead339d.png)
![display](https://user-images.githubusercontent.com/70073219/176310980-d9af3f1f-3998-4be6-980c-3d5655ec17b2.png)
![playerbust](https://user-images.githubusercontent.com/70073219/176311017-bed58d17-7d32-43fc-b6e3-f4d77c359968.png)
![playerbeatsdealer](https://user-images.githubusercontent.com/70073219/176311068-c5d2aad2-4a6b-4916-b675-781cd3d0e1ea.png)
![dealerbeatsplayer](https://user-images.githubusercontent.com/70073219/176311106-a8651fe5-9ead-4865-9f97-7dadb576b94d.png)
![dealerbust](https://user-images.githubusercontent.com/70073219/176311152-cae992a0-ed34-4fd5-8271-242549179ee0.png)
