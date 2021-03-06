package dominion;

import java.util.*;

public class CardCollection 
{
    private ArrayList<Card> deck;
    private ArrayList<Card> discard;
    private ArrayList<Card> hand;
    private ArrayList<Card> table;
    
    public CardCollection()
    {
        deck = new ArrayList(100);
        discard = new ArrayList(100);
        hand = new ArrayList(100);
        table = new ArrayList(100);
        
        for (int i = 0; i < 7; i++) deck.add(new TreasureCard("Copper", 0, 1));
        for (int i = 7; i < 10; i++) deck.add(new VictoryCard("Estate", 2, 1));
        shuffleDeck();
    }
    
    public ArrayList<Card> getDeck()
    {
        return deck;
    }
    
    public ArrayList<Card> getDiscard()
    {
        return discard;
    }
    
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    
    public ArrayList<Card> getTable()
    {
        return table;
    }
    
    public int getValueOnTable()
    {
        int totalValue = 0;
        for (Card playedCards : table) if (playedCards.getType() == 0) totalValue += ((TreasureCard) playedCards).getTreasurePoints();
        return totalValue;
    }
    
     public int getValueHand()
    {
        int totalValue = 0;
        for (Card playedCards : hand) if (playedCards.getType() == 0) totalValue += ((TreasureCard) playedCards).getTreasurePoints();
        return totalValue;
    }
    
    public void drawCard(int amount)
    {
        for (int i = 0; i < amount; i++)
        {
            if (deck.isEmpty()) discardPileToDeck();
            hand.add(deck.get(0));
            deck.remove(0);
        }
    }
    
    public void discardCardFromHand(int index)
    {
        discard.add(hand.get(index));
        hand.remove(index);
    }
    
    public void discardCardFromTable(int index)
    {
        discard.add(table.get(index));
        table.remove(index);
    }
    
    public void discardAllCards()
    {
        int size = hand.size();
        for(int i = 0; i < size; i++)
        {
            discard.add(hand.get(0));
            hand.remove(0);
        }
        tablePileToDiscard();
    }
    
    public void discardPileToDeck()
    {
        deck = (ArrayList) discard.clone();
        discard.clear();
        shuffleDeck();
    }
    
    public void tablePileToDiscard()
    {
        int size = table.size();
        for (int i = 0; i < size; i++) 
        {
            discard.add(table.get(0));
            table.remove(0);
        }
    }
    
    public void deckPileToDiscard()
    {
        int size = deck.size();
        for (int i = 0; i < size; i++)
        {
            discard.add(deck.get(0));
            deck.remove(0);
        }
    }
    
    public void shuffleDeck()
    {
        Collections.shuffle(deck);
    }
    
    public void addNewCardToDiscard(Card card)
    {
        discard.add(card);
    }
    
    public void addCardToTopOfDeck(Card card)
    {
        deck.add(0, card);
    }
    
    public void addCardToHand(Card card)
    {
        hand.add(card);
    }
    
    public void playCard(int handIndex)// het is mogelijk om cijfers in te geven die hoger zijn dan het aantal kaarten in de hand
    {
        table.add(hand.get(handIndex));
        hand.remove(handIndex);
    }
    
    public void trashCardFromHand(int index)
    {
        hand.remove(index);
    }
    
    public void trashCardFromTable(int index)
    {
        table.remove(index);
    }
    
    public boolean hasTypeInHand(int type)
    {
        Boolean hasType = false;
        for (Card cards : hand) if (cards.getType() == type) hasType = true;
        
        return hasType;
    }
    
    public boolean hasReactionInHand()//Enkel moat voor het moment?
    {
        Boolean hasReaction = false;
        for (Card card : hand) if(card.getName().equals("Moat")) hasReaction = true;
        return hasReaction;
    }
}
