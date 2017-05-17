package ccretreat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordWrap {

	public static String umbrechen(String text, int maxZeilenl�nge){
		String[] w�rter = w�rterUmbrechen(text);
		String ausgabeText = ausgabeTextAufbereiten(w�rter);
		return ausgabeText;
	}

	static String[] w�rterUmbrechen(String text) {
		return Stream.of(text.split("\\s"))
				.filter(s -> !s.isEmpty())
				.collect(Collectors.toList())
				.toArray(new String[]{});
	}

	static String ausgabeTextAufbereiten(String[] zeilen) {
		return String.join("\n", zeilen);
	}
}
