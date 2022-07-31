package com.foord.ordering.system.domain.entity;

import com.food.ordering.order.system.entity.BaseEntity;
import com.food.ordering.order.system.valueobject.Money;
import com.food.ordering.order.system.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
    private String name;
    private Money price;

    public Product(String name, Money price, ProductId productId) {
        super.setId(productId);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}
