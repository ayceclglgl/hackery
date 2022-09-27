import java.util.List;

public final class TalkingClock {

	public static final int TWELVE_HOUR_FORMAT = 12;

	public static final  int      TEN_BASED    = 10;
	private static final String[] SINGLE_DIGIT = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
	private static final String[] TENS         = { "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen" };
	private static final String[] TWO_DIGITS   = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };

	public static final String TWELVE       = "twelve";
	public static final String OH           = "oh";
	public static final String AM           = "am";
	public static final String PM           = "pm";
	public static final String BLANK_STRING = " ";
	public static final String EMPTY_STRING = "";
	public static final String COLON        = ":";

	private static final List<String> SAMPLE_INPUT = List.of("00:00", "01:30", "12:05", "14:01", "20:29", "21:00");

	private TalkingClock() {
	}

	public static void main(String[] args) {
		for (String time : SAMPLE_INPUT) {
			System.out.printf(getTimeInText(time) + "\n");
		}
	}

	private static String getTimeInText(String time) {
		String[] hourMinute = time.split(COLON);
		int hour = Integer.parseInt(hourMinute[0]);
		int minute = Integer.parseInt(hourMinute[1]);
		return String.format("It's %s %s %s", getHourInText(hour), getMinuteInText(minute), getAMOrPM(hour));
	}

	private static String getHourInText(int hour) {
		int mod = hour % TWELVE_HOUR_FORMAT;
		if (mod == 0) {
			return TWELVE;
		}
		return getNumberText(mod);
	}

	private static String getMinuteInText(int minute) {
		if (minute == 0) {
			return EMPTY_STRING;
		}
		if (minute < 10) {
			return OH + BLANK_STRING + getNumberText(minute);
		}
		return getNumberText(minute);
	}

	private static String getAMOrPM(int hour) {
		if (hour < TWELVE_HOUR_FORMAT) {
			return AM;
		}
		return PM;
	}

	private static String getNumberText(int number) {
		if (number < TEN_BASED) {
			return SINGLE_DIGIT[number];
		}

		if (number < 20) {
			return TENS[number / TEN_BASED];
		}

		int mod = number % TEN_BASED;
		if (mod == 0) {
			return TWO_DIGITS[number / TEN_BASED];
		}
		return TWO_DIGITS[number / TEN_BASED] + BLANK_STRING + SINGLE_DIGIT[mod];
	}
}
