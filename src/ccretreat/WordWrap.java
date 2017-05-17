package ccretreat;

import java.util.ArrayList;
import java.util.List;
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

	static String[] worteDerZeileBestimmen(List<String> wortliste, int maxLength) {
		final int wordSeparatorLength = 1;
		List<String> wortgruppe = new ArrayList<>();
		int currentLineLength = 0;
		while(!wortliste.isEmpty()){
			String currentWord = wortliste.get(0);
			
			int newLineLength = currentLineLength + currentWord.length();
			boolean inTheMiddleOfTheLine = currentLineLength > 0;
			if (inTheMiddleOfTheLine){
				newLineLength += wordSeparatorLength;
			}
			
			boolean lineCompleted = newLineLength > maxLength;
			if (lineCompleted){
				break;
			}
			
			wortgruppe.add(currentWord);
			wortliste.remove(0);
			currentLineLength = newLineLength;
		}
		return wortgruppe.toArray(new String[]{});
	}
}
