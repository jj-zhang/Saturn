package utoronto.saturn.app.front_end.viewmodels;

import android.arch.lifecycle.ViewModel;

import utoronto.saturn.User;

public class SignupViewModel extends ViewModel {

    public User checkLogin(String username, String password) {
        // TODO: Implement login checking stuff
        return new User("John", "Doe", username);
    }

}
