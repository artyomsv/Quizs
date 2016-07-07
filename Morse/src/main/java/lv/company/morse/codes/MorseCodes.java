package lv.company.morse.codes;

import lv.company.morse.tones.TonesType;

import static lv.company.morse.tones.TonesType.OFF;
import static lv.company.morse.tones.TonesType.ON;

public enum MorseCodes {

    ALFA('A', OFF, ON),
    BRAVO('B', ON, OFF, OFF, OFF),
    CHARLIE('C', ON, OFF, ON, OFF),
    DELTA('D', ON, OFF, OFF),
    ECHO('E', OFF),
    FOXTROT('F', OFF, OFF, ON, OFF),
    GOLF('G', ON, ON),
    HOTEL('H', OFF, OFF, OFF, OFF),
    INDIA('I', OFF, OFF),
    JULIETT('J', OFF, ON, ON, ON),
    KILO('K', ON, OFF, ON),
    LIMA('L', OFF, ON, OFF, OFF),
    MIKE('M', ON, ON),
    NOVEMBER('N', ON, OFF),
    OSCAR('O', ON, ON, ON),
    PAPA('P', OFF, ON, ON, OFF),
    QUEBEC('Q', ON, ON, OFF, ON),
    ROMEO('R', OFF, ON, OFF),
    SIERRA('S', OFF, OFF, OFF),
    TANGO('T', ON),
    UNIFORM('U', OFF, OFF, ON),
    VICTOR('V', OFF, OFF, OFF, ON),
    WHISKEY('W', OFF, ON, ON),
    XRAY('X', ON, OFF, OFF, ON),
    YANKEE('Y', ON, OFF, ON, ON),
    ZULU('Z', ON, ON, OFF, OFF),

    ONE('1', OFF, ON, ON, ON, ON),
    TWO('2', OFF, OFF, ON, ON, ON),
    THREE('3', OFF, OFF, OFF, ON, ON),
    FOUR('4', OFF, OFF, OFF, OFF, ON),
    FIVE('5', OFF, OFF, OFF, OFF, OFF),
    SIX('6', ON, OFF, OFF, OFF, OFF),
    SEVEN('7', ON, ON, OFF, OFF, OFF),
    EIGHT('8', ON, ON, ON, OFF, OFF),
    NINE('9', ON, ON, ON, ON, OFF),
    ZERO('0', ON, ON, ON, ON, ON),

    EMPTY(' ');


    private final Character value;
    private final TonesType[] tonesTypes;

    MorseCodes(Character value, TonesType... tonesTypes) {
        this.value = value;
        this.tonesTypes = tonesTypes;
    }

    public Character getValue() {
        return value;
    }

    public TonesType[] getTonesTypes() {
        return tonesTypes;
    }

}
