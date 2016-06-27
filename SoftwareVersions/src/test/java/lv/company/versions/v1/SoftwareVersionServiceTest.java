package lv.company.versions.v1;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SoftwareVersionServiceTest {

    @Test
    public void secondHigerThenFirst() throws Exception {
        assertThat(new SoftwareVersionService().compareVersions("1", "1.1.0"), is(-1));
    }

    @Test
    public void equalVersions() throws Exception {
        assertThat(new SoftwareVersionService().compareVersions("1", "1.0"), is(0));
    }

    @Test
    public void firstHigerThenSecond() throws Exception {
        assertThat(new SoftwareVersionService().compareVersions("1.2.5", "1.2.4"), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneOfTheVersionsIsNull() throws Exception {
        new SoftwareVersionService().compareVersions(null, "1.1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongFormat() throws Exception {
        new SoftwareVersionService().compareVersions("1.1", "1.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void containLetters() throws Exception {
        new SoftwareVersionService().compareVersions("2.1.a", "3");
    }
}