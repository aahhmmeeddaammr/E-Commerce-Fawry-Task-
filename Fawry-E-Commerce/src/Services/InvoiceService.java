package Services;

import Entities.Cart;
import Entities.CartItem;

public class InvoiceService {
    public static void printCheckoutInvoice(Cart cart , double subtotal , double shippingFees , double total  , double customerBalance) {
        System.out.println("** Checkout receipt **");
        System.out.printf("%-5s %-20s %-10s %-10s%n", "Qty", "Item", "Unit Price", "Total");
        System.out.println("---------------------------------------------");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%-5d %-20s %-10.2f %-10.2f%n",
                    item.getQuantity(),
                    item.getProduct().getName(),
                    item.getProduct().getPrice(),
                    item.getTotalPrice());        }
        System.out.println("---------------------------------------------");
        System.out.printf("%-37s %-10.2f%n", "Subtotal:", subtotal);
        System.out.printf("%-37s %-10.2f%n", "Shipping:", shippingFees);
        System.out.printf("%-37s %-10.2f%n", "Total:", total);
        System.out.printf("%-37s %-10.2f%n", "Balance left:", customerBalance);
    }
}
