package com.food.ordering.order.system.valueobject;

import java.util.UUID;

public class RestaurantId extends BaseId<UUID>{
    protected RestaurantId(UUID value) {
        super(value);
    }
}
