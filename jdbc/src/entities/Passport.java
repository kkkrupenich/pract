package entities;

import java.sql.Date;

public class Passport implements IEntity {
    private Long id;
    private String serialNumber;
    private String identificationNumber;
    private String registration;
    private Date issueDate;
    private Date expirationDate;

    public Passport() {
    }

    public Passport(Long id, String serialNumber, String identificationNumber, String registration, Date issueDate,
            Date expirationDate) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.identificationNumber = identificationNumber;
        this.registration = registration;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Passport [id=" + id + ", serialNumber=" + serialNumber + "]";
    }
}
