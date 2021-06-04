package com.vodafone.assessment.vodafonegarage.service.impl;

import com.vodafone.assessment.vodafonegarage.constant.GarageNotificationConstant;
import com.vodafone.assessment.vodafonegarage.dto.CarDto;
import com.vodafone.assessment.vodafonegarage.dto.ParkStatus;
import com.vodafone.assessment.vodafonegarage.entity.Car;
import com.vodafone.assessment.vodafonegarage.enums.StatusTypeEnum;
import com.vodafone.assessment.vodafonegarage.repository.CarRepository;
import com.vodafone.assessment.vodafonegarage.service.GarageService;
import com.vodafone.assessment.vodafonegarage.util.ParkUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class GarageServiceImpl implements GarageService {

    private final ModelMapper modelMapper;
    private final CarRepository carRepository;
    private int garageCapacity = 10;
    private final Map<Integer, Boolean> garageMap = new HashMap<>();
    private StringBuilder notificationBuilder = new StringBuilder();

    @PostConstruct
    void init() {
        for (int i = 1; i <= garageCapacity; i++) {
            garageMap.put(i, false);
        }
    }

    @Override
    public String produceTicket(String parkParams) {
        List<ParkStatus> parkStatusList = ParkUtil.checkParkTicketList(parkParams);
        return generateParkTicket(parkStatusList);
    }


    @Override
    public String generateParkTicket(List<ParkStatus> parkStatusList) {
        for (ParkStatus parkStatus : parkStatusList) {
            if (parkStatus.getStatusTypeEnum() == StatusTypeEnum.PARK) {
                log.info("will be " + StatusTypeEnum.PARK);
                Integer[] availableParking = findAvailableParking(parkStatus.getCarDto().getVehicleType().getSlotHoldSize());
                if (availableParking != null) {
                    parkStatus.getCarDto().setSlotList(Arrays.asList(availableParking));
                    carRepository.save(modelMapper.map(parkStatus.getCarDto(), Car.class));
                    Arrays.asList(availableParking)
                            .forEach(slotNumber -> garageMap.put(slotNumber, true));
                    notificationBuilder
                            .append(GarageNotificationConstant.GARAGE_ALLOCATED + " ")
                            .append(availableParking.length + " ")
                            .append(" " + GarageNotificationConstant.GARAGE_SLOT + "\n");
                } else {
                    notificationBuilder
                            .append(GarageNotificationConstant.GARAGE_CAPACITY_LABEL).append("\n");
                }
            } else if (parkStatus.getStatusTypeEnum() == StatusTypeEnum.LEAVE) {
                log.info("will be " + StatusTypeEnum.LEAVE);
                Optional<Car> carById = carRepository.getCarById(parkStatus.getUniqueNumber());
                if (carById.isPresent()) {
                    carRepository.delete(carById.get());
                    leaveParkingSlot(modelMapper.map(carById.get(), CarDto.class));
                } else {
                    notificationBuilder
                            .append(GarageNotificationConstant.GARAGE_NO_CAR);
                }
            } else if (parkStatus.getStatusTypeEnum() == StatusTypeEnum.STATUS) {
                log.info("will be " + StatusTypeEnum.STATUS);
                List<Car> listOfCars = carRepository.findAll();
                notificationBuilder
                        .append("\n" + GarageNotificationConstant.GARAGE_STATUS + "\n");
                listOfCars.forEach(car ->
                        notificationBuilder.append(car.getNumberPlate())
                                .append(" ").append(car.getCarColor())
                                .append(" ").append(car.getSlotList().toString()).append("\n"));
            }
        }
        return notificationBuilder.toString();
    }

    private void leaveParkingSlot(CarDto carDto) {
        carDto.getSlotList().forEach(slotNumber -> garageMap.put(slotNumber, false));
    }

    private Integer[] findAvailableParking(int slotSize) {
        for (int i = 1; i <= garageCapacity; i++) {
            if (i == 1) {
                Integer[] availableSlots = availableNearSlots(slotSize);
                if (availableSlots != null) {
                    log.info("find near parking");
                    return availableSlots;
                }
            } else {
                Integer[] availableSlots = availableFreeSlots(i, slotSize);
                if (availableSlots != null) {
                    log.info("find free parking");
                    return availableSlots;
                }
            }

        }
        return null;
    }

    private Integer[] availableFreeSlots(int index, int slotSize) {
        List<Integer> slotList = new ArrayList<>();
        for (int i = index - 1; i < index + slotSize; i++) {
            if (garageMap.containsKey(i) && !garageMap.get(i)) {
                slotList.add(i);
            } else {
                return null;
            }
        }
        slotList.remove(0);
        return slotList.toArray(new Integer[0]);
    }

    private Integer[] availableNearSlots(int slotSize) {
        List<Integer> slotNearList = new ArrayList<>();
        for (int i = 1; i < slotSize + 1; i++) {
            if (garageMap.containsKey(i) && !garageMap.get(i)) {
                slotNearList.add(i);
            } else {
                return null;
            }
        }
        return slotNearList.toArray(new Integer[0]);
    }


}
