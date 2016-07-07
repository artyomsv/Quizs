package lv.company.morse.codes;

import lv.company.morse.tones.ToneFactory;

public interface Code {

    void execute(ToneFactory factory);

}
