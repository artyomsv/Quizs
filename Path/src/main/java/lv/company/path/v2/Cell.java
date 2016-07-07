package lv.company.path.v2;

public interface Cell {

    boolean block();

    boolean end();

    Cell goRight();

    Cell goDown();

}
