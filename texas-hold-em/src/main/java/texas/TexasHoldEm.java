package texas;

import java.util.ArrayList;
import java.util.List;

import model.Deck;
import model.Player;
import model.Table;

public final class TexasHoldEm {

	private TexasHoldEm() {
	}

	private static final Deck   deck    = new Deck();
	private static final Player player1 = new Player();
	private static final Player player2 = new Player();
	private static final Table        table   = new Table();
	private static final Round        round   = new Round();
	private static final List<Player> players = new ArrayList<>();

	public static void main(String[] args) {
		players.add(player1);
		players.add(player2);

		deck.shuffle();
		player1.setCard(deck.getTopCards(2));
		player2.setCard(deck.getTopCards(2));
		table.setCards(deck.getTopCards(3));
		table.getCards().add(deck.getTopCard());
		table.getCards().add(deck.getTopCard());

		round.game(players, table);
	}
}
