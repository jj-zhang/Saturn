package utoronto.saturn.app.front_end.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.sql.SQLException;

import utoronto.saturn.User;
import utoronto.saturn.UserDatabase;
import utoronto.saturn.app.GuiManager;

public class SignupViewModel extends ViewModel {

    /*
        Checks to see if the entered email already exists. Returns True if email exists and False
        if the email doesn't exists in the database
     */
    public boolean checkLogin(String email, String password) {
        GuiManager guiManager = GuiManager.getInstance();

        // Checks to see if user is successful in sign up
        return !guiManager.signUp(email, password);

    }
}
