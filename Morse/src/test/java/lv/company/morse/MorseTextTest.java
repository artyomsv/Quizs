package lv.company.morse;

import lv.company.morse.tones.ToneFactory;
import org.junit.Test;

public class MorseTextTest {

    @Test
    public void name() throws Exception {
        new MorseText("Hello World").execute(ToneFactory.DEFAULT);
    }
}