package utoronto.saturn;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class TestUser {

    @Test
    public void TestEmptyFirstName(){
        Throwable thrown = catchThrowable(() -> {new User("", "","");});
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void TestEmptyLastName(){
        Throwable thrown = catchThrowable(() -> {new User("a", "","");});
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void TestEmptyEmail(){
        Throwable thrown = catchThrowable(() -> {new User("a", "a","");});
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void TestEmailWithoutAt(){
        Throwable thrown = catchThrowable(() -> {new User("a", "a","a");});
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test(expected = IllegalStateException.class)
    public void TestDuplicateFollow () {
        User A = new User("a","a", "a@a");
        A.addToFollowedCreators(new User("b", "b", "b@b"));
        A.addToFollowedCreators(new User("c", "c", "c@c"));
        A.addToFollowedCreators(new User("b", "b", "b@b"));
    }
}
