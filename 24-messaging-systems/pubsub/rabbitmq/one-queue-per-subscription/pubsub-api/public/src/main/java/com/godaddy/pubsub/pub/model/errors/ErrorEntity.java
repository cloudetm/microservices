package com.godaddy.pubsub.pub.model.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class ErrorEntity {
    private final String code;
    private final String message;
    private final String stackTrace;

    @Getter
    private static String errorCodeInternalServerError = "INTERNAL_SERVER_ERROR";

    private static String errorMessageInternalServerError = "Internal server error";

    public static String convertStackTraceToString(final Throwable e){
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        e.printStackTrace(printWriter);
        return result.toString();
    }
}
