package com.zmey.uptime.controllers;

import com.zmey.uptime.services.targetprocessor.ProcessFactory;
import com.zmey.uptime.services.targetprocessor.TargetProcess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TargetProcessTest {

    @Autowired
    ProcessFactory processFactory;

    @Test
    public void testGetProcess() {
        String processType = "Unknown process";
        assertEquals("I am process Type Three", processFactory.getTargetProcess(processType).process());
    }

    @Test
    public void testGetProcessTypeOne() {



        TargetProcess targetProcess = processFactory.getTargetProcess("TypeOne");



        assertEquals("I am process Type One", targetProcess.process());
    }
}
