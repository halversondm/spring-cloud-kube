package com.halversondm.cloud.filters.pre;

import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SimpleFilterTest {

    private final SimpleFilter filter = new SimpleFilter();

    @Test
    void getOrder_returnsOne() {
        assertThat(filter.getOrder()).isEqualTo(1);
    }

    @Test
    void filter_callsChainAndCompletesSuccessfully() {
        MockServerHttpRequest request = MockServerHttpRequest
                .get("http://localhost/some/path")
                .build();
        MockServerWebExchange exchange = MockServerWebExchange.from(request);

        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(exchange)).thenReturn(Mono.empty());

        Mono<Void> result = filter.filter(exchange, chain);

        assertThat(result).isNotNull();
        result.block();
    }
}
