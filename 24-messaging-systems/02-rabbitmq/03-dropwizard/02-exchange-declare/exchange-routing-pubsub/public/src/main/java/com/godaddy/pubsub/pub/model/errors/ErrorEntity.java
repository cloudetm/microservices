package com.godaddy.pubsub.pub.model.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class ErrorEntity {
    private final String code;
    private final String message;
    private final String stackTrace;
}
