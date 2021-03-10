package com.example.config;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class PrometheusConfig {

    //  grafana에서 많은 scrape target이 있을 때 tag를 가지고 구분을 할 수 있게 해준다.
    @Bean
    MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizerTags() {
        return registry -> registry.config().commonTags("application", "PROMETHEUS-DEMO-SERVER");
    }
}
