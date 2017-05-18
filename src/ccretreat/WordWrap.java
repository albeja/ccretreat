package ccretreat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordWrap {

	public static String umbrechen(String text, int maxZeilenl�nge){
		String[] w�rter = w�rterUmbrechen(text);
		String[] silben = zuLangeWorteTrennen(w�rter, maxZeilenl�nge);
		String[] zeilen = zeilenBauen(silben, maxZeilenl�nge);
		String ausgabeText = ausgabeTextAufbereiten(zeilen);
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
		List<String> w�rter = new ArrayList<>();
		w�rter.addAll(Arrays.asList(worte));
		String[][] zeilen = worteZusammenfassenProZeile(w�rter, maxLength);
		return alleZeilenAusWortgruppenBauen(zeilen);
	}

	static String[] zuLangeWorteTrennen(String[] worte, int maximaleZeilenl�nge) {
		List<String> silben = new ArrayList<>();
		for(String wort : worte){
			silben.addAll(WordWrap.split(wort, maximaleZeilenl�nge));
		}
		return silben.toArray(new String[]{});
	}

	static List<String> split(String wort, int maximaleZeilenl�nge) {
		if(wort.length() <= maximaleZeilenl�nge) {
			return Collections.singletonList(wort);
		}
		List<String> silben = new ArrayList<>();
		int silbenAnzahl = wort.length() / maximaleZeilenl�nge;
		for(int i = 0; i < silbenAnzahl; i++) {
			silben.add(wort.substring(i * maximaleZeilenl�nge, (i + 1) * maximaleZeilenl�nge));
		}
		silben.add(wort.substring(maximaleZeilenl�nge * silbenAnzahl));
		return silben;
	}
}
