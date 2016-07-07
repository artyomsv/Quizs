package lv.company.morse;

import lv.company.morse.codes.MorseCodes;

import java.util.HashMap;
import java.util.Map;

class CodesDictionary {

    private Map<Character, MorseCodes> map;

    CodesDictionary() {
        map = new HashMap<Character, MorseCodes>(MorseCodes.values().length);
        for (MorseCodes code : MorseCodes.values()) {
            map.put(code.getValue(), code);
        }
    }

    public MorseCodes get(char letter) {
        if (map.containsKey(letter)) {
            return map.get(letter);
        }
        return MorseCodes.EMPTY;
    }
}
