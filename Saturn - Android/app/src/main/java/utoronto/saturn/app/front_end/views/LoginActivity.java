package utoronto.saturn.app.front_end.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utoronto.saturn.*;
import utoronto.saturn.app.GuiManager;
import utoronto.saturn.app.R;
import utoronto.saturn.app.front_end.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel mViewModel;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        button = findViewById(R.id.login_activity_button);
        button.setOnClickListener(this::onButtonClickAction);
    }

    private void onButtonClickAction(View v) {

        AutoCompleteTextView usernameView = findViewById(R.id.txt_username);
        Editable username = usernameView.getText();
        TextInputEditText passwordView = findViewById(R.id.txt_password);
        Editable password = passwordView.getText();

        // TODO: output a message if these are empty
        if (username.toString().equals("") || password.toString().equals("")) {
            removeKeyboard();
            Snackbar error_message = Snackbar.make(v , "Please fill in all text boxes.",
                    2000);
            error_message.show();
            return;
        }

        boolean res = GuiManager.getInstance().logIn(username.toString(), password.toString());

        // TODO: output a message if the user is not found

        if (!res) {
            removeKeyboard();
            Snackbar error_message = Snackbar.make(v , "Invalid email. Please try again.",
                    2000);
            error_message.show();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void removeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private class pgsqlcon extends AsyncTask<String, Void, Boolean> {
        Connection conn;
        PreparedStatement st;
        public pgsqlcon() {
            super();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            // check if username is good
            try {
                Class.forName("org.postgresql.Driver");
                //STEP 3: Open a connection
                Log.d("myTag", "Connecting to database...");
                conn = DriverManager.getConnection("jdbc:postgresql://tantor.db.elephantsql.com:5432/tjlevpcn"
                        , "tjlevpcn", "SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu");
                st = conn.prepareStatement("SELECT * FROM Users WHERE username = ?");
                st.setString(1, strings[0]);
                ResultSet result = st.executeQuery();
                if (result.next()) {
                    return true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }
}
