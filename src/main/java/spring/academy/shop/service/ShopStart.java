package spring.academy.shop.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import spring.academy.shop.model.Product;

import java.math.BigDecimal;

@Profile("Start")
@Service
public class ShopStart implements Shop {

    public ShopStart() {
        basket.add(new Product("milk",generatePrice()));
        basket.add(new Product("butter",generatePrice()));
        basket.add(new Product("cheese",generatePrice()));
        basket.add(new Product("tomato",generatePrice()));
        basket.add(new Product("potato",generatePrice()));
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void sumBasket() {
        System.out.println("START Shop");
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Product product:basket) {
            System.out.println("Product: " + product.getName() + ", price: " + product.getPrice() + " PLN");
            sum = sum.add(product.getPrice());
        };
        System.out.println("Sum: " + sum + " PLN");
    }
}
