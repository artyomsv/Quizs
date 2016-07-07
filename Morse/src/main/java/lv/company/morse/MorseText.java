package lv.company.morse;

import lv.company.morse.tones.ToneFactory;

import java.util.StringTokenizer;

public class MorseText {

    private final String text;

    public MorseText(String text) {
        this.text = text;
    }

    public void execute(ToneFactory factory) {
        CodesDictionary dictionary = new CodesDictionary();
        StringTokenizer tokenizer = new StringTokenizer(text, " ,.;");
        while (tokenizer.hasMoreElements()) {
            MorseWord morseWord = new MorseWord(tokenizer.nextToken());
            morseWord.execute(factory, dictionary);
        }

    }

}
