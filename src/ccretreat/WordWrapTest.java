package ccretreat;

import static org.hamcrest.CoreMatchers.is;
import static ccretreat.WordWrap.umbrechen;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
	public void testName() {
		String[] worte = new String[]{"a", "bcd", "efghi"};
		int maximaleZeilenlänge = 2;
		
		String[] actualSilben = zuLangeWorteTrennen(worte, maximaleZeilenlänge);
		String[] expectedSilben = new String[]{"a", "bc", "d", "ef", "gh", "i"};

		assertThat(actualSilben, is(expectedSilben));
	}

	private String[] zuLangeWorteTrennen(String[] worte, int maximaleZeilenlänge) {
		List<String> silben = new ArrayList<>();
		for(String wort : worte){
			silben.addAll(split(wort, maximaleZeilenlänge));
		}
		return silben.toArray(new String[]{});
	}

	private List<String> split(String wort, int maximaleZeilenlänge) {
		List<String> silben = new ArrayList<>();
		if(wort.length() <= maximaleZeilenlänge){
			return Collections.singletonList(wort);
		}
		int loops = wort.length() / maximaleZeilenlänge;
		for(int i = 0; i < loops; i++) {
			silben.add(wort.substring(i * maximaleZeilenlänge, (i + 1) * maximaleZeilenlänge));
		}
		silben.add(wort.substring(maximaleZeilenlänge * loops));
		return silben;
	}
	
}
