package lv.company.morse.codes;

import lv.company.morse.tones.ToneFactory;
import lv.company.morse.tones.TonesType;

public class MorseCode implements Code {

    private final TonesType[] tonesTypes;

    public MorseCode(TonesType... tonesTypes) {
        this.tonesTypes = tonesTypes;
    }

    public void execute(ToneFactory factory) {
        for (TonesType type : tonesTypes) {
            factory.build(type).execute();
        }
        factory.build(TonesType.SPACE).execute();
    }

}
