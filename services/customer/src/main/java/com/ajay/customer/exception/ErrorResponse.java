package com.ajay.customer.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> error
) {
}
