package com.vodafone.assessment.vodafonegarage.dto;

import com.vodafone.assessment.vodafonegarage.enums.VehicleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private Integer id;
    private String numberPlate;
    private String carColor;
    private VehicleTypeEnum vehicleType;
    private List<Integer> slotList;

}

