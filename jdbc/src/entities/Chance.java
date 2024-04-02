package entities;

public class Chance implements IEntity {
    private Long id;
    private double loseChance;
    private double returnChance;
    private double winChance;

    public Chance() {
    }

    public Chance(Long id, double loseChance, double returnChance, double winChance) {
        this.id = id;
        this.loseChance = loseChance;
        this.returnChance = returnChance;
        this.winChance = winChance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLoseChance() {
        return loseChance;
    }

    public void setLoseChance(double loseChance) {
        this.loseChance = loseChance;
    }

    public double getReturnChance() {
        return returnChance;
    }

    public void setReturnChance(double returnChance) {
        this.returnChance = returnChance;
    }

    public double getWinChance() {
        return winChance;
    }

    public void setWinChance(double winChance) {
        this.winChance = winChance;
    }

    @Override
    public String toString() {
        return "Chance [id=" + id + ", loseChance=" + loseChance + ", returnChance=" + returnChance + ", winChance="
                + winChance + "]";
    }
}
