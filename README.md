# 🛒 E-Commerce System – Fawry Rise Journey Internship Program Challenge

## 📌 Overview

This is a fully functional object-oriented E-Commerce system implemented in **Java**, designed to fulfill the requirements of the **Fawry Rise Journey Internship Program Challenge**. The system simulates core e-commerce functionality such as product definition (with expiration and shipping handling), cart management, checkout processing, and shipping services.

## ✅ Features

- **Product Types**
    - Expirable products (e.g., Cheese, Biscuits)
    - Non-expirable products (e.g., TV, Scratch Cards)
    - Non-expirable Shippable items with weights
    - Expirable Shippable items with weights
- **Cart System**
    - Add products with specific quantity (validated against stock)
    - Handle perishable and non-perishable logic
- **Checkout Process**
    - Calculates subtotal, shipping fees, and total
    - Validates product availability, expiry, and customer balance
    - Generates a detailed invoice and shipment notice
- **Shipping Service**
    - Collects and prints shippable items in a well-formatted table
    - Calculates total package weight

## 🧩 Assumptions

- All product weights are in **kilograms**
- Expiry dates are checked against **current system date**
- Shipping fees is a **fixed 100** if any item requires shipping
- Only **valid, available, and non-expired** products can be purchased

## 📂 Project Structure

```
src/
│
├── Main.java                           # Entry point with usage examples
├── Enities/
│   ├── Product.java                    # Abstract base class for all products
│   ├── ExpirableProduct.java           # Products with expiration
│   ├── ExpirableShippableProduct.java  # Products with expiration and Implements shipping logic
│   ├── NonExpiryProduct.java           # Products without expiration
│   ├── NonExpiryShippableProduct.java  # Products without expiration and Implements shipping logic
│   ├── Cart.java                       # Holds multiple cart items
│   ├── CartItem.java                   # Represents item and quantity
│   ├── Customer.java                   # Customer with balance info
│   ├── PerishableShippableProduct.java
│   └── interfaces/
│        ├──Shippable.java
│        └──Expirable.java
│
├── services/
│   ├── CheckoutService.java    # Handles validation, payment, and printing
│   └── ShippingService.java    # Handles shipping label formatting
```

## 🚀 How to Run

> **Requirements:** Java 8+

1. Clone the repository

```bash
git https://github.com/aahhmmeeddaammr/E-Commerce-Fawry-Task-.git
```

2. Compile and Run

```bash
javac Main.java
java Main
```

## 🧪 Example Scenario

```java
Customer customer = new Customer("Ahmed", 1000);
// Test Products
Product cheese = new ExpirableShippableProduct("Cheese", 100, 20, LocalDate.now().plusDays(5), 0.2);
Product biscuits = new ExpirableShippableProduct("Biscuits", 150, 5, LocalDate.now().plusDays(2), 0.7);
Product tv = new NonExpiryShippableProduct("TV", 300, 3, 5);
Product scratchCard = new NonExpiryProduct("Scratch Card", 50, 20);
// Generate user cart
Cart cart = new Cart();
// add products into cart
        cart.add(cheese, 1);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);
        cart.add(tv, 1);
// checkout
        CheckoutService.checkout(customer, cart);
```

### ✅ Sample Console Output

```
** Shipment notice **
Qty   Item                     Weight (g)
--------------------------------------------------
2     Cheese                   400
1     Biscuits                 700
--------------------------------------------------
Total package weight:          1.1kg

** Checkout receipt **
Qty   Item                 Unit Price Total
--------------------------------------------------------
2     Cheese               100.00     200.00
1     Biscuits             150.00     150.00
1     Scratch Card         50.00      50.00
--------------------------------------------------------
Subtotal:                              400.00
Shipping:                              30.00
Total:                                 430.00
Balance left:                          570.00
```

## ❗️Validation & Error Handling

- ❌ Adding more quantity than available → `IllegalArgumentException`
- ❌ Adding expired product → `IllegalArgumentException`
- ❌ Insufficient customer balance → `IllegalArgumentException`
- ❌ Empty cart checkout → `IllegalArgumentException`

## 🧠 Key OOP Concepts Applied

- **Abstraction & Inheritance:** Product hierarchy
- **Encapsulation:** Data is properly protected through private fields
- **Interfaces:** Shipping logic via `Shippable` interface and Expriration logic via `Expirable` interface
- **Polymorphism:** Treating products via abstract/interface references

## 🧪 Tests and Edge Cases Covered

| Case                     | Expected Behavior                       |
| ------------------------ | --------------------------------------- |
| Product is expired       | Prevent checkout                        |
| Quantity > stock         | Throws exception                        |
| Cart is empty            | Throws exception                        |
| Insufficient balance     | Throws exception                        |
| Only non-shippable items | No shipping fee applied                 |
| Multiple shippable items | Weight aggregated, shipping fee applied |

## 📄 License

This project is for educational purposes under the **Fawry Rise Journey Internship Program Challenge** guidelines.

## 👤 Author

**Ahmed Amr**  
Java | TypeScript | Web Developer  
Contact: [aamr24987@gmail.com]  
GitHub: [github.com/aahhmmeeddaammr](https://github.com/aahhmmeeddaammr)

---

> Feel free to fork this repository, adapt it, and use it for future OOP or systems design interviews.
