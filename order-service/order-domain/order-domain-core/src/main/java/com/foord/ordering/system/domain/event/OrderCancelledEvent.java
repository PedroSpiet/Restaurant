package com.foord.ordering.system.domain.event;

import com.food.ordering.order.system.event.DomainEvents;
import com.foord.ordering.system.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {

    public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
