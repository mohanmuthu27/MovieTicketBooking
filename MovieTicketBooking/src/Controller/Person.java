package Controller;

public abstract class Person implements Displayable {
    int id;
    String name;
    String mobile;

    Person(int id, String name, String mobile) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
    }

    public void show() {
        System.out.println("ID: " + id + " Name: " + name + " Mobile: " + mobile);
    }
}