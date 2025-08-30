package com.zmey.uptime.services;

import com.zmey.uptime.dto.CustomerDto;
import com.zmey.uptime.dto.TargetDto;
import com.zmey.uptime.entities.Target;
import com.zmey.uptime.mappers.TargetMapper;
import com.zmey.uptime.mappers.TargetToJob;
import com.zmey.uptime.quartz.JobManager;
import com.zmey.uptime.quartz.SchedulerManager;
import com.zmey.uptime.quartz.jobs.PingJob;

import com.zmey.uptime.repositories.TargetRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TargetService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    private TargetMapper mapper;

    // @Autowired
    // private SchedulerManager jobSheduler;

    @Autowired
    private JobManager jobManager;

    public TargetDto createTarget(TargetDto targetDto) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String authHeader = request.getHeader("Authorization");

        String jwtToken = authHeader != null && authHeader.startsWith("Bearer ")
                ? authHeader.substring(7)
                : null;

        TargetDto targetDto2 = targetDto;


        targetDto2.setCustomerId(jwtService.extractCustomerId(jwtToken));

        Target target = mapper.mapDtoToModel(targetDto);
        Target savedTarget = targetRepository.save(target);

        jobManager.scheduleJob(savedTarget);
        return mapper.mapModelToDto(savedTarget);
    }



    public void deleteTarget(Long id) {

        Optional<Target> existTarget = targetRepository.findById(id);

        if (existTarget.isPresent()) {
            targetRepository.deleteById(id);
            // jobManager.
        }
    }

    public Target updateTarget(Long id, Target target) {
        return targetRepository.findById(id)
                .map((Target existingTarget) -> {
                    existingTarget.setName(target.getName());
                    existingTarget.setCustomer(target.getCustomer());
                    existingTarget.setDescription(target.getDescription());
                    existingTarget.setUrl(target.getUrl());
                    return targetRepository.save(existingTarget);
                }).orElseThrow(() -> new EntityNotFoundException("Target not found"));

    }

    public Optional<Target> findById(Long id) {
        return targetRepository.findById(id);
    }

    public Optional<Target> findByUrl(String url) {
        return targetRepository.findByUrl(url);
    }

    public List<TargetDto> findAll() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomerDto customerDto = (CustomerDto) authentication.getPrincipal();
        List<Target> targets = targetRepository.findAllByCustomerId(customerDto.getId());

        List<TargetDto> result = targets.stream()
                .map(element -> mapper.mapModelToDto(element))
                .collect(Collectors.toList());

        return result;
    }

    private String getJwtTokenFromContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return (auth != null && auth.getCredentials() instanceof String)
                ? (String) auth.getCredentials()
                : null;
    }

}
