package model;

public enum Rank {
	CARD_ONE(1),
	CARD_TWO(2),
	CARD_THREE(3),
	CARD_FOUR(4),
	CARD_FIVE(5),
	CARD_SIX(6),
	CARD_SEVEN(7),
	CARD_EIGHT(8),
	CARD_NINE(9),
	CARD_TEN(10),
	JACK(11),
	QUEEN(12),
	KING(13),
	ACE(14);

	private final int number;
	Rank(int number){
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}
