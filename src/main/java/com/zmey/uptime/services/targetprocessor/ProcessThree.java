package com.zmey.uptime.services.targetprocessor;

import org.springframework.stereotype.Component;

@Component
public class ProcessThree implements TargetProcess {

    @Override
    public String process() {

        return "process Type Three";
    }

    @Override
    public String getType() {
        return "three";
    }
}
