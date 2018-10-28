package utoronto.saturn.app.front_end.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

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
    }

    private void onButtonClickAction(View v) {
        AutoCompleteTextView usernameView = findViewById(R.id.txt_username);
        Editable username = usernameView.getText();
        TextInputEditText passwordView = findViewById(R.id.txt_password);
        Editable password = passwordView.getText();

        // TODO: output a message if these are empty
        if (username == null || password == null) return;

        User user = mViewModel.checkLogin(username.toString(), password.toString());
        // TODO: output a message if the user is not found
        if (user == null) return;

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Intent.EXTRA_EMAIL, user.getEmail());
        startActivity(intent);
    }
}
