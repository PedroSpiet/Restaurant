package com.foord.ordering.system.domain;

import com.foord.ordering.system.domain.entity.Order;
import com.foord.ordering.system.domain.entity.Restaurant;
import com.foord.ordering.system.domain.event.OrderCancelledEvent;
import com.foord.ordering.system.domain.event.OrderCreatedEvent;
import com.foord.ordering.system.domain.event.OrderPaidEvents;
import com.foord.ordering.system.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;


public class OrderDomainServiceImpl implements OrderDomainService{
    @Override
    public OrderCreatedEvent initializeAndValidateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        order.validateOrder();
        order.initializerOrder();
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()) {
            throw new OrderDomainException("Restaurant is not active ID: " + restaurant.getId());
        }
    }

    @Override
    public OrderPaidEvents payOrder(Order order) {
        order.pay();
        return new OrderPaidEvents(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
    }
}
