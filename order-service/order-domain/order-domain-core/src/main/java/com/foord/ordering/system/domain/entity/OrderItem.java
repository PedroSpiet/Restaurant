package com.foord.ordering.system.domain.entity;

import com.food.ordering.order.system.entity.BaseEntity;
import com.food.ordering.order.system.valueobject.Money;
import com.food.ordering.order.system.valueobject.OrderId;
import com.foord.ordering.system.domain.valueobject.OrderItemId;
import com.foord.ordering.system.domain.valueobject.TrackingId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItem extends BaseEntity<OrderItemId> {
    private OrderId orderId;
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money totalPrice;

    public void initializeOrdemItens(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    boolean isPriceValid() {
        return price.isGreaterThanZero() &&
                price.equals(product.getPrice()) &&
                price.multiply(quantity).equals(totalPrice);
    }
}
