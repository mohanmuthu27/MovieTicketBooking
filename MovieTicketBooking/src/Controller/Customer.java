package Controller;

public class Customer extends Person {
    Customer(int id, String name, String mobile) {
        super(id, name, mobile);
    }

    @Override
    public void show() {
        System.out.println("Customer ID: " + id + " | " + name + " | " + mobile);
    }
}