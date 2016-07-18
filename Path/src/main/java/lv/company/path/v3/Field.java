package lv.company.path.v3;

public class Field {

    private static final int FREE_CELL = 1;
    private final byte[][] array;

    public Field(byte[][] array) {
        if (array == null) {
            throw new IllegalArgumentException("Initial array is null");
        }
        this.array = array;
    }

    public int traverse() {
        if (array.length == 0) {
            return 0;
        }
        Cell cell = buildCellsTree();
        return traverse(cell, 0);
    }

    private int traverse(Cell cell, int ways) {
        if (cell.blocked()) {
            return ways;
        } else if (cell.end()) {
            return ways + 1;
        }

        ways = traverseInToDirection(cell.goRight(), ways);
        ways = traverseInToDirection(cell.goDown(), ways);

        return ways;
    }

    private int traverseInToDirection(Cell cell, int ways) {
        if (!cell.blocked()) {
            return traverse(cell, ways);
        } else {
            return ways;
        }
    }

    private Cell buildCellsTree() {
        Cell latest = null;
        Cell[] buffer = getBlockedCellArray(array[0]);

        boolean endCell = true;
        for (int y = array.length - 1; y >= 0; y--) {
            latest = new OutOfFieldCell();
            for (int x = array[y].length - 1; x >= 0; x--) {
                Cell current = buildCurrentCell(latest, buffer[x], endCell, array[y][x] == FREE_CELL, endCell);
                endCell = false;
                buffer[x] = current;
                latest = current;
            }
        }

        return latest;
    }

    private Cell buildCurrentCell(Cell latest, Cell fromBottom, boolean endCell, boolean isFreeCell, boolean isEndCell) {
        Cell current;
        if (isFreeCell) {
            current = new PathCell(latest, fromBottom, endCell);
        } else {
            current = new BlockedPathCell(latest, fromBottom, endCell);
        }
        return current;
    }

    private Cell[] getBlockedCellArray(byte[] bytes) {
        Cell bottomCells[] = new Cell[bytes.length];
        for (int i = 0; i < bottomCells.length; i++) {
            bottomCells[i] = new OutOfFieldCell();
        }
        return bottomCells;
    }
}
