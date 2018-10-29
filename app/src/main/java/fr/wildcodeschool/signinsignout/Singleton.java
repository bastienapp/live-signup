package fr.wildcodeschool.signinsignout;

import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class Singleton {
    private static final Singleton ourInstance = new Singleton();
    private UserModel mUser = null;

    static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }

    public void loadUser(String userId, final UserListener listener) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference userRef = database.getReference("user");
        if (userId != null) {
            userRef.child(userId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    mUser = dataSnapshot.getValue(UserModel.class);
                    listener.onSuccess(mUser);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // TODO afficher un message d'erreur ici
                    listener.onFailure(databaseError.getMessage());
                }
            });
        }
    }

    public UserModel getUser() {
        return mUser;
    }

    public void setUser(UserModel user) {
        mUser = user;
    }
}
