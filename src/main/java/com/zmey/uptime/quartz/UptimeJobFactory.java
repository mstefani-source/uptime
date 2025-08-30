package com.zmey.uptime.quartz;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.NotImplementedException;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zmey.uptime.entities.Target;
import com.zmey.uptime.entities.enums.Protocol;
import com.zmey.uptime.entities.enums.ProtocolHandler;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class UptimeJobFactory {

    Collection<ProtocolHandler> protocolHandlers = null;

    private final Map<Protocol, Class<? extends Job>> jobClassMap;

    public UptimeJobFactory(Collection<ProtocolHandler> protocolHandlers) {
        this.protocolHandlers = protocolHandlers;
        this.jobClassMap = protocolHandlers.stream()
                .collect(Collectors.toMap(
                        ProtocolHandler::getProtocol,
                        ProtocolHandler::getProtocolClass));
    }

    public Class<? extends Job> getJobClass(Target savedTarget) {

        return jobClassMap.get(savedTarget.getProtocol());

    }
}
