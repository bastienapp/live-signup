package fr.wildcodeschool.signinsignout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference userRef = database.getReference("user");
        final String userId = mAuth.getUid();

        final EditText etName = findViewById(R.id.et_name);
        final EditText etAge = findViewById(R.id.et_age);

        userRef.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel user = dataSnapshot.getValue(UserModel.class);
                if (user != null) {
                    etName.setText(user.getName());
                    etAge.setText(String.valueOf(user.getAge()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Button disconnect = findViewById(R.id.button_disconnect);
        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(ProfileActivity.this, SignUpActivity.class));
                finish();
            }
        });

        Button updateProfile = findViewById(R.id.button_update);
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String ageValue = etAge.getText().toString();
                int age = 0;
                if (!ageValue.isEmpty()) {
                    age = Integer.parseInt(ageValue);
                }
                if (userId != null) {
                    userRef.child(userId).setValue(new UserModel(name, age));
                }
            }
        });
    }
}
