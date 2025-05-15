package com.zmey.uptime.services.targetprocessor;

import org.springframework.stereotype.Component;

@Component
public class ProcessFour implements TargetProcess {

    @Override
    public String process() {

        return "I am process Type Four";
    }

    @Override
    public String getType() {
        return "Four";
    }
}
