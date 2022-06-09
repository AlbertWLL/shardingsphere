package com.example.danque.util;


import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.MDC;

public class TraceIdMessageConverter extends MessageConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return TraceLogUtils.genTracgId();
    }
}
