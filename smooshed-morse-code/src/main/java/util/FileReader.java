package util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reads the given file from resources folder
 */
public class FileReader {
	/**
	 * Reads file inside resorces folder
	 *
	 * @param fileName - file name to be read
	 * @return - each word in a list
	 * @throws URISyntaxException - if file nam could not be converted to URI
	 * @throws IOException        - if file could not be opened
	 */
	public List<String> readFile(String fileName) throws URISyntaxException, IOException {
		Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI());
		Stream<String> lines = Files.lines(path);
		List<String> wordList = lines.filter(line -> !line.isEmpty()).filter(line -> !line.isBlank()).collect(Collectors.toList());
		lines.close();
		return wordList;
	}

}
