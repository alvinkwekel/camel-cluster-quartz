package com.broekman.tryout;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("quartz2://myScheduler1?cron=0+*/1+*+*+*+?")
            .routeId("hello")
            .log("hello {{instance.name}}");
    }

}
