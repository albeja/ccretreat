package ccretreat;

import static ccretreat.WordWrap.w�rterUmbrechen;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class W�rterUmbrechenTest {

	@Test
	public void wordsAreSeparatedByOneSpace(){
		assertThat(w�rterUmbrechen("a b c"), is(new String[]{"a", "b", "c"}));
	}
	
	@Test
	public void surroundingSpacesAreIgnored() {
		assertThat(w�rterUmbrechen(" a b c "), is(new String[]{"a", "b", "c"}));
	}
	
	@Test
	public void multipleSpacesAreIgnored() {
		assertThat(w�rterUmbrechen("a  b c  "), is(new String[]{"a", "b", "c"}));
	}
	
	@Test
	public void commasStickToWord() {
		assertThat(w�rterUmbrechen("a ,b c,"), is(new String[]{"a", ",b", "c,"}));
	}

	@Test
	public void commasAsWords() {
		assertThat(w�rterUmbrechen("a , b c,"), is(new String[]{"a", ",", "b", "c,"}));
	}
	
	@Test
	public void newlinesAreIgnored() {
		assertThat(w�rterUmbrechen("\na\n\nb\n"), is(new String[]{"a", "b"}));
	}
}
