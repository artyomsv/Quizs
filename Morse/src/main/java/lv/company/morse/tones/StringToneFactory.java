package lv.company.morse.tones;

class StringToneFactory implements ToneFactory {

    StringToneFactory() {
    }

    public Tone build(TonesType type) {
        switch (type) {
            case ON:
                return new StringOnTone();
            case OFF:
                return new StringOffTone();
            case SPACE:
            default:
                return new StringSpaceTone();
        }
    }

    private class StringOffTone implements Tone {
        public void execute() {
            System.out.print("*");
        }
    }

    private class StringOnTone implements Tone {
        public void execute() {
            System.out.print("-");
        }
    }

    private class StringSpaceTone implements Tone {
        public void execute() {
            System.out.print(" ");
        }
    }

}
