package smooshe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import model.MorseCodeCount;
import util.FileReader;

@ExtendWith(MockitoExtension.class)
class SmooshedTest {

	@Mock
	private FileReader fileReader;
	@InjectMocks
	private Smooshed smooshed;

	@Test
	void smooshed_validateConstructor(){
		Map<Character, String> morseCodeMap = smooshed.getMorseCodeMap();
		assertNotNull(morseCodeMap);
		assertEquals(26, morseCodeMap.size());
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"  ", "\t", "\n"})
	void isBlank_ShouldReturnTrueForAllTypesOfBlankStrings(String input) {
		assertThrows(IllegalArgumentException.class , ()->smooshed.smoothe(input));
	}

	@ParameterizedTest
	@CsvSource({"programmer,.--..-.-----..-..-----..-.", "sos,...---...", "daily,-...-...-..-.--", "bits,-.....-...", "three,-.....-..."})
	void smooshed_whenInputIsValid(String input, String expected){
		assertEquals(expected,smooshed.smoothe(input));
	}

	@ParameterizedTest
	@CsvSource({"needing,-...-....-.--.", "nervate,-...-....-.--.", "niding,-...-....-.--.", "tiling,-...-....-.--."})
	void smooshed_whenDifferentWordsHasSomeMorseCode(String input, String expected) {
		assertEquals(expected,smooshed.smoothe(input));
	}

	@Test
	void smooshed_countDashes(){
		String morseCode = smooshed.smoothe("nonimmigrants");
		assertEquals(15,smooshed.countDashes(morseCode));
	}

	@Test
	void smooshed_countDashesAndDots(){
		String morseCode = smooshed.smoothe("photoconductive");
		MorseCodeCount morseCodeCount = smooshed.countDashesAndDots(morseCode);
		assertEquals(morseCodeCount.getDashCount(),morseCodeCount.getDotCount());
	}

	/**
	 *
	 * photoconductive, unconventionality, oxyphenbutazone, glycogenolyses,
	 * oxyhemoglobins, noncontrollable, postproductions, telecommunications, photojournalist,
	 * immunogenetically, decarboxylation, radioautography, petrologically, interchromosomal, countergovernments, overhomogenizing, melodramatizations, overcontrolling, coeducationally, gastroenterologist, geochronologies, macrolepidoptera, glycosylations, counteradaptations, photoreactivation,
	 * photometrically, proprioceptions, congregationalism, polyacrylamide, aerodynamically, pseudonymously, neuropathologic, combinatorially,
	 * cytomegalovirus, microphotometries, miscommunications, pharmacognostic, counterdemonstrated, autoradiography, biotransformation, phytopathogenic, cyanoethylations
	 *
	 */

}