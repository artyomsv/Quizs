package lv.company.path.v2;

public class FreeCell implements Cell {

    private final Cell right;
    private final Cell bottom;

    public FreeCell(Cell right, Cell bottom) {
        this.right = right;
        this.bottom = bottom;
    }

    public boolean block() {
        return false;
    }

    public boolean end() {
        return false;
    }

    public Cell goRight() {
        return right;
    }


    public Cell goDown() {
        return bottom;
    }

}
