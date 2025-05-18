//package com.zmey.uptime.services.targetprocessor;
//
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ProcessFactory {
//
//    @Autowired
//    private List<TargetProcess> processes;
//
//    private final Map<String, TargetProcess>  targetProcessMap = processes.stream().collect(Collectors.toMap(TargetProcess::getType, Function.identity()));
//
//    public ProcessFactory(List<TargetProcess> processes) {
//        this.processes = processes;
//    }
//
//    public TargetProcess getTargetProcess(String type) {
//        return targetProcessMap.getOrDefault(type, null);
//    }
//}
//
