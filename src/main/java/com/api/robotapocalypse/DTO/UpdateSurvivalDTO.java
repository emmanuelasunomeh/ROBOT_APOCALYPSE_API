package com.api.robotapocalypse.DTO;

import com.api.robotapocalypse.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSurvivalDTO {

    Long survivalId;
    String longitude;
    String latitude;
}
