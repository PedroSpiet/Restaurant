package com.foord.ordering.system.domain.valueobject;

import com.food.ordering.order.system.valueobject.BaseId;

import java.util.UUID;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}
