package com.api.robotapocalypse.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class RobotCpuClientDTO {
     public String model;
     public String serialNumber;
     public Date manufacturedDate;
     public String category;


}
