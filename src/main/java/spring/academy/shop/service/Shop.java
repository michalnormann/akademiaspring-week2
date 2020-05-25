package spring.academy.shop.service;


import spring.academy.shop.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public interface Shop {

    List<Product> basket = new ArrayList<>();

    default void addToBucket(Product product, BigDecimal price) {
        basket.add(product);
    };

    void sumBasket();

    default BigDecimal generatePrice() {
        BigDecimal random  = new BigDecimal(Math.random()*250+50);
        return random.setScale(2, RoundingMode.CEILING);
    }
}
