package ccretreat;

import static org.hamcrest.CoreMatchers.is;
import static ccretreat.WordWrap.umbrechen;
import static ccretreat.WordWrap.wörterUmbrechen;
import static org.junit.Assert.*;

import org.junit.Test;

public class WordWrapTest {
	
	@Test
	public void textWithSingleLetterLinesWrapsAtLengthOne() {
		assertThat(umbrechen("a\nb\nc", 1), is("a\nb\nc"));
	}
	
	@Test
	public void wordsAreSeparatedByOneSpace(){
		assertThat(wörterUmbrechen("a b c"), is(new String[]{"a", "b", "c"}));
	}
	
	@Test
	public void surroundingSpacesAreIgnored() {
		assertThat(wörterUmbrechen(" a b c "), is(new String[]{"a", "b", "c"}));
	}
	
	@Test
	public void multipleSpacesAreIgnored() {
		assertThat(wörterUmbrechen("a  b c  "), is(new String[]{"a", "b", "c"}));
	}
	
	@Test
	public void commasStickToWord() {
		assertThat(wörterUmbrechen("a ,b c,"), is(new String[]{"a", ",b", "c,"}));
	}

	@Test
	public void commasAsWords() {
		assertThat(wörterUmbrechen("a , b c,"), is(new String[]{"a", ",", "b", "c,"}));
	}
	
	@Test
	public void newlinesAreIgnored() {
		assertThat(wörterUmbrechen("\na\n\nb\n"), is(new String[]{"a", "b"}));
	}
	
	@Test
	public void wordsAreYankedWithNewline() {
		assertThat(WordWrap.ausgabeTextAufbereiten(new String[]{"x", "y", "z"}), is("x\ny\nz"));
	}
}
