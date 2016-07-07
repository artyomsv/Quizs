package lv.company.morse.tones;

public interface ToneFactory {

    ToneFactory DEFAULT = new StringToneFactory();

    Tone build(TonesType type);

}
