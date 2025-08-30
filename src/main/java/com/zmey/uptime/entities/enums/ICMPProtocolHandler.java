package com.zmey.uptime.entities.enums;

import org.quartz.Job;
import com.zmey.uptime.quartz.jobs.PingJob;
import org.springframework.stereotype.Component;

@Component
public class ICMPProtocolHandler implements ProtocolHandler {

    @Override
    public Protocol getProtocol() {
        return Protocol.ICMP;
    }

    @Override
    public Class<? extends Job> getProtocolClass() {
        return PingJob.class;
    }
    
}
