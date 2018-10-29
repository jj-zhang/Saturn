package utoronto.saturn.app.front_end.viewmodels;

import android.arch.lifecycle.ViewModel;
import utoronto.saturn.*;

public class LoginViewModel extends ViewModel{
    public User checkLogin(String username, String password) {
        // TODO: Implement login checking stuff
        return new User(username, "Doe@joe.ca", "123");
    }

}
