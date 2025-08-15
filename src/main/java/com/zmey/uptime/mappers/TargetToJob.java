package com.zmey.uptime.mappers;

import com.zmey.uptime.dto.JobDto;
import com.zmey.uptime.dto.TargetDto;

public class TargetToJob {

    JobDto convert(TargetDto targetDto){
        JobDto jobDto = new JobDto();

        jobDto.setDestination(targetDto.getUrl());
        jobDto.setType(targetDto.getProtocol());
        jobDto.setFrequency(targetDto.getFrequency());
        return jobDto;
    }
}
