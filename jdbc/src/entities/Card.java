package entities;

import java.sql.Date;

public class Card implements IEntity {
    private Long id;
    private Long number;
    private Date expirationDate;
    private String holdersName;
    private int cvv;

    public Card() {
    }

    public Card(Long id, Long number, Date expirationDate, String holdersName, int cvv) {
        this.id = id;
        this.number = number;
        this.expirationDate = expirationDate;
        this.holdersName = holdersName;
        this.cvv = cvv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getHoldersName() {
        return holdersName;
    }

    public void setHoldersName(String holdersName) {
        this.holdersName = holdersName;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Card [id=" + id + ", number=" + number + "]";
    }
}
