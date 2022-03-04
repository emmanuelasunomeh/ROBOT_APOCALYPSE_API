package com.api.robotapocalypse.repo;

import com.api.robotapocalypse.entity.Survival;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurvivalRepository extends JpaRepository<Survival, Long> {
    List<Survival> findByIsInfected(@NonNull Boolean isInfected);

    @Query("select count(s.id) from Survival s where s.isInfected=:isInfected ")
    Long countByIsInfectedEquals(@NonNull @Param("isInfected") Boolean isInfected);

    @Query("select count(id) from Survival")
    Long countAll();

}
