package Entities;

import Entities.Interfaces.Shippable;

import java.time.LocalDate;

public class ExpirableShippableProduct extends ExpirableProduct implements Shippable {
    private double weight;

    public ExpirableShippableProduct(String name, float price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity, expiryDate);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
