package util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.Test;

class FileReaderTest {
	private final FileReader fileReader = new FileReader();

	@Test
	void fileRead() throws URISyntaxException, IOException {
		List<String> wordList = fileReader.readFile("wordList.txt");
		assertNotNull(wordList);
	}

}