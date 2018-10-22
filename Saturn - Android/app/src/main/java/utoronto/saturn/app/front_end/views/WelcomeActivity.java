package utoronto.saturn.app.front_end.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import utoronto.saturn.app.R;
import utoronto.saturn.app.front_end.viewmodels.WelcomeViewModel;

public class WelcomeActivity extends AppCompatActivity {
    private WelcomeViewModel mViewModel;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        mViewModel = ViewModelProviders.of(this).get(WelcomeViewModel.class);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this::onLoginButtonClickAction);
    }

    private void onLoginButtonClickAction(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
