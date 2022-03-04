package com.api.robotapocalypse.repo;

import com.api.robotapocalypse.entity.Location;
import com.api.robotapocalypse.entity.Survival;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findBySurvivalId(Long survivalId);
}
