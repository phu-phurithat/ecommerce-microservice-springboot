package com.phu.order_service.handler;

import java.util.Map;

public record ErrorResponse (
        Map<String, String> errors
) {

}
