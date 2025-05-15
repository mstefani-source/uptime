package com.zmey.uptime.services.targetprocessor;

import org.springframework.stereotype.Component;

@Component
public class ProcessTwo implements TargetProcess {

    @Override
    public String process() {

        return "I am process Type Two";
    }

    @Override
    public String getType() {
        return "two";
    }
}
