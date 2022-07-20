import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.Country;
import domain.Team;
import service.Probability;
import service.Tournament;

public final class WordCupPrognostication {
	private static final Map<Country, Integer> scoreMap = Map.ofEntries(
			Map.entry(Country.ENGLAND, 1692),
			Map.entry(Country.GERMANY, 1775),
			Map.entry(Country.NETHERLANDS, 1726),
			Map.entry(Country.DENMARK, 1503),
			Map.entry(Country.NORWAY, 1537),
			Map.entry(Country.SWEDEN, 1801),
			Map.entry(Country.FRANCE, 1832),
			Map.entry(Country.BELGIUM, 1374),
			Map.entry(Country.ICELAND, 1476),
			Map.entry(Country.SPAIN, 1668),
			Map.entry(Country.FINLAND, 1210),
			Map.entry(Country.AUSTRIA, 1380),
			Map.entry(Country.ITALY, 1582),
			Map.entry(Country.SWITZERLAND, 1327),
			Map.entry(Country.NORTHERN_IRELAND, 980),
			Map.entry(Country.PORTUGAL, 1248)
	);

	private static final Team ENGLAND          = new Team(Country.ENGLAND, scoreMap.get(Country.ENGLAND));
	private static final Team GERMANY          = new Team(Country.GERMANY, scoreMap.get(Country.GERMANY));
	private static final Team NETHERLANDS      = new Team(Country.NETHERLANDS, scoreMap.get(Country.NETHERLANDS));
	private static final Team DENMARK          = new Team(Country.DENMARK, scoreMap.get(Country.DENMARK));
	private static final Team NORWAY           = new Team(Country.NORWAY, scoreMap.get(Country.NORWAY));
	private static final Team SWEDEN           = new Team(Country.SWEDEN, scoreMap.get(Country.SWEDEN));
	private static final Team FRANCE           = new Team(Country.FRANCE, scoreMap.get(Country.FRANCE));
	private static final Team BELGIUM          = new Team(Country.BELGIUM, scoreMap.get(Country.BELGIUM));
	private static final Team ICELAND          = new Team(Country.ICELAND, scoreMap.get(Country.ICELAND));
	private static final Team SPAIN            = new Team(Country.SPAIN, scoreMap.get(Country.SPAIN));
	private static final Team FINLAND          = new Team(Country.FINLAND, scoreMap.get(Country.FINLAND));
	private static final Team AUSTRIA          = new Team(Country.AUSTRIA, scoreMap.get(Country.AUSTRIA));
	private static final Team ITALY            = new Team(Country.ITALY, scoreMap.get(Country.ITALY));
	private static final Team SWITZERLAND      = new Team(Country.SWITZERLAND, scoreMap.get(Country.SWITZERLAND));
	private static final Team NORTHERN_IRELAND = new Team(Country.NORTHERN_IRELAND, scoreMap.get(Country.NORTHERN_IRELAND));
	private static final Team PORTUGAL         = new Team(Country.PORTUGAL, scoreMap.get(Country.PORTUGAL));

	private static final List<Team> FIRST_POT  = Arrays.asList(ENGLAND, AUSTRIA, NORWAY, NORTHERN_IRELAND);
	private static final List<Team> SECOND_POT = Arrays.asList(GERMANY, DENMARK, SPAIN, FINLAND);
	private static final List<Team> THIRD_POT  = Arrays.asList(NETHERLANDS, SWEDEN, SWITZERLAND, PORTUGAL);
	private static final List<Team> FOURTH_POT = Arrays.asList(FRANCE, ITALY, BELGIUM, ICELAND);

	private static final int SIMULATION_RUN = 1000000;

	private static final Map<Country, Integer> simulationWinners = new EnumMap<>(Country.class);

	private static final Probability probability = new Probability();
	private static final Tournament  tournament  = new Tournament(probability);

