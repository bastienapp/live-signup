package fr.wildcodeschool.signinsignout;

public interface UserListener {

    void onSuccess(UserModel userModel);

    void onFailure(String error);
}
