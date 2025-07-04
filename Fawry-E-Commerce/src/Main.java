import Entities.*;
import Services.CheckoutService;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Ahmed", 1000);
        // Test Products
        Product cheese = new ExpirableShippableProduct("Cheese", 100, 20, LocalDate.now().plusDays(5), 0.2);
        Product biscuits = new ExpirableProduct("Biscuits", 150, 5, LocalDate.now().plusDays(2));
        Product tv = new NonExpiryShippableProduct("TV", 300, 3, 5);
        Product scratchCard = new NonExpiryProduct("Scratch Card", 50, 20);
        // Generate user cart
        Cart cart = new Cart();
        // add products into cart
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);
        cart.add(tv, 1);
        // checkout
        CheckoutService.checkout(customer, cart);
    }
}