	private WordCupPrognostication() {
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		printToConsole(true, "Please enter 1 to play tournament, 0 to run simulation:");
		int mode = scanner.nextInt();
		if (mode == 1) {
			playTournament(true);
		} else if (mode == 0) {
			runSimulation();
		} else {
			throw new IllegalArgumentException("Invalid mode, please enter 1 to play tournament, 0 to run simulation");
		}
	}

	private static void runSimulation() {
		for (int i = 0; i < SIMULATION_RUN; i++) {
			Team winner = playTournament(false);
			simulationWinners.put(winner.getName(), simulationWinners.getOrDefault(winner.getName(), 0) + 1);
			resetScores();
		}
		simulationWinners.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.forEach(countryIntegerEntry -> printToConsole(true, "%s - %s%n", countryIntegerEntry.getKey(), countryIntegerEntry.getValue()));
	}

	private static Team playTournament(boolean print) {
		printToConsole(print, "%nFirst pot: %s%nSecond pot: %s%nThird pot: %s%nFourth pot: %s%n", FIRST_POT, SECOND_POT, THIRD_POT, FOURTH_POT);
		List<Team> winnersOfFirstPot = tournament.playMatch(FIRST_POT);
		List<Team> winnersOfSecondPot = tournament.playMatch(SECOND_POT);
		List<Team> winnersOfThirdPot = tournament.playMatch(THIRD_POT);
		List<Team> winnersOfFourthPot = tournament.playMatch(FOURTH_POT);
		printToConsole(print,
				"%nFirst pot eighth-finals: %s%nSecond pot eighth-finals: %s%nThird pot eighth-finals: %s%nFourth pot eighth-finals: %s%n",
				winnersOfFirstPot, winnersOfSecondPot, winnersOfThirdPot, winnersOfFourthPot);

		List<Team> quarterFinalsOfFirstPot = tournament.playMatch(winnersOfFirstPot);
		List<Team> quarterFinalsOfSecondPot = tournament.playMatch(winnersOfSecondPot);
		List<Team> quarterFinalsOfThirdPot = tournament.playMatch(winnersOfThirdPot);
		List<Team> quarterFinalsOfFourthPot = tournament.playMatch(winnersOfFourthPot);
		printToConsole(print,
				"%nFirst pot quarter finals: %s%nSecond pot quarter finals: %s%nThird pot quarter finals: %s%nFourth pot quarter finals: %s%n",
				quarterFinalsOfFirstPot, quarterFinalsOfSecondPot, quarterFinalsOfThirdPot, quarterFinalsOfFourthPot);

		List<Team> semiFinalsPotA = Stream.of(quarterFinalsOfFirstPot, quarterFinalsOfSecondPot).flatMap(Collection::stream)
				.collect(Collectors.toList());
		List<Team> winnerSemiFinalsPotA = tournament.playMatch(semiFinalsPotA);
		List<Team> semiFinalsPotB = Stream.of(quarterFinalsOfThirdPot, quarterFinalsOfFourthPot).flatMap(Collection::stream)
				.collect(Collectors.toList());
		List<Team> winnerSemiFinalsPotB = tournament.playMatch(semiFinalsPotB);
		printToConsole(print, "%nSemi finals pot A: %s%nSemi finals pot B: %s%n", semiFinalsPotA, semiFinalsPotB);

		List<Team> finals = Stream.of(winnerSemiFinalsPotA, winnerSemiFinalsPotB).flatMap(Collection::stream).collect(Collectors.toList());
		printToConsole(print, "%nFinals: %s%n", finals);
		List<Team> winner = tournament.playMatch(finals);
		if (winner.size() == 1) {
			printToConsole(print, "%nWinner is %s%n", winner.get(0));
			return winner.get(0);
		}
		throw new IllegalStateException("Could not find winner");
	}

	private static void resetScores() {
		Arrays.asList(FIRST_POT, SECOND_POT, THIRD_POT, FOURTH_POT).forEach(pot -> pot.forEach(team -> team.setRating(scoreMap.get(team.getName()))));
	}

	private static void printToConsole(boolean print, String text, Object... args) {
		if (print) {
			System.out.printf(text, args);
		}
	}

}
