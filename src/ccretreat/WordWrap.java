package ccretreat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordWrap {

	public static String umbrechen(String text, int maxZeilenlänge){
		String[] wörter = wörterUmbrechen(text);
		String ausgabeText = ausgabeTextAufbereiten(wörter);
		return ausgabeText;
	}

	static String[] wörterUmbrechen(String text) {
		return Stream.of(text.split("\\s"))
				.filter(s -> !s.isEmpty())
				.collect(Collectors.toList())
				.toArray(new String[]{});
	}

	static String ausgabeTextAufbereiten(String[] zeilen) {
		return String.join("\n", zeilen);
	}
}
