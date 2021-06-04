package com.vodafone.assessment.vodafonegarage.enums;

import lombok.Getter;

public enum VehicleTypeEnum {

    CAR("car", 1), JEEP("jeep", 2), TRUCK("truck", 4);

    @Getter
    private String vehicleType;

    @Getter
    private int slotHoldSize;

    VehicleTypeEnum(String vehicleType, int slotHoldSize) {
        this.vehicleType = vehicleType;
        this.slotHoldSize = slotHoldSize;
    }

    public static VehicleTypeEnum returnVehicleType(String vehicleType) {
        switch (vehicleType) {
            case "Car":
                return VehicleTypeEnum.CAR;
            case "Jeep":
                return VehicleTypeEnum.JEEP;
            case "Truck":
                return VehicleTypeEnum.TRUCK;
            default:
                return null;
        }
    }
}

