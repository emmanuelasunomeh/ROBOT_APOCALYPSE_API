package com.api.robotapocalypse.controller;

import com.api.robotapocalypse.DTO.SaveSurvivalDTO;
import com.api.robotapocalypse.DTO.UpdateSurvivalDTO;
import com.api.robotapocalypse.aop.annotation.LogExecutionTime;
import com.api.robotapocalypse.clients.RobotCPUClient;
import com.api.robotapocalypse.service.SurvivalService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;


@Data
@RestController
@RequestMapping("api/v1/robot-apocalypse")
public class SurvivalController {

    private final RobotCPUClient robotCPUClient;
    private final SurvivalService survivalService;

    @Autowired
    public SurvivalController(RobotCPUClient client, SurvivalService survival) {
        robotCPUClient = client;
        survivalService = survival;
    }

//    Add survivors to the database
    @LogExecutionTime
    @PostMapping("/save-survivor")
    @Transactional
    public ResponseEntity<?> createSurvivor(@RequestBody SaveSurvivalDTO saveSurvivalDTO){
        return new ResponseEntity<>(survivalService.saveSurvivor(saveSurvivalDTO), HttpStatus.CREATED);
    }
    //    Update survivor location
    @LogExecutionTime
    @PostMapping("/update-survivor-location")
    public ResponseEntity<?> updateSurvivorLocation(@RequestBody UpdateSurvivalDTO updateSurvivalDTO){
        return new ResponseEntity<>(survivalService.updateSurvivorLocation(updateSurvivalDTO), HttpStatus.OK);
    }
    //    Flag survivor as infected
    @LogExecutionTime
    @GetMapping("/flag-infected-survivor/{survivorId}")
    public ResponseEntity<?> indicateInfectedSurvivor(@PathVariable Long survivorId){
        Boolean flagInfected = survivalService.flagSurvivorInfected(survivorId);
        return new ResponseEntity<>(flagInfected?"Survivor is Infected":"Survivor Not Infected", HttpStatus.OK);
    }


    //    Percentage of infected survivors
    @LogExecutionTime
    @GetMapping("/percentage-of-infected-survivor")
    public ResponseEntity<?> getPercentageOfInfectedSurvivor(){
        return new ResponseEntity<>(survivalService.getPercentageOfInfectedSurvivor(), HttpStatus.OK);
    }

    @LogExecutionTime
    @GetMapping("/percentage-of-non-infected-survivor")
    public ResponseEntity<?> getPercentageOfNonInfectedSurvivor(){
        return new ResponseEntity<>(survivalService.getPercentageOfNonInfectedSurvivor(), HttpStatus.OK);
    }

    @LogExecutionTime
    @GetMapping("/list-of-infected-survivor")
    public ResponseEntity<?> getListOfInfectedSurvivor(){
        return new ResponseEntity<>(survivalService.listOfInfectedSurvivor(), HttpStatus.OK);
    }

    @LogExecutionTime
    @GetMapping("/list-of-non-infected-survivor")
    public ResponseEntity<?> getListOfNonInfectedSurvivor(){
        return new ResponseEntity<>(survivalService.listOfNonInfectedSurvivor(), HttpStatus.OK);
    }


    //    GET /api/stocks … (get the List of RobotCPU)
    @LogExecutionTime
    @GetMapping()
    public ResponseEntity<?> getAllRobotCPU() {
        return new ResponseEntity<>(survivalService.listOfRobots(), HttpStatus.OK);
    }

}
