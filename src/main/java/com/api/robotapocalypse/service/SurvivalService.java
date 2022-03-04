package com.api.robotapocalypse.service;

import com.api.robotapocalypse.DTO.Robot;
import com.api.robotapocalypse.DTO.RobotCpuClientDTO;
import com.api.robotapocalypse.DTO.SaveSurvivalDTO;
import com.api.robotapocalypse.DTO.UpdateSurvivalDTO;
import com.api.robotapocalypse.entity.Location;
import com.api.robotapocalypse.entity.Survival;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public interface SurvivalService {


    //    Add survivors to the database
    Survival saveSurvivor(SaveSurvivalDTO saveSurvivalDTO);

    //    Update survivor location
    Location updateSurvivorLocation(UpdateSurvivalDTO updateSurvivalDTO);

    //    Flag survivor as infected
    Boolean flagSurvivorInfected(Long SurvivalId);

    //            • Percentage of infected survivors.
    HashMap<String, Double> getPercentageOfInfectedSurvivor();

    //            • Percentage of non-infected survivors.
    HashMap<String, Double> getPercentageOfNonInfectedSurvivor();

    //            • List of infected survivors
    List<Survival> listOfInfectedSurvivor();

    //• List of non-infected survivors
    List<Survival> listOfNonInfectedSurvivor();

    //• List of robots
    List<Robot> listOfRobots();










}
