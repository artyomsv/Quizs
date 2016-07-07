package lv.company.path.v2;

public class BlockCell implements Cell {

    public boolean block() {
        return true;
    }

    public boolean end() {
        return false;
    }

    public Cell goRight() {
        System.out.println("Right blocked");
        return this;
    }

    public Cell goDown() {
        System.out.println("Down blocked");
        return this;
    }

}
