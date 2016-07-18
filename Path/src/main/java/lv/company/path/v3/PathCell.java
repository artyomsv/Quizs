package lv.company.path.v3;

public class PathCell implements Cell {

    private final Cell right;
    private final Cell down;
    private final boolean pathEnd;

    public PathCell(Cell right, Cell down) {
        this(right, down, false);
    }

    public PathCell(Cell right, Cell down, boolean pathEnd) {
        this.right = right;
        this.down = down;
        this.pathEnd = pathEnd;
    }

    public boolean blocked() {
        return false;
    }

    public boolean end() {
        return pathEnd;
    }

    public Cell goRight() {
        return right;
    }

    public Cell goDown() {
        return down;
    }

}
