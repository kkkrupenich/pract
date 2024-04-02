package entities;

import java.sql.Date;

public class Subscription implements IEntity {
    private Long id;
    private boolean status;
    private Date expirationDate;

    public Subscription() {
    }

    public Subscription(Long id, boolean status, Date expirationDate) {
        this.id = id;
        this.status = status;
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Subscription [id=" + id + ", status=" + status + ", expirationDate=" + expirationDate + "]";
    }
}
