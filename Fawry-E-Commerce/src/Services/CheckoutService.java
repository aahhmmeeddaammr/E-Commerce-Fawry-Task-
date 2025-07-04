package Services;

import Entities.Cart;
import Entities.CartItem;
import Entities.Customer;
import Entities.Interfaces.Expirable;
import Entities.Interfaces.Shippable;

import java.util.HashMap;
import java.util.Map;

public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) {

        if (cart.isEmpty()) throw new IllegalArgumentException("Cart is empty");
       // this for loop check if product is expired or is Available
        for (CartItem item : cart.getItems()) {
            // safety condition using short circuit and
            if (item.getProduct() instanceof Expirable && ((Expirable) item.getProduct()).isExpired()) {
                throw new IllegalArgumentException("Product " + item.getProduct().getName() + " is expired.");
            }

            if (!item.getProduct().isAvailable(item.getQuantity())) {
                throw new IllegalArgumentException("Product " + item.getProduct().getName() + " is out of stock.");
            }
        }
        // get price only for shippable items in the cart
        double subtotal = cart.getSubtotalForShippableProducts();

        // I assume if there are products that need to be shipped, the cost will be $100.

        double shippingFees = cart.getShippableItems().isEmpty() ? 0 : 100;

        double total = subtotal + shippingFees;

        if (!customer.checkBalanceCanCover(total)) throw new IllegalArgumentException("Insufficient balance");

        Map<String, Integer> quantityMap = new HashMap<>();

        for (CartItem item : cart.getShippableItems()) {
            item.getProduct().setQuantity(item.getProduct().getQuantity() - item.getQuantity());
            // if there is duplicate products name will sum the quantity of all
            quantityMap.put(item.getProduct().getName(), quantityMap.getOrDefault(item.getProduct().getName(), 0) + item.getQuantity());
        }

        if (!cart.getShippableItems().isEmpty()) {
            ShippingService.ship(cart.getShippableItems().stream().map((p)->(Shippable) p.getProduct()).toList(), quantityMap);
        }

        customer.setBalance(customer.getBalance() - total);

        System.out.println("** Checkout receipt **");
        System.out.printf("%-5s %-20s %-10s %-10s%n", "Qty", "Item", "Unit Price", "Total");
        System.out.println("---------------------------------------------");
        for (CartItem item : cart.getShippableItems()) {
            System.out.printf("%-5d %-20s %-10.2f %-10.2f%n",
                    item.getQuantity(),
                    item.getProduct().getName(),
                    item.getProduct().getPrice(),
                    item.getTotalPrice());        }
        System.out.println("---------------------------------------------");
        System.out.printf("%-37s %-10.2f%n", "Subtotal:", subtotal);
        System.out.printf("%-37s %-10.2f%n", "Shipping:", shippingFees);
        System.out.printf("%-37s %-10.2f%n", "Total:", total);
        System.out.printf("%-37s %-10.2f%n", "Balance left:", customer.getBalance());
    }
}
