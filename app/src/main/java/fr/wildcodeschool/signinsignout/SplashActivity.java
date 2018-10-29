package fr.wildcodeschool.signinsignout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getUid() != null) {
            Singleton.getInstance().loadUser(mAuth.getUid(),
                    new UserListener() {
                        @Override
                        public void onSuccess(UserModel userModel) {
                            startActivity(new Intent(SplashActivity.this, ProfileActivity.class));
                        }

                        @Override
                        public void onFailure(String error) {
                            // TODO : déconnecter de force l'utilisateur
                        }
                    });
        } else {
            startActivity(new Intent(SplashActivity.this, SignUpActivity.class));
        }
    }
}
