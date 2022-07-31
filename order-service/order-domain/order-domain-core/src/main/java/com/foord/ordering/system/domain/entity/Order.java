package com.foord.ordering.system.domain.entity;

import com.food.ordering.order.system.entity.AggregateRoot;
import com.food.ordering.order.system.valueobject.*;
import com.foord.ordering.system.domain.exception.OrderDomainException;
import com.foord.ordering.system.domain.valueobject.OrderItemId;
import com.foord.ordering.system.domain.valueobject.StreetAddress;
import com.foord.ordering.system.domain.valueobject.TrackingId;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Builder
public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress deliveryAddress;
    private final Money price;
    private final List<OrderItem> items;

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> listOfFailures;

    public void initializerOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        initializerOrderItems();
    }

    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    private void validateItemsPrice() {
        Money moneyItemTotal = items.stream()
                .map(item -> {
                   validateItemPrice(item);
                   return item.getTotalPrice();
                }).reduce(Money.ZERO, Money::add);

        if (!price.equals(moneyItemTotal)) {
            throw new OrderDomainException("Total Price: " + price.getValue() + " is not to equal to Ordem items total: "
                    + moneyItemTotal.getValue());
        }
    }

    private void validateItemPrice(OrderItem item) {
        if (!item.isPriceValid()) {
            throw new OrderDomainException("Order item price: " + item.getPrice().getValue() + " Is not valid for product: "
                    + item.getProduct().getId().getValue());
        }
    }

    public void pay() {
        if (orderStatus != OrderStatus.PENDING) {
            throw new OrderDomainException("Incorret status payment");
        }

        orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Incorret status payment");
        }

        orderStatus = OrderStatus.APPROVED;
    }


    public void cancel(List<String> failureMessages) {
        if (!(orderStatus == OrderStatus.CANCELLING) || orderStatus == orderStatus.PENDING) {
            throw new OrderDomainException("Incorret status payment");
        }

        orderStatus = OrderStatus.CANCELLED;
        updateFailuresMessage(failureMessages);

    }

    public void initCancel(List<String> failureMessages) {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Incorret status payment");
        }
        orderStatus = OrderStatus.CANCELLING;
        updateFailuresMessage(failureMessages);
    }

    private void updateFailuresMessage(List<String> failureMessages) {
        if (this.listOfFailures != null && failureMessages != null) {
            this.listOfFailures.addAll(failureMessages.stream().filter(message -> !message.isEmpty()).collect(Collectors.toList()));
        }

        if (this.listOfFailures == null) {
            this.listOfFailures = failureMessages;
        }
    }


    private void validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw new OrderDomainException("Total price must be greater than zero");
        }
    }

    private void validateInitialOrder() {
        if (orderStatus != null || getId() != null) {
            throw new OrderDomainException("Order is not corret state for initialization");
        }
    }

    private void initializerOrderItems() {
        long itemId = 1;
        for(OrderItem orderItem: items) {
            orderItem.initializeOrdemItens(super.getId(), new OrderItemId(itemId++));
        }
    }
}

