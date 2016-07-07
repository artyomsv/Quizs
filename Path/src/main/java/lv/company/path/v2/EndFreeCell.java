package lv.company.path.v2;

public class EndFreeCell extends FreeCell {

    public EndFreeCell(Cell right, Cell bottom) {
        super(right, bottom);
    }

    @Override
    public boolean end() {
        return true;
    }
}
