package ccretreat;

import static org.hamcrest.CoreMatchers.is;
import static ccretreat.WordWrap.umbrechen;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class WordWrapTest {
	
	@Test
	public void textWithSingleLetterLinesWrapsAtLengthOne() {
		assertThat(umbrechen("a\nb\nc", 1), is("a\nb\nc"));
	}
	
	@Test
	public void zeilenAusWortenBauen() {
		String[] worte = new String[]{"a", "b", "c"};
		int maxLength = 3;
		
		String[] expectedZeilen = new String[]{"a b", "c"};
		assertThat(WordWrap.zeilenBauen(worte, maxLength), is(expectedZeilen));
	}
	
	@Test
	public void emptyWordlist() {
		List<String> wortliste = new ArrayList<>();
		int maxLength = 3;
		
		String[] expectedWortgruppe = new String[]{};
		String[] expectedRemainingWortliste = new String[]{};
		
		String[] wortgruppe = WordWrap.worteDerZeileBestimmen(wortliste, maxLength);
		
		assertThat(wortliste.toArray(), is(expectedRemainingWortliste));
		assertThat(wortgruppe, is(expectedWortgruppe));
	}
	
	@Test
	public void lastWordConstitutesLine() {
		List<String> wortliste = new ArrayList<>();
		wortliste.add("c");
		int maxLength = 3;
		
		String[] expectedWortgruppe = new String[]{"c"};
		String[] expectedRemainingWortliste = new String[]{};
		
		String[] wortgruppe = WordWrap.worteDerZeileBestimmen(wortliste, maxLength);
		
		assertThat(wortliste.toArray(), is(expectedRemainingWortliste));
		assertThat(wortgruppe, is(expectedWortgruppe));
	}

	@Test
	public void wordgroupFitsExactlyInLine() {
		List<String> wortliste = new ArrayList<>();
		wortliste.addAll(Arrays.asList("a", "b", "c"));
		int maxLength = 3;
		
		String[] expectedWortgruppe = new String[]{"a", "b"};
		String[] expectedRemainingWortliste = new String[]{"c"};
		
		String[] wortgruppe = WordWrap.worteDerZeileBestimmen(wortliste, maxLength);
		
		assertThat(wortliste.toArray(), is(expectedRemainingWortliste));
		assertThat(wortgruppe, is(expectedWortgruppe));
	}
	
	@Test
	public void wordgroupShorterThanLine(){
		List<String> wortliste = new ArrayList<>();
		wortliste.addAll(Arrays.asList("a", "b", "c"));
		int maxLength = 2;
		
		String[] expectedWortgruppe = new String[]{"a"};
		String[] expectedRemainingWortliste = new String[]{"b", "c"};
		
		String[] wortgruppe = WordWrap.worteDerZeileBestimmen(wortliste, maxLength);
		
		assertThat(wortliste.toArray(), is(expectedRemainingWortliste));
		assertThat(wortgruppe, is(expectedWortgruppe));
	}
	
	@Test
	public void oneWordPerLine() {
		List<String> wortliste = new ArrayList<>();
		wortliste.addAll(Arrays.asList("a", "b", "c"));
		int maxLength = 1;
		
		String[][] lines = WordWrap.worteZusammenfassenProZeile(wortliste, maxLength);
		
		String[][] expectedLines = new String[][]{{"a"}, {"b"}, {"c"}};		
		assertThat(lines, is(expectedLines));
	}
	
	@Test
	public void multipleWordsPerLine() {
		List<String> wortliste = new ArrayList<>();
		wortliste.addAll(Arrays.asList("a", "b", "c"));
		int maxLength = 3;
		
		String[][] lines = WordWrap.worteZusammenfassenProZeile(wortliste, maxLength);
		
		String[][] expectedLines = new String[][]{{"a", "b"}, {"c"}};		
		assertThat(lines, is(expectedLines));
	}
	
	@Test
	public void zeilenAusWortgruppenBauen() {
		String[][] wortgruppen= new String[][]{{"a", "b"}, {"c", "d"}};
		
		String[] expectedZeilen = new String[]{"a b", "c d"};
		assertThat(WordWrap.alleZeilenAusWortgruppenBauen(wortgruppen), is(expectedZeilen));
	}
	
	@Test
	public void langeWörterUmbrechen() {
		String text = "Es blaut die Nacht,\n" +
				"die Sternlein blinken,\n" +
				"Schneeflöcklein leis hernieder sinken.";
		
		String actualText = WordWrap.umbrechen(text, 14);
		
		String expectedText = "Es blaut die\n" + 
				"Nacht, die\n" + 
				"Sternlein\n" + 
				"blinken,\n" + 
				"Schneeflöcklei\n" + 
				"n leis\n" + 
				"hernieder\n" + 
				"sinken.";
		
		assertThat(actualText, is(expectedText));
	}
	
	@Test
	public void zuLangeWorteTrennen() {
		String[] worte = new String[]{"a", "bcd", "efghi"};
		int maximaleZeilenlänge = 2;
		
		String[] actualSilben = WordWrap.zuLangeWorteTrennen(worte, maximaleZeilenlänge);
		
		String[] expectedSilben = new String[]{"a", "bc", "d", "ef", "gh", "i"};
		assertThat(actualSilben, is(expectedSilben));
	}
	
	@Test
	public void kurzeWorteNichtTrennen() {
		String[] worte = new String[]{"a", "b", "c"};
		int maximaleZeilenlänge = 1;
		
		String[] actualSilben = WordWrap.zuLangeWorteTrennen(worte, maximaleZeilenlänge);
		
		String[] expectedSilben = new String[]{"a", "b", "c"};
		assertThat(actualSilben, is(expectedSilben));
	}
	
	@Test
	public void split() {
		assertThat(WordWrap.split("a", 1), is(Arrays.asList("a")));
	}
}
