package Entities;

import Entities.Interfaces.Shippable;

public class NonExpiryShippableProduct extends NonExpiryProduct implements Shippable {
    private double weight;
    public NonExpiryShippableProduct(String name, float price, int quantity , double weight) {
        super(name , price , quantity);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

}
