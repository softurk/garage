package com.vodafone.assessment.vodafonegarage.util;

import com.vodafone.assessment.vodafonegarage.dto.CarDto;
import com.vodafone.assessment.vodafonegarage.dto.ParkStatus;
import com.vodafone.assessment.vodafonegarage.enums.StatusTypeEnum;
import com.vodafone.assessment.vodafonegarage.enums.VehicleTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class ParkUtil {

    public static List<ParkStatus> checkParkTicketList(String parkParams) {
        List<ParkStatus> parkStatusList = new ArrayList<>();
        ParkStatus parkStatus = new ParkStatus();
        String[] paramSplit = parkParams.split("\n");
        for (String parkData : paramSplit) {
            if (!parkData.isEmpty()) {
                String[] splitData = parkData.split(" ");
                if (splitData.length == 4) {
                    CarDto carDto = new CarDto(null, splitData[1], splitData[2], VehicleTypeEnum.returnVehicleType(splitData[3]), null);
                    parkStatus.setCarDto(carDto);
                    parkStatus.setStatusTypeEnum(StatusTypeEnum.PARK);
                } else if (splitData.length == 2) {
                    parkStatus.setStatusTypeEnum(StatusTypeEnum.LEAVE);
                    parkStatus.setUniqueNumber(Integer.valueOf(splitData[1]));

                } else if (splitData.length == 1) {
                    parkStatus.setStatusTypeEnum(StatusTypeEnum.STATUS);
                }
                parkStatusList.add(parkStatus);
            }
        }
        return parkStatusList;
    }
}

