package lv.company.path.v3;

public interface Cell {

    boolean blocked();

    boolean end();

    Cell goRight();

    Cell goDown();

}
