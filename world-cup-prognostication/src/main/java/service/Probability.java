package service;

import domain.Team;

/**
 * Calculates the probability based on ELO ratings
 */
public class Probability {
	private static final int K = 60;
	private static final int G = 1;

	/**
	 * Calculates elo ratings of given teams
	 * <p>newEloRating = oldEloRating + K * G * (W - We)
	 * <p>K: is a weighting for the importance of the game (K is 60 for the World Cup)
	 * <p>G: is a parameter based on the goal differential (we’ll assume that all games are won by a single goal, so G = 1
	 * <p>W: is 1 for a win and 0 for a loss
	 * <p>We: is the winning expectation calculated by the P(A) = 1/(1+10^m)
	 *
	 * @param firsTeam   - first team
	 * @param secondTeam - second team
	 * @param winOrLost  - who wins. If true, first team wins, otherwise second team
	 */
	public double calculateEloRating(Team firsTeam, Team secondTeam, boolean winOrLost) {
		int w = winOrLost ? 1 : 0;
		return K * G * (w - calculateWinExpectation(firsTeam, secondTeam));
	}

	/**
	 * Calculates win expectation of a team by comparing team ratings
	 *
	 * @param firstTeam  - first team
	 * @param secondTeam - second team
	 * @return - probability of winning
	 */
	public double calculateWinExpectation(Team firstTeam, Team secondTeam) {
		// P(A) = 1/(1+10^m) where m is the rating difference (rating(B)-rating(A)) divided by 400
		double ratingDifference = (secondTeam.getRating() - firstTeam.getRating()) / 400.0;
		return 1 / (1 + Math.pow(10, ratingDifference));
	}

}
