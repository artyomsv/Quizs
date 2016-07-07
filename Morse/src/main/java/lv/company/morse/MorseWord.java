package lv.company.morse;

import lv.company.morse.codes.MorseCode;
import lv.company.morse.codes.MorseCodes;
import lv.company.morse.tones.ToneFactory;
import lv.company.morse.tones.TonesType;

class MorseWord {

    private final String word;

    MorseWord(String word) {
        this.word = word;
    }

    void execute(ToneFactory factory, CodesDictionary dictionary) {
        for (char c : word.toCharArray()) {
            MorseCodes codes = dictionary.get(Character.toUpperCase(c));
            MorseCode code = new MorseCode(codes.getTonesTypes());
            code.execute(factory);
        }

        for (int i = 0; i < 7; i++) {
            factory.build(TonesType.SPACE).execute();
        }

    }

}
