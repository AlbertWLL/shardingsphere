package com.example.danque.api.service;

import com.example.danque.api.entity.Vehicle;
import com.example.danque.common.cache.CachedData;

/**
 * @author danque
 * @date 2022/4/13
 * @desc
 */
public interface VehicleService {

    public String getVehicleFromMaster(long id);

    public CachedData<Vehicle> getVehicleFromSlave(long id);

    void saveVehicleInfo(Vehicle vehicle);

    void updateVehicleInfo(Vehicle vehicle);
}
