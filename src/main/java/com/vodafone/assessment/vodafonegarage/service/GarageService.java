package com.vodafone.assessment.vodafonegarage.service;

import com.vodafone.assessment.vodafonegarage.dto.ParkStatus;
import java.util.List;

public interface GarageService {

    String produceTicket(String parkParams);

    String generateParkTicket(List<ParkStatus> parkStatusList);

}
