package com.zmey.uptime.controllers;

import com.zmey.uptime.services.targetprocessor.ProcessFactory;
import com.zmey.uptime.services.targetprocessor.TargetProcess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TargetProcessTest {

    @Autowired
    ProcessFactory processFactory;

    @Test
    public void testGetProcess() {
        String processType = "Unknown process";
        assertEquals("I am process Type Three", processFactory.generateTargetProcess(processType).process());
    }

    @Test
    public void testGetProcessTypeOne() {
        TargetProcess targetProcess = processFactory.generateTargetProcess("TypeOne");
        assertEquals("I am process Type One", targetProcess.process());
    }
}
