package utoronto.saturn.app.front_end.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.sql.ResultSet;
import java.sql.SQLException;

import utoronto.saturn.*;

public class LoginViewModel extends ViewModel{
    public User checkLogin(String email, String password) {
        // TODO: Implement login checking stuff
        User loginUser = new User(email, password);
        UserDatabase userDB = null;
        try {
            userDB = new UserDatabase(loginUser);
        } catch (SQLException e){
            // TODO: Display error
            e.printStackTrace();
        }

        // Get the resulting relations after selecting email
        ResultSet validUser = userDB.getAttribute("email");

        try {
            if (!validUser.next()) {
                // TODO: Implement popup saying invalid user
            } else {
                // TODO: Display error
                return loginUser;
            }
        } catch (SQLException e){

            e.printStackTrace();
        }
        return null;

    }

}
