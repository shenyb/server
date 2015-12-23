/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.common.core.service.system;

import com.need.common.domain.enums.WarehouseTypeEnum;

/**
 *
 * @author YAN
 */
public interface SystemSettingService {
    
    String getSystemSettingByName(String name);
    
    int getTransportLimit(WarehouseTypeEnum warehouseType);
    
    int getTransportFee(WarehouseTypeEnum warehouseType, int sumPrice);
    
    void init();
    
}
