package fr.wildcodeschool.signinsignout;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fr.wildcodeschool.signinsignout.listener.LoadUserListener;

class UserSingleton {
    private static UserSingleton ourInstance = null;
    private UserModel user = null;
    private ArrayList<UserModel> listUser = new ArrayList<>();

    static UserSingleton getInstance() {
        if (ourInstance == null) {
            ourInstance = new UserSingleton();
        }
        return ourInstance;
    }

    private UserSingleton() {
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void clear() {
        ourInstance = null;
    }

    public void loadUser(String userId, final LoadUserListener listener) {
        if (user != null) {
            listener.onSuccess();
        }
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference userRef = database.getReference("user");
        userRef.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel user = dataSnapshot.getValue(UserModel.class);
                setUser(user);
                // TODO : prévenir l'appelant de la méthode que j'ai terminé
                listener.onSuccess();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // TODO afficher un message d'erreur ici
                listener.onFailure();
            }
        });
    }
}
