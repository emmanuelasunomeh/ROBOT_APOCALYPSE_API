package com.api.robotapocalypse.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Robot {

    public String model;
    public String serialNumber;
    public Date manufacturedDate;
    public String category;
}
