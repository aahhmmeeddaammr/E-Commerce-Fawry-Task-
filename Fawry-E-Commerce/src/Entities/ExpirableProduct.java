package Entities;

import Interfaces.Expirable;

import java.time.LocalDate;

public class ExpirableProduct extends Product implements Expirable {
    private LocalDate expiryDate;

    public ExpirableProduct(String name, float price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }
    public LocalDate getExpiryDate() {return expiryDate;}
    public void setExpiryDate(LocalDate expiryDate) {this.expiryDate = expiryDate;}
    @Override
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }
}
