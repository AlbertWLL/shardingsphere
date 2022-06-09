package com.example.danque.api.controller;

import com.example.danque.api.entity.Vehicle;
import com.example.danque.api.service.VehicleService;
import com.example.danque.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 动态数据源 + redis缓存 + rabbitMQ练习
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/updateVehicleInfo")
    public Result updateVehicleInfo(@RequestBody Vehicle vehicle) {
        vehicleService.updateVehicleInfo(vehicle);
        return Result.success(null);
    }

    @PostMapping("/saveVehicleInfo")
    public Result saveVehicleInfo(@RequestBody Vehicle vehicle) {
        vehicleService.saveVehicleInfo(vehicle);
        return Result.success(null);
    }
}


