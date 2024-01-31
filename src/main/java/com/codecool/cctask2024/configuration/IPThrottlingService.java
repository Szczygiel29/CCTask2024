package com.codecool.cctask2024.configuration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class IPThrottlingService implements HandlerInterceptor {

    @Value("${throttling.capacity}")
    long capacity;

    @Value("${throttling.refill-period-in-seconds}")
    long refillPeriodInMinutes;

    @Value("${throttling.refill-tokens}")
    long refillTokens;

    @Value("#{'${throttling.white-list}'.split(',')}")
    List<String> excludedEndpoints;

    final Cache<String, Bucket> ipBucketMap = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        String ipAddress = request.getRemoteAddr();

        if (excludedEndpoints.contains(request.getRequestURI())) {
            return true;
        }

        Bucket bucket = ipBucketMap.get(ipAddress, () ->
                Bucket.builder()
                        .addLimit(Bandwidth.classic(capacity,
                                Refill.intervally(refillTokens, Duration.ofMinutes(refillPeriodInMinutes))))
                        .build()
        );

        if (bucket.tryConsume(1)) {
            return true;
        } else {
            response.setStatus(429);
            response.getWriter().write("{ \"status\": 429, \"error\": \"Too Many Requests\", \"message\": \"You have exhausted your API Request Quota\" }");
            return false;
        }

    }
}
