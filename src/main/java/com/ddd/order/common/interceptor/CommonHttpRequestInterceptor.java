package com.ddd.order.common.interceptor;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Slf4j
@Component
public class CommonHttpRequestInterceptor implements HandlerInterceptor {

    public static final String HEADER_REQUEST_UUID_KEY = "x-request-id";

    @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            String requestEventId = request.getHeader(HEADER_REQUEST_UUID_KEY);
            if (StringUtils.isEmpty(requestEventId)) {
                requestEventId = UUID.randomUUID().toString();
            }

            MDC.put(HEADER_REQUEST_UUID_KEY, requestEventId);
            return true;
        }
}
