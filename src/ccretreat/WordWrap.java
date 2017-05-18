package ccretreat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordWrap {

	public static String umbrechen(String text, int maxZeilenlänge){
		String[] wörter = wörterUmbrechen(text);
		String[] zeilen = zeilenBauen(wörter, maxZeilenlänge);
		String ausgabeText = ausgabeTextAufbereiten(zeilen);
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

	static String[][] worteZusammenfassenProZeile(List<String> wortliste, int maxLength) {
		List<String[]> wortgruppen = new ArrayList<>();
		while(!wortliste.isEmpty()) {
			String[] wortgruppe = worteDerZeileBestimmen(wortliste, maxLength);
			wortgruppen.add(wortgruppe);
		}
		return wortgruppen.toArray(new String[][]{});
	}

	static String[] alleZeilenAusWortgruppenBauen(String[][] wortgruppen) {
		List<String> zeilen = new ArrayList<>();
		final String wortTrenner = " ";
		for(String[] wortgruppe : wortgruppen){
			String zeile = String.join(wortTrenner, wortgruppe);
			zeilen.add(zeile);
		}
		return zeilen.toArray(new String[]{});
	}

	static String[] zeilenBauen(String[] worte, int maxLength) {
		List<String> wörter = new ArrayList<>();
		wörter.addAll(Arrays.asList(worte));
		String[][] zeilen = worteZusammenfassenProZeile(wörter, maxLength);
		return alleZeilenAusWortgruppenBauen(zeilen);
	}
}
