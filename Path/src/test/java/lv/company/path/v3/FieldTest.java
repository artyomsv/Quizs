package lv.company.path.v3;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FieldTest {

    @Test
    public void test1() throws Exception {
        byte[][] array = new byte[][]{
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1}

        };
        assertThat(new Field(array).traverse(), is(1));
    }

    @Test
    public void test2() throws Exception {
        byte[][] array = new byte[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}

        };
        assertThat(new Field(array).traverse(), is(2));
    }

    @Test
    public void test3() throws Exception {
        byte[][] array = new byte[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1}

        };
        assertThat(new Field(array).traverse(), is(3));
    }

    @Test
    public void test4() throws Exception {
        byte[][] array = new byte[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}

        };
        assertThat(new Field(array).traverse(), is(6));
    }


    @Test
    public void test5() throws Exception {
        byte[][] array = new byte[][]{
                {1, 1, 1}

        };
        assertThat(new Field(array).traverse(), is(1));
    }


    @Test
    public void test6() throws Exception {
        byte[][] array = new byte[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1}

        };
        assertThat(new Field(array).traverse(), is(0));
    }

    @Test
    public void test7() throws Exception {
        byte[][] array = new byte[][]{
                {1, 1},
                {0, 1}

        };
        assertThat(new Field(array).traverse(), is(1));
    }


    @Test(expected = IllegalArgumentException.class)
    public void nullInputData() throws Exception {
        new Field(null).traverse();
    }

    @Test
    public void emptyData() throws Exception {
        assertThat(new Field(new byte[0][0]).traverse(), is(0));
    }

}