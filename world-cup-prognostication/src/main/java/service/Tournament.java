package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import domain.Team;

public class Tournament {
	private final Probability probability;
	private final Random      random = new Random();

	public Tournament(Probability probability) {
		this.probability = probability;
	}

	/**
	 * Play matches among teams which are given in pot
	 *
	 * @param pot - list of teams
	 * @return - list of winners
	 */
	public List<Team> playMatch(List<Team> pot) {
		List<Team> winners = new ArrayList<>();
		for (int i = 0; i < pot.size(); i += 2) {
			winners.add(play(pot.get(i), pot.get(i + 1)));
		}
		return winners;
	}

	private Team play(Team firstTeam, Team secondTeam) {
		// Toss a coin, who wins. 1: firstTeam wins, 0: secondTeam wins
		double winExpectationOfFirstTeam = probability.calculateWinExpectation(firstTeam, secondTeam);
		boolean firstTeamWins = random.nextDouble() < winExpectationOfFirstTeam;
		firstTeam.setRating((int) (firstTeam.getRating() + probability.calculateEloRating(firstTeam, secondTeam, firstTeamWins)));
		secondTeam.setRating((int) (secondTeam.getRating() + probability.calculateEloRating(firstTeam, secondTeam, !firstTeamWins)));
		return firstTeamWins ? firstTeam : secondTeam;
	}

}
