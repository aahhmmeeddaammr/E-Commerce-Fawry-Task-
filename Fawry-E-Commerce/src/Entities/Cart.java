package Entities;

import Interfaces.Shippable;

import java.util.ArrayList;
import java.util.List;
public class Cart {
    List<CartItem> items = new ArrayList<>();
    public void add(Product product, int quantity) {
        if (!product.isAvailable(quantity)) {
            throw new IllegalArgumentException("Not enough quantity"+" from this product " + product.getName() + " in the stock.");
        }
        items.add(new CartItem(product, quantity));
    }
    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotalForShippableProducts() {
        double subTotal = 0;
        List<CartItem> shippableItems = getShippableItems();
        for (CartItem item : shippableItems) {
            subTotal += item.getTotalPrice();
        }
        return subTotal;
    }

    public List<CartItem> getShippableItems() {
        List<CartItem> shippableItems = new ArrayList<>();
        for (CartItem item : this.items) {
            if (item.getProduct() instanceof Shippable) {
                shippableItems.add(item);
            }
        }
        return shippableItems;
    }

}
