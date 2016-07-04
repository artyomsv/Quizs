package lv.company.path.v1;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PathTest {

    @Test
    public void test1() throws Exception {
        byte[][] array = new byte[][]{
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1}

        };
        assertThat(Path.calculate(array), is(1));
    }

    @Test
    public void test2() throws Exception {
        byte[][] array = new byte[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}

        };
        assertThat(Path.calculate(array), is(2));
    }

    @Test
    public void test3() throws Exception {
        byte[][] array = new byte[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1}

        };
        assertThat(Path.calculate(array), is(3));
    }

    @Test
    public void test4() throws Exception {
        byte[][] array = new byte[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}

        };
        assertThat(Path.calculate(array), is(6));
    }


    @Test
    public void test5() throws Exception {
        byte[][] array = new byte[][]{
                {1, 1, 1}

        };
        assertThat(Path.calculate(array), is(1));
    }


    @Test
    public void test6() throws Exception {
        byte[][] array = new byte[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1}

        };
        assertThat(Path.calculate(array), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullInputData() throws Exception {
        Path.calculate(null);
    }

    @Test
    public void emptyData() throws Exception {
        assertThat(Path.calculate(new byte[0][0]), is(0));
    }
}