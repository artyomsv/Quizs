package lv.company.path.v3;

public class OutOfFieldCell implements Cell {

    public boolean blocked() {
        return true;
    }

    public boolean end() {
        return true;
    }

    public Cell goRight() {
        throw new UnsupportedOperationException("You cannot traverse on " + this.getClass().getSimpleName() + " cell");
    }

    public Cell goDown() {
        throw new UnsupportedOperationException("You cannot traverse on " + this.getClass().getSimpleName() + " cell");
    }

}
