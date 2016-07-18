package lv.company.path.v3;

public class BlockedPathCell extends PathCell {

    public BlockedPathCell(Cell right, Cell down) {
        super(right, down, false);
    }

    public BlockedPathCell(Cell right, Cell down, boolean pathEnd) {
        super(right, down, pathEnd);
    }

    @Override
    public boolean blocked() {
        return true;
    }

    @Override
    public Cell goRight() {
        return this;
    }

    @Override
    public Cell goDown() {
        return this;
    }
}
