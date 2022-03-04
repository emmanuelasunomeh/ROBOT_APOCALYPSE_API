package com.api.robotapocalypse.clients;

import com.api.robotapocalypse.DTO.RobotCpuClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url="https://robotstakeover20210903110417.azurewebsites.net", name="robot-cpu")
public interface RobotCPUClient {

    @GetMapping("/robotcpu")
    public List<RobotCpuClientDTO> getAllRobotCPU();

}
