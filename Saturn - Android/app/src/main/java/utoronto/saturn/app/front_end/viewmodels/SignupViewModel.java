package utoronto.saturn.app.front_end.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.sql.SQLException;

import utoronto.saturn.User;
import utoronto.saturn.UserDatabase;
import utoronto.saturn.app.GuiManager;

public class SignupViewModel extends ViewModel {

    /*
        Checks to see if the entered email already exists. Returns True or False
        whether or not the email already exists in the database
     */
    public boolean checkLogin(String email, String password) {
        GuiManager guiManager = GuiManager.getInstance();

        // If the input is valid
        return guiManager.signUp(email, null, null, password);

    }
}
