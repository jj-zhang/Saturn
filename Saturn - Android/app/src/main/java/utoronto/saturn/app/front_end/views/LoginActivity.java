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
import java.sql.Statement;

import utoronto.saturn.*;
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

        pgsqlcon pgcon = new pgsqlcon();
        pgcon.execute();
    }

    private void onButtonClickAction(View v) {

        AutoCompleteTextView usernameView = findViewById(R.id.txt_username);
        Editable username = usernameView.getText();
        TextInputEditText passwordView = findViewById(R.id.txt_password);
        Editable password = passwordView.getText();

        // TODO: output a message if these are empty
        if (username == null || password == null) return;
        String username_string = username.toString();
        String password_string = password.toString();
        System.out.println(username_string + " " + password_string);
        User user = mViewModel.checkLogin(username_string, password_string);
        // TODO: output a message if the user is not found

        if (user == null) {
            Snackbar error_message = Snackbar.make(v , "Invalid email. Please try again.",
                    2000);
            error_message.show();
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Intent.EXTRA_EMAIL, user.getEmail());
        startActivity(intent);
    }


    private class pgsqlcon extends AsyncTask<Void, Void, Void> {

        public pgsqlcon() {
            super();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Connection conn = null;
            Statement st = null;

            try {
                //STEP 2: Register JDBC driver
                Class.forName("org.postgresql.Driver");

                //STEP 3: Open a connection
                Log.d("myTag", "Connecting to database...");

                conn = DriverManager.getConnection("jdbc:postgresql://tantor.db.elephantsql.com:5432/tjlevpcn"
                        , "tjlevpcn", "SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu");
            } catch (Exception e){
                System.out.println(e.getStackTrace());
            }
            return null;
        }

    }
}
