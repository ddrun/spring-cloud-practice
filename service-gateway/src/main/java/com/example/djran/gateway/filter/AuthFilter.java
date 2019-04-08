package com.example.djran.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.isAlreadyRouted;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.setAlreadyRouted;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.setResponseStatus;

@Configuration
@Slf4j
public class AuthFilter implements GlobalFilter ,Ordered {

    private static final String INVOKE_TIME_BEGIN = "invokeTimeBegin";
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("进入AuthFilter");
        ServerHttpRequest request = exchange.getRequest();
        String accessToken = exchange.getRequest().getQueryParams().getFirst("access_token");
        if (accessToken == null || accessToken.isEmpty()) {
            setAlreadyRouted(exchange);
            return Mono.defer(() -> {
                System.out.println("进入AuthFilter return");
                setResponseStatus(exchange, HttpStatus.UNAUTHORIZED);
                final ServerHttpResponse response = exchange.getResponse();
                byte[] bytes = "Hello World".getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                response.getHeaders().set("aaa", "bbb");
                return response.writeWith(Flux.just(buffer));
            });
        }
        //记录beginTime
        exchange.getAttributes().put(INVOKE_TIME_BEGIN, System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(INVOKE_TIME_BEGIN);
                    if (startTime != null) {
                        log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
                    }
                })
        );
    }
}
