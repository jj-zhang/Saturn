package utoronto.saturn;

import org.junit.Test;

import java.net.URL;

import static org.assertj.core.api.Assertions.*;

public class TestEvent {
    private static final String id = "a";
    private static final String name = "b";
    private static final String desc = "c";
    private static final double rating = 1;
    private static final double userRating = 2;
    private static final long releaseDate = System.currentTimeMillis();
    private static final Event correctEvent = new Event(id, name,  null, releaseDate);

    @Test
    public void testIDIsCorrect() {
        assertThat(correctEvent.getID()).isEqualTo(id);
    }

    @Test
    public void testIDIsEmpty() {
        String id = "";

        assertThatThrownBy(() -> new Event(id, "a", null, 0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testNameIsCorrect() {
        assertThat(correctEvent.getName()).isEqualTo(name);
    }

    @Test
    public void testNameIsEmpty() {
        String name = "";

        assertThatThrownBy(() -> new Event("a", name,  null, 0)).isInstanceOf(IllegalArgumentException.class);
    }

//    @Test
//    public void testDescIsCorrect() {
//        assertThat(correctEvent.getDescription()).isEqualTo(desc);
//    }
//
//    @Test
//    public void testRatingIsCorrect() {
//        assertThat(correctEvent.getRating()).isEqualTo(rating);
//    }
//
//    @Test
//    public void testRatingIsNegative() {
//        assertThatThrownBy(() -> new Event("a", "a", "a", -2, 0, null, 0)).isInstanceOf(IllegalArgumentException.class);
//    }

//    @Test
//    public void testUserRatingIsCorrect() {
//        assertThat(correctEvent.getUserRating()).isEqualTo(userRating);
//    }
//
//    @Test
//    public void testUserRatingIsNegative() {
//        assertThatThrownBy(() -> new Event("a", "a", "a", 0, -2, null, 0)).isInstanceOf(IllegalArgumentException.class);
   //}

    @Test
    public void testReleaseDateIsCorrect() {
        assertThat(correctEvent.getReleaseDate()).isEqualTo(releaseDate);
    }

    @Test
    public void testReleaseDateIsNegative() {
        assertThatThrownBy(() -> new Event("a", "a",  null, -2)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testTimeTillReleaseIsCorrect() {
        assertThat(correctEvent.timeTillRelease()).isGreaterThanOrEqualTo(0);
    }

}
