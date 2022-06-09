package com.example.danque.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.danque.api.entity.Vehicle;
import com.example.danque.api.mapper.VehicleMapper;
import com.example.danque.api.service.VehicleService;
import com.example.danque.common.cache.CachedData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author danque
 * @date 2022/4/13
 * @desc
 */
@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public String getVehicleFromMaster(long id) {
        Vehicle vehicle = vehicleMapper.selectById(id);
        return JSON.toJSONString(vehicle);
    }

    @Override
    public CachedData<Vehicle> getVehicleFromSlave(long id) {
        Vehicle vehicle = vehicleMapper.selectById(id);
        return new CachedData<>(vehicle);
    }


    @Override
    @Transactional(rollbackFor = Exception.class,propagation= Propagation.SUPPORTS)
    public void saveVehicleInfo(Vehicle vehicle) {
        vehicleMapper.insert(vehicle);
    }


    @Override
    @Transactional(rollbackFor = Exception.class,propagation= Propagation.SUPPORTS)
    public void updateVehicleInfo(Vehicle vehicle) {
        UpdateWrapper<Vehicle> queryWrapper = new UpdateWrapper<>();
        queryWrapper.lambda().eq(Vehicle::getId,vehicle.getId());
        queryWrapper.lambda().eq(Vehicle::getCoId,vehicle.getCoId());
        int update = vehicleMapper.update(vehicle, queryWrapper);
        if(update > 0){
            System.out.println("update vehicle success!");
        }
    }
}
