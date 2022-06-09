package com.example.danque.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.danque.api.entity.Vehicle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author danque
 * @date 2022/4/13
 * @desc
 */
@Repository
@Mapper
public interface VehicleMapper extends BaseMapper<Vehicle> {

}
