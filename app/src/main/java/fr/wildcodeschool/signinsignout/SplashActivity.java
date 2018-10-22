package fr.wildcodeschool.signinsignout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import fr.wildcodeschool.signinsignout.listener.LoadUserListener;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        final String userId = mAuth.getUid();
        if (userId != null) {
            final UserSingleton singleton = UserSingleton.getInstance();
            singleton.loadUser(userId, new LoadUserListener() {
                @Override
                public void onSuccess() {
                    startActivity(new Intent(SplashActivity.this, ProfileActivity.class));
                    finish();
                }

                @Override
                public void onFailure() {
                    // TODO afficher un message d'erreur ici
                }
            });
        } else {
            startActivity(new Intent(SplashActivity.this, SignUpActivity.class));
            finish();
        }
    }
}
