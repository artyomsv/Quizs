package lv.company.versions.v2;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SoftwareVersionTest {

    @Test
    public void secondHigerThenFirst() throws Exception {
        assertThat(new SoftwareVersion("1").compareTo(new SoftwareVersion("1.1.0")), is(-1));
    }

    @Test
    public void equalVersions() throws Exception {
        assertThat(new SoftwareVersion("1").compareTo(new SoftwareVersion("1.0")), is(0));
    }

    @Test
    public void firstHihgerThenSecond() throws Exception {
        assertThat(new SoftwareVersion("1.2.5").compareTo(new SoftwareVersion("1.2.4")), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneOfTheVersionsIsNull() throws Exception {
        new SoftwareVersion(null).compareTo(new SoftwareVersion("1.1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongFormat() throws Exception {
        new SoftwareVersion("1.1").compareTo(new SoftwareVersion("1."));
    }

    @Test(expected = IllegalArgumentException.class)
    public void containLetters() throws Exception {
        new SoftwareVersion("2.1.a").compareTo(new SoftwareVersion("3"));
    }
}