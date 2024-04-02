package entities;

public class Game implements IEntity {
    private Long id;
    private String name;
    private boolean premiumStatus;
    private Long chanceID;
    private double minimalBet;
    private double maximumBet;

    public Game() {
    }

    public Game(Long id, String name, boolean premiumStatus, Long chanceID, double minimalBet, double maximumBet) {
        this.id = id;
        this.name = name;
        this.premiumStatus = premiumStatus;
        this.chanceID = chanceID;
        this.minimalBet = minimalBet;
        this.maximumBet = maximumBet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumStatus() {
        return premiumStatus;
    }

    public void setPremiumStatus(boolean premiumStatus) {
        this.premiumStatus = premiumStatus;
    }

    public Long getChance() {
        return chanceID;
    }

    public void setChance(Long chanceID) {
        this.chanceID = chanceID;
    }

    public double getMinimalBet() {
        return minimalBet;
    }

    public void setMinimalBet(double minimalBet) {
        this.minimalBet = minimalBet;
    }

    public double getMaximumBet() {
        return maximumBet;
    }

    public void setMaximumBet(double maximumBet) {
        this.maximumBet = maximumBet;
    }

    @Override
    public String toString() {
        return "Game [id=" + id + ", name=" + name + ", premiumStatus=" + premiumStatus + "]";
    }
}
