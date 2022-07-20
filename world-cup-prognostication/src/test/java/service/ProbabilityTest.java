package service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import domain.Country;
import domain.Team;

class ProbabilityTest {

	private final Probability probability = new Probability();

	private final Team teamEngland = new Team(Country.ENGLAND, 1692);
	private final Team teamAustria = new Team(Country.AUSTRIA, 1985);

	@Test
	void calculateEloRating_whenFirstTeamWins_thenReturnPositiveEloRating() {
		double eloRating = probability.calculateEloRating(teamEngland, teamAustria, true);
		assertTrue(eloRating > 0);
	}

	@Test
	void calculateEloRating_whenSecondTeamWins_thenReturnNegativeEloRating() {
		double eloRating = probability.calculateEloRating(teamEngland, teamAustria, false);
		assertTrue(eloRating < 0);
	}

	@Test
	void calculateWinExpectation_whenTeamRatingsAreDifferent_thenCompareChanceOfWinning() {
		double teamEnglandProbability = probability.calculateWinExpectation(teamEngland, teamAustria);
		double teamAustriaProbability = probability.calculateWinExpectation(teamAustria, teamEngland);
		assertTrue(teamEnglandProbability < teamAustriaProbability);
	}
}