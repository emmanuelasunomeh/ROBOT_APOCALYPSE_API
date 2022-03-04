package com.api.robotapocalypse.service.impl;

import com.api.robotapocalypse.DTO.Robot;
import com.api.robotapocalypse.DTO.RobotCpuClientDTO;
import com.api.robotapocalypse.DTO.SaveSurvivalDTO;
import com.api.robotapocalypse.DTO.UpdateSurvivalDTO;
import com.api.robotapocalypse.clients.RobotCPUClient;
import com.api.robotapocalypse.entity.Inventory;
import com.api.robotapocalypse.entity.Location;
import com.api.robotapocalypse.entity.Survival;
import com.api.robotapocalypse.exceptions.LocationNotFoundException;
import com.api.robotapocalypse.exceptions.SurvivalNotFoundException;
import com.api.robotapocalypse.repo.InventoryRepository;
import com.api.robotapocalypse.repo.LocationRepository;
import com.api.robotapocalypse.repo.SurvivalRepository;
import com.api.robotapocalypse.service.SurvivalService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@Data
public class SurvivalServiceImp implements SurvivalService {

    private final SurvivalRepository survivalRepository;
    private final RobotCPUClient robotCPUClient;
    private final LocationRepository locationRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public SurvivalServiceImp(SurvivalRepository survivalRepo, RobotCPUClient client,
                              LocationRepository locationRepo, InventoryRepository inventoryRepo){
        survivalRepository = survivalRepo;
        robotCPUClient = client;
        locationRepository = locationRepo;
        inventoryRepository = inventoryRepo;


    }

    @Override
    public Survival saveSurvivor(SaveSurvivalDTO saveSurvivalDTO) {

            Survival survival = survivalRepository.save(saveSurvivalDTO.getSurvival());
            Location location = saveSurvivalDTO.getLocation();
            location.setSurvival(survival);
            List<Inventory> inventories = saveSurvivalDTO.getInventories();
            survival.setLocation(locationRepository.save(new Location(location.getLatitude(), location.getLongitude(),survival)));
            List<Inventory> savedInventories = new ArrayList<>();
            for(Inventory inventory: inventories){
                inventory.setSurvival(survival);
                Inventory inv = inventoryRepository.save(inventory);
                savedInventories.add(inv);
            }
            return survival;

    }

    @Override
    public Location updateSurvivorLocation(UpdateSurvivalDTO updateSurvivalDTO) {
        Location location = locationRepository
                .findBySurvivalId(updateSurvivalDTO.getSurvivalId())
                .orElseThrow(() -> new LocationNotFoundException("NO LOCATION RECORD FOUND!!"));
        location.setLongitude(updateSurvivalDTO.getLongitude());
        location.setLatitude(updateSurvivalDTO.getLatitude());
        return locationRepository.save(location);
    }

    @Override
    public Boolean flagSurvivorInfected(Long survivalId) {

        Survival survival = survivalRepository.findById(survivalId)
                .orElseThrow(() -> new SurvivalNotFoundException("NO RECORD FOUND"));
        survival.setNoOfFlags(survival.getNoOfFlags()+1L);
        if(survival.getNoOfFlags()>=3 && survival.getIsInfected().equals(Boolean.TRUE)){
            return Boolean.TRUE;
        }
        if(survival.getNoOfFlags()>=3 && survival.getIsInfected().equals(Boolean.FALSE)){
            survival.setIsInfected(Boolean.TRUE);
            survivalRepository.save(survival);
            return Boolean.TRUE;
        }
        survivalRepository.save(survival);
        return Boolean.FALSE;
    }

    @Override
    public HashMap<String, Double> getPercentageOfInfectedSurvivor() {
        HashMap<String, Double> percentageOfInfectedSurvivor = getPercentageOfInfectedOrNonInfectedSurvival(Boolean.TRUE, "PercentageOfInfectedSurvivor");
        return percentageOfInfectedSurvivor;
    }


    @Override
    public HashMap<String, Double> getPercentageOfNonInfectedSurvivor() {
        HashMap<String, Double> percentageOfNonInfectedSurvivor = getPercentageOfInfectedOrNonInfectedSurvival(Boolean.FALSE, "PercentageOfNonInfectedSurvivor");
        return percentageOfNonInfectedSurvivor;
    }

    @Override
    public List<Survival> listOfInfectedSurvivor() {
        return this.getListOfInfectedOrNonInfectedSurvival(Boolean.TRUE);
    }

    @Override
    public List<Survival> listOfNonInfectedSurvivor() {
        return this.getListOfInfectedOrNonInfectedSurvival(Boolean.FALSE);
    }

    @Override
    public List<Robot> listOfRobots() {

        List<RobotCpuClientDTO> robotCpuList = robotCPUClient.getAllRobotCPU();
        List<Robot> robotList = new ArrayList<>();
        if(robotCpuList.size()>0){
            robotCpuList.stream().forEach( robotCpu -> {
                Robot robot = new Robot(robotCpu.getModel(), robotCpu.getSerialNumber(), robotCpu.getManufacturedDate(), robotCpu.getCategory());
                robotList.add(robot);
            });

        }
        return robotList;
    }


//    PRIVATE METHODS

//
    private HashMap<String, Double> getPercentageOfInfectedOrNonInfectedSurvival(Boolean isInfected, String getInfected) {

        Long infectedCount = survivalRepository.countByIsInfectedEquals(isInfected);
        Long totalCount = survivalRepository.countAll();

        Double percentage = infectedCount/Double.parseDouble(totalCount.toString());

        HashMap<String, Double> percentageOfInfectedSurvivor = new HashMap<>();
        percentageOfInfectedSurvivor.put(getInfected, percentage*100);
        return percentageOfInfectedSurvivor;
    }

    private List<Survival> getListOfInfectedOrNonInfectedSurvival(Boolean isInfected){
        List<Survival> SurvivalList = survivalRepository.findByIsInfected(isInfected);
        return SurvivalList;
    }

}
