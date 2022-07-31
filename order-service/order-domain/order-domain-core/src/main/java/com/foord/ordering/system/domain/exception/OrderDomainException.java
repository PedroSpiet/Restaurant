package com.foord.ordering.system.domain.exception;

import com.food.ordering.order.system.exception.DomainExceptionClass;

public class OrderDomainException extends DomainExceptionClass {
    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
