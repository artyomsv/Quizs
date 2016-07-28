package lv.company.versions.v2;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SoftwareVersionTest {

    @Test
    public void secondHigerThenFirst() throws Exception {
        assertThat(wrap(new SoftwareVersion("1")).compareTo(wrap(new SoftwareVersion("1.1.0"))), is(-1));
    }

    @Test
    public void equalVersions() throws Exception {
        assertThat(wrap(new SoftwareVersion("1")).compareTo(wrap(new SoftwareVersion("1.0"))), is(0));
    }

    @Test
    public void firstHihgerThenSecond() throws Exception {
        assertThat(wrap(new SoftwareVersion("1.2.5")).compareTo(wrap(new SoftwareVersion("1.2.4"))), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneOfTheVersionsIsNull() throws Exception {
        wrap(new SoftwareVersion(null)).compareTo(wrap(new SoftwareVersion("1.1")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongFormat() throws Exception {
        wrap(new SoftwareVersion("1.1")).compareTo(wrap(new SoftwareVersion("1.")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void containLetters() throws Exception {
        wrap(new SoftwareVersion("2.1.a")).compareTo(wrap(new SoftwareVersion("3")));
    }

    private Version wrap(Version version) {
        return new NotNullableVersion(new NumericVersion(version));
    }
}