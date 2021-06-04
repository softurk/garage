package com.vodafone.assessment.vodafonegarage.entity;

import com.vodafone.assessment.vodafonegarage.enums.VehicleTypeEnum;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car")
@Data
public class Car {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String numberPlate;

    @Column
    private String carColor;

    @Column
    private VehicleTypeEnum vehicleType;

    @ElementCollection
    public List<Integer> slotList;
}
