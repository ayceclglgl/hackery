package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private final List<Card> cards;

	public Deck(){
		cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}
	}
	public void shuffle(){
		Collections.shuffle(cards);
	}

	public List<Card> getTopCards(int number){
		List<Card> cardList = cards.subList(0, number);
		cards.removeAll(cardList);
		return cardList;
	}

	public Card getTopCard(){
		return getTopCards(1).get(0);
	}

	public List<Card> getCards() {
		return cards;
	}
}
