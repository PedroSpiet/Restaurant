package com.foord.ordering.system.domain.entity;

import com.food.ordering.order.system.entity.AggregateRoot;
import com.food.ordering.order.system.valueobject.RestaurantId;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class Restaurant extends AggregateRoot<RestaurantId> {
    private final List<Product> products;
    private boolean active;

}
