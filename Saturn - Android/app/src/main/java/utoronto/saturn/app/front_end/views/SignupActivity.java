package utoronto.saturn.app.front_end.views;

import android.arch.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;


import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import utoronto.saturn.User;
import utoronto.saturn.app.R;
import utoronto.saturn.app.front_end.viewmodels.LoginViewModel;
import utoronto.saturn.app.front_end.viewmodels.SignupViewModel;

public class SignupActivity extends AppCompatActivity {
    private SignupViewModel myViewModel;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("before set Content View");
        setContentView(R.layout.signup_activity);
        System.out.println("set Content View");
        myViewModel = ViewModelProviders.of(this).get(SignupViewModel.class);
        button = findViewById(R.id.signup_activity_button);
        button.setOnClickListener(this::onButtonClickAction);
        System.out.println("on create");
    }

    private void onButtonClickAction(View v) {

        AutoCompleteTextView usernameView = findViewById(R.id.txt_username);
        Editable username = usernameView.getText();
        AutoCompleteTextView emailView = findViewById(R.id.txt_email);
        Editable email = emailView.getText();
        TextInputEditText passwordView = findViewById(R.id.txt_password);
        Editable password = passwordView.getText();

        // TODO: output a message if these are empty
        if (username == null || password == null || email == null) return;



        if (myViewModel.checkLogin(email.toString(), password.toString())) {
            // TODO: output a message if the username exists is not found
            Snackbar error_message = Snackbar.make(v , "Email already exists. Please try again.",
                    2000);
            error_message.show();
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            return;
        }



        Intent intent = new Intent(this, WelcomeActivity.class);
        //intent.putExtra(Intent.EXTRA_EMAIL, myUser.getEmail());
        startActivity(intent);
    }
}
