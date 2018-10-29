package utoronto.saturn;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class TestUser {

    @Test
    public void TestEmptyUsername(){
        Throwable thrown = catchThrowable(() -> {new User("", "m@gic@user.ca", "abcabcabc");});
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void TestEmptyEmail(){
        Throwable thrown = catchThrowable(() -> {new User("magic", "", "abcabcabc");});
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void TestEmptyPassword(){
        Throwable thrown = catchThrowable(() -> {new User("magic", "a@a", "");});
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void TestPasswordTooShort(){
        // TODO: Actually do this
        assertThat(true);
    }

    @Test
    public void TestEmailWithoutAt(){
        Throwable thrown = catchThrowable(() -> {new User("magic", "aa", "aefaefaef");});
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test(expected = IllegalStateException.class)
    public void TestDuplicateFollow () {
        User A = new User("magic", "a@a","aefaefaef");
        A.addToFollowedCreators(new User("magic", "b@b", "abcabcabc"));
        A.addToFollowedCreators(new User("magic", "c@c", "cdecdecde"));
        A.addToFollowedCreators(new User("magic", "b@b", "abcabcabc"));
    }
}
