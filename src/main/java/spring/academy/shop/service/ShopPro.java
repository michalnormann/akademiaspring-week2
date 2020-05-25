package spring.academy.shop.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import spring.academy.shop.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Profile("Pro")
@Service
public class ShopPro implements Shop {

    @Value("${shop.tax}")
    private BigDecimal tax;

    @Value("${shop.discount}")
    private BigDecimal discount;

    public ShopPro() {
        basket.add(new Product("milk",generatePrice()));
        basket.add(new Product("butter",generatePrice()));
        basket.add(new Product("cheese",generatePrice()));
        basket.add(new Product("tomato",generatePrice()));
        basket.add(new Product("potato",generatePrice()));
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void sumBasket() {
        System.out.println("PRO Shop");
        BigDecimal sum = BigDecimal.valueOf(0);
        BigDecimal sumTax = BigDecimal.valueOf(0);
        BigDecimal sumWithTax = BigDecimal.valueOf(0);

        for (Product product:basket) {

            BigDecimal productTax = product.getPrice().multiply(tax).divide(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
            BigDecimal productWithTax = product.getPrice().add(productTax);

            System.out.println("Product: " + product.getName() + ", price: " + product.getPrice() + " PLN, tax: " + productTax + " PLN, price with tax: " + productWithTax + " PLN.");
            sum = sum.add(product.getPrice());
            sumTax = sumTax.add(productTax);
            sumWithTax = sumWithTax.add(productWithTax);
        };
        System.out.println("Sum: " + sum + " PLN.");
        System.out.println("Tax sum: " + sumTax + " PLN.");
        System.out.println("Sum with tax: " + sumWithTax + " PLN.");
        System.out.println("Sum with discount " + discount + "%: " + sumWithTax.subtract(discount.divide(new BigDecimal(100)).multiply(sumWithTax)).setScale(2, RoundingMode.CEILING) + " PLN.");
    }

}
