package com.foord.ordering.system.domain.valueobject;

import java.util.UUID;

public class StreetAddress {
    private final UUID uuid;
    private final String street;
    private final String postalCode;
    private final String city;

    public StreetAddress(UUID uuid, String street, String postalCode, String city) {
        this.uuid = uuid;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }
}
