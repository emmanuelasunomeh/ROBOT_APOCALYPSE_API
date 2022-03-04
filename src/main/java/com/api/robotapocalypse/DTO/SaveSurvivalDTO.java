package com.api.robotapocalypse.DTO;

import com.api.robotapocalypse.entity.Inventory;
import com.api.robotapocalypse.entity.Location;
import com.api.robotapocalypse.entity.Survival;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveSurvivalDTO {

    Survival survival;
    Location location;
    List<Inventory> inventories;

}
