package com.foord.ordering.system.domain.valueobject;

import com.food.ordering.order.system.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {

    public TrackingId(UUID value) {
        super(value);
    }
}
