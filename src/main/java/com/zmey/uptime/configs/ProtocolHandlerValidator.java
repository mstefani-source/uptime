package com.zmey.uptime.configs;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.NotImplementedException;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zmey.uptime.entities.enums.Protocol;
import com.zmey.uptime.entities.enums.ProtocolHandler;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ProtocolHandlerValidator {

    Collection<ProtocolHandler> protocolHandlers;
    private final Map<Protocol, Class<? extends Job>> jobClassMap;

    @Autowired
    public ProtocolHandlerValidator(Collection<ProtocolHandler> protocolHandlers) {
        this.protocolHandlers = protocolHandlers;
        jobClassMap = protocolHandlers.stream()
                .collect(Collectors.toMap(
                        ProtocolHandler::getProtocol,
                        ProtocolHandler::getProtocolClass));

        for (Protocol protocol : Protocol.values()) {
            log.info("---------> " + jobClassMap.get(protocol));
            if (jobClassMap.get(protocol) == null)
               throw new NotImplementedException("No Handler for protocol " + protocol);
        }

    }

}
