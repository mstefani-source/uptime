package com.zmey.uptime.entities.enums;

import org.quartz.Job;
import com.zmey.uptime.quartz.jobs.HttpJob;
import org.springframework.stereotype.Component;

@Component
public class HttpProtocolHandler implements ProtocolHandler {

    @Override
    public Protocol getProtocol() {
        return Protocol.HTTP;
    }

    @Override
    public Class<? extends Job> getProtocolClass() {
        return HttpJob.class;
    }
    
}
