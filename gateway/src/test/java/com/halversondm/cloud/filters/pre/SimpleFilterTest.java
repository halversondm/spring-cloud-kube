package com.halversondm.cloud.filters.pre;

import com.netflix.zuul.context.RequestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleFilterTest {

    private SimpleFilter filter;

    @BeforeEach
    void setUp() {
        filter = new SimpleFilter();
        RequestContext.testSetCurrentContext(new RequestContext());
    }

    @AfterEach
    void tearDown() {
        RequestContext.testSetCurrentContext(null);
    }

    @Test
    void filterType_returnsPre() {
        assertThat(filter.filterType()).isEqualTo("pre");
    }

    @Test
    void filterOrder_returnsOne() {
        assertThat(filter.filterOrder()).isEqualTo(1);
    }

    @Test
    void shouldFilter_returnsTrue() {
        assertThat(filter.shouldFilter()).isTrue();
    }

    @Test
    void run_returnsNull() {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/some/path");
        RequestContext.getCurrentContext().setRequest(request);

        Object result = filter.run();

        assertThat(result).isNull();
    }
}
