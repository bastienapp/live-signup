package fr.wildcodeschool.signinsignout;

public class UserModel {

    private String name;
    private int age;

    public UserModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public UserModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
