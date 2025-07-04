package Services;

import Entities.Cart;
import Entities.CartItem;
import Entities.Customer;
import Entities.Interfaces.Expirable;
import Entities.Interfaces.Shippable;

import java.util.HashMap;
import java.util.Map;
/**
 * Provides functionality to process a checkout operation for a customer's cart.
 * This service validates the cart, calculates costs, processes shipping, and updates
 * the customer's balance, generating a formatted receipt upon successful checkout.
 */
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
        // get price for all items in the cart
        double subtotal = cart.getTotalPriceForProducts();

        // I assume if there are products that need to be shipped, the cost will be $100.

        double shippingFees = cart.getShippableItems().isEmpty() ? 0 : 100;

        double total = subtotal + shippingFees;

        if (!customer.checkBalanceCanCover(total)) throw new IllegalArgumentException("Insufficient balance");

        Map<String, Integer> quantityMap = new HashMap<>();

        for (CartItem item : cart.getItems()) {
            item.getProduct().setQuantity(item.getProduct().getQuantity() - item.getQuantity());
            // if there is duplicate products name will sum the quantity of all
            quantityMap.put(item.getProduct().getName(), quantityMap.getOrDefault(item.getProduct().getName(), 0) + item.getQuantity());
        }

        if (!cart.getShippableItems().isEmpty()) {
            //send only shippable items
            ShippingService.ship(cart.getShippableItems().stream().map((p)->(Shippable) p.getProduct()).toList(), quantityMap);
        }

        customer.setBalance(customer.getBalance() - total);

        InvoiceService.printCheckoutInvoice(cart , subtotal , shippingFees , total  ,customer.getBalance());
    }
}
