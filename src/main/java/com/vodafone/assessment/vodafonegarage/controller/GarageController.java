package com.vodafone.assessment.vodafonegarage.controller;


import com.vodafone.assessment.vodafonegarage.service.GarageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/garage")
public class GarageController {

    private final GarageService garageService;

    @PostMapping("/parking")
    public String garageParking(@RequestBody String parkParams) {
        log.info("garage parking service called");
        return garageService.produceTicket(parkParams);
    }
}
