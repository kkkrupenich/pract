package entities;

public class User implements IEntity {
    private Long id;
    private String email;
    private String password;
    private String fio;
    private Long passportID;
    private Long roleID;
    private double balance;
    private Long subscription;

    public User() {
    }

    public User(Long id, String email, String password, String fio, Long passportID, Long roleID, double balance,
            Long subscription) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fio = fio;
        this.passportID = passportID;
        this.roleID = roleID;
        this.balance = balance;
        this.subscription = subscription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Long getPassportID() {
        return passportID;
    }

    public void setPassportID(Long passportID) {
        this.passportID = passportID;
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getSubscription() {
        return subscription;
    }

    public void setSubscription(Long subscription) {
        this.subscription = subscription;
    }
}
