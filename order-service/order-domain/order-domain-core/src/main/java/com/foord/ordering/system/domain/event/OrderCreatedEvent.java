package com.foord.ordering.system.domain.event;

import com.food.ordering.order.system.event.DomainEvents;
import com.foord.ordering.system.domain.entity.Order;
import lombok.Getter;

import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent{
    public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
