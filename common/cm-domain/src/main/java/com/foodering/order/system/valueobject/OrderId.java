package com.foodering.order.system.valueobject;

import com.foodering.order.system.entity.BaseEntity;

import java.util.UUID;

public class OrderId extends BaseId<UUID> {
    public OrderId(UUID uuid) {
        super(uuid);
    }
}
