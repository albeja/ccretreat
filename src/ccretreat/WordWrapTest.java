package ccretreat;

import static org.hamcrest.CoreMatchers.is;
import static ccretreat.WordWrap.umbrechen;
import static org.junit.Assert.*;

import org.junit.Test;

public class WordWrapTest {
	
	@Test
	public void textWithSingleLetterLinesWrapsAtLengthOne() {
		assertThat(umbrechen("a\nb\nc", 1), is("a\nb\nc"));
	}
	
}
