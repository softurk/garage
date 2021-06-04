package com.vodafone.assessment.vodafonegarage.dto;

import com.vodafone.assessment.vodafonegarage.enums.StatusTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkStatus {

    @NonNull
    private StatusTypeEnum statusTypeEnum;
    private CarDto carDto;
    private Integer uniqueNumber;

}


