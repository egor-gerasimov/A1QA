package project.model;

import java.util.Objects;

public class Game {

    private String id;
    private double finalPrice;
    private double originalPrice;
    private double discountRate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Game)) {
            return false;
        }
        Game game = (Game) o;
        return Double.compare(game.finalPrice, finalPrice) == 0 &&
            Double.compare(game.originalPrice, originalPrice) == 0 &&
            Double.compare(game.discountRate, discountRate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(finalPrice, originalPrice, discountRate);
    }
}
