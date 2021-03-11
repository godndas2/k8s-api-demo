package com.example.api;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@Slf4j
public class TestK8sController {

    private Gauge promGuage;
    private Histogram histogram;

    @PostConstruct
    public void init() {
        promGuage = Gauge.build("demo", "demo metrics").register(); // Metric의 name을 지정해주고, Metric에 등록하게 된다.
        Counter.build("demoCounter", "demo counter").register();
        histogram = Histogram.build("demohistogram", "demohistogram").buckets(1.0f, 2.0f, 3.0f).register();
    }

    @GetMapping
    String demo() {
        promGuage.inc();
        histogram.observe(1.0f);
        return "demo";
    String hello() {
        return "HELLO KUBE"; // feature -> develop -> release Finish
    }

}
