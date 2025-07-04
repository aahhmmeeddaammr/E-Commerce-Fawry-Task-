package Services;

import Entities.Interfaces.Shippable;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * Provides functionality to process the shipping of items and generate a shipment notice.
 * This service calculates the total weight of shippable items and outputs a formatted
 * notice detailing the quantities, names, and weights of the items being shipped.
 */
public class ShippingService {
    public static void ship(List<Shippable> items, Map<String, Integer> quantities) {
        System.out.println("** Shipment notice **");
        System.out.printf("%-5s %-25s %-15s%n", "Qty", "Item", "Weight (g)");
        System.out.println("---------------------------------------------");

        double totalWeight = 0;
        Set<String> printed = new HashSet<>();

        for (Shippable item : items) {
            String name = item.getName();
            if (!printed.contains(name)) {
                int qty = quantities.get(name);
                double weightGrams = item.getWeight() * 1000 * qty;
                double totalWeightForItem = qty * item.getWeight();

                System.out.printf("%-5d %-25s %-15.0f%n", qty, name, weightGrams);
                totalWeight += totalWeightForItem;
                printed.add(name);
            }
        }

        System.out.println("---------------------------------------------");
        System.out.printf("%-31s %.1fkg%n", "Total package weight:", totalWeight);

    }
}
