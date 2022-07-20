package service;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.Country;
import domain.Team;

@ExtendWith(MockitoExtension.class)
class TournamentTest {
	private final Team ENGLAND          = new Team(Country.ENGLAND, 1692);
	private final Team AUSTRIA          = new Team(Country.AUSTRIA, 1380);
	private final Team NORWAY           = new Team(Country.NORWAY, 1537);
	private final Team NORTHERN_IRELAND = new Team(Country.NORTHERN_IRELAND, 980);

	private final List<Team> pot = Arrays.asList(ENGLAND, AUSTRIA, NORWAY, NORTHERN_IRELAND);

	@Mock
	private Probability probability;

	@InjectMocks
	private Tournament tournament;

	@Test
	void playMatch_checkTeamsScoresChanging() {
		when(probability.calculateEloRating(any(), any(), eq(true))).thenReturn(10.0);
		when(probability.calculateEloRating(any(), any(), eq(false))).thenReturn(-10.0);

		List<Team> winners = tournament.playMatch(pot);

		assertNotNull(winners);
		assertNotEquals(1692, ENGLAND.getRating());
		assertNotEquals(1380, AUSTRIA.getRating());
		assertNotEquals(1537, NORWAY.getRating());
		assertNotEquals(980, NORTHERN_IRELAND.getRating());
		verify(probability, times(pot.size() / 2)).calculateEloRating(any(), any(), eq(true));
		verify(probability, times(pot.size() / 2)).calculateEloRating(any(), any(), eq(false));
	}

}