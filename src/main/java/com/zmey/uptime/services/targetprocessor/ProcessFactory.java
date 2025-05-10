package com.zmey.uptime.services.targetprocessor;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Supplier;

@Component
public class ProcessFactory {
    private final Map<String, Supplier<TargetProcess>> supplier = Map.of(
            "TypeOne", ProcessOne::new,
            "TypeTwo", ProcessTwo::new,
            "TypeThree", ProcessThree::new
    );

    public TargetProcess generateTargetProcess(String type) {
        return supplier.getOrDefault(type,  ProcessThree::new).get();
    }
}

