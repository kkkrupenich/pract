package entities;

import java.sql.Date;

public class Review implements IEntity {
    private Long id;
    private Long userID;
    private Long gameID;
    private String message;
    private String rating;
    private Date date;

    public Review() {
    }

    public Review(Long id, Long userID, Long gameID, String message, String rating, Date date) {
        this.id = id;
        this.userID = userID;
        this.gameID = gameID;
        this.message = message;
        this.rating = rating;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return userID;
    }

    public void setUser(Long userID) {
        this.userID = userID;
    }

    public Long getGame() {
        return gameID;
    }

    public void setGame(Long gameID) {
        this.gameID = gameID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
