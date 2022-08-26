package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import util.Generator;

public class Simulation {
	private static final double    PROBABILITY = 0.05;
	private final        Generator generator;

	public Simulation(Generator generator) {
		this.generator = generator;
	}

	public Optional<Map.Entry<String, Integer>> simulate(String randomString, String targetString, int numberOfCopies) {
		List<String> randomStringList = makeCopies(randomString, numberOfCopies);
		List<String> mutatedList = mutate(randomStringList);
		Map<String, Integer> scoreMap = compareWithTarget(mutatedList, targetString);
		return scoreMap.entrySet().stream().max(Map.Entry.comparingByValue());
	}

	private Map<String, Integer> compareWithTarget(List<String> randomStringList, String targetString) {
		Map<String, Integer> scoreMap = new HashMap<>();
		randomStringList.forEach(s -> {
			int score = 0;
			char[] targetStringCharacterArray = targetString.toCharArray();
			char[] randomStringCharacterArray = s.toCharArray();
			for (int i = 0; i < targetStringCharacterArray.length; i++) {
				if (targetStringCharacterArray[i] == randomStringCharacterArray[i]) {
					score++;
				}
			}
			scoreMap.put(s, score);
		});
		return scoreMap;
	}

	private List<String> mutate(List<String> randomStringList) {
		return randomStringList.stream().map(s -> {
			char[] characterArray = s.toCharArray();
			for (int i = 0; i < characterArray.length; i++) {
				characterArray[i] = generator.generateRandomCharacterWithProbability(PROBABILITY, characterArray[i]);
			}
			return String.valueOf(characterArray);
		}).collect(Collectors.toList());
	}

	private List<String> makeCopies(String randomString, int numberOfCopies) {
		List<String> randomStringList = new ArrayList<>();
		IntStream.range(0, numberOfCopies).forEach(i -> randomStringList.add(randomString));
		return randomStringList;
	}
}
