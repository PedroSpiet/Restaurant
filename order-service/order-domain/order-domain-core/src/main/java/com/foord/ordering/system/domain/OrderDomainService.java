package com.foord.ordering.system.domain;

import com.foord.ordering.system.domain.entity.Order;
import com.foord.ordering.system.domain.entity.Restaurant;
import com.foord.ordering.system.domain.event.OrderCancelledEvent;
import com.foord.ordering.system.domain.event.OrderCreatedEvent;
import com.foord.ordering.system.domain.event.OrderPaidEvents;

import java.util.List;

public interface OrderDomainService {
    OrderCreatedEvent initializeAndValidateOrder(Order order, Restaurant restaurant);

    OrderPaidEvents payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}

