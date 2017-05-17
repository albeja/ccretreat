package ccretreat;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AusgabeTextAufbereitenTest {
	@Test
	public void wordsAreYankedWithNewline() {
		assertThat(WordWrap.ausgabeTextAufbereiten(new String[]{"x", "y", "z"}), is("x\ny\nz"));
	}
}
