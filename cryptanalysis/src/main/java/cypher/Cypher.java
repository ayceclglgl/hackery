package cypher;

import util.Direction;

public interface Cypher {

	String encrypt(String input, Direction direction, int shift);

	String decrypt(String input, Direction direction, int shift);

}
