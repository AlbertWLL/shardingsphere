package com.example.danque.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author danque
 * @date 2022/4/13
 * @desc
 */
@Data
@TableName(value = "tb_vehicle_1")
public class ShardingVehicle implements Serializable {

    private static final long serialVersionUID = 4544057081169003488L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private long userId;
    private long contactId;
    private String vin;
    private String pinCode;
    private String engerNo;
    private String vehicleName;
    private long photoId;
    private String licenseNo;
    private String licenseColor;
    private long brandId;
    private long marketId;
    private String materialCode;
    private Date bindTime;
    private long bindStatus;
    private String description;
    private long status;
    private long createBy;
    private Date createDate;
    private long lastUpdateBy;
    private Date lastUpdateDate;
    private long rowVersion;
    private boolean isValid;
    private long modelId;
    private String photoUrl;
    private long packageId;
    private String orderNo;
    private long seriesNo;
    private long oemId;
    private Date finishTime;
    private String sapCode;
    private Date checkoutDate;
    private Date licenseDate;
    private long platformId;
    private String fc01;
    private String clxh;
    private long projectnameId;
    private String oemFactory;

}
