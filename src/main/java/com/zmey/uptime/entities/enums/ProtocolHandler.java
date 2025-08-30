package com.zmey.uptime.entities.enums;

import org.quartz.Job;

public interface ProtocolHandler {
    Protocol getProtocol();
    Class<? extends Job> getProtocolClass();
}
