package com.zmey.uptime.services.targetprocessor;

import org.springframework.stereotype.Component;

@Component
public class ProcessOne implements TargetProcess {


    @Override
    public String process() {
        return "I am process Type One";
    }

    @Override
    public String getType() {
        return "one";
    }
}
