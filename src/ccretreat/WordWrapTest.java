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
	public void wordsAreSeperatedByOneSpace(){
		assertThat(wörterUmbrechen("a b c"), is(new String[]{"a", "b", "c"}));
	}
	
	@Test
	public void surroundingSpacesAreIgnored() {
		assertThat(wörterUmbrechen(" a b c "), is(new String[]{"a", "b", "c"}));
	}
	
	@Test
	public void multipleSpacesAreIgnored() throws Exception {
		assertThat(wörterUmbrechen("a  b c  "), is(new String[]{"a", "b", "c"}));
	}
	
	@Test
	public void commasStickToWord() throws Exception {
		assertThat(wörterUmbrechen("a ,b c,"), is(new String[]{"a", ",b", "c,"}));
	}

	@Test
	public void commasAsWords() throws Exception {
		assertThat(wörterUmbrechen("a , b c,"), is(new String[]{"a", ",", "b", "c,"}));
	}
}
