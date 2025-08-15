package com.zmey.uptime.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zmey.uptime.entities.Target;

public interface TargetRepository extends JpaRepository <Target, Long>  {
    // Optional<Target> findById(Long id);
    Optional<Target> findByUrl(String url);
    List<Target> findAllByCustomerId(Long customerId);
}
