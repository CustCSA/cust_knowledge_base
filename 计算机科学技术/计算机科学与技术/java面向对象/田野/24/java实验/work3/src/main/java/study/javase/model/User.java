package study.javase.model;

public class User {
    private int id;
    private String name;
    private String password;
    private float deposit;

    public User(int id, String name, String password, float deposit) {
        this.deposit = deposit;
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    public float getDeposit() {
        return deposit;
    }
}